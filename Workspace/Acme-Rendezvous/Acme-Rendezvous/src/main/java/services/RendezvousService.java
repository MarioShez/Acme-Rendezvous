package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

import repositories.RendezvousRepository;
import domain.Announcement;
import domain.Comment;
import domain.GpsCoordinate;
import domain.Question;
import domain.Rendezvous;
import domain.User;
import forms.RendezvousForm;

@Service
@Transactional
public class RendezvousService {

	// Managed repository -----------------------------------------------------

	@Autowired
	private RendezvousRepository rendezvousRepository;

	// Supporting services ----------------------------------------------------

	@Autowired
	private UserService userService;

	@Autowired
	private AdminService adminService;

	@Autowired
	private AnnouncementService announcementService;

	@Autowired
	private CommentService commentService;

	@Autowired
	private QuestionService questionService;

	@Autowired
	private Validator validator;

	// Constructors -----------------------------------------------------------

	public RendezvousService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------

	public Rendezvous create() {

		final Rendezvous result = new Rendezvous();

		result.setAdult(false);
		result.setFinalVersion(false);
		result.setDeleted(false);
		result.setOrganiser(this.userService.findByPrincipal());
		result.setAnnouncements(new ArrayList<Announcement>());
		result.setComments(new ArrayList<Comment>());
		result.setQuestions(new ArrayList<Question>());
		result.setAttendants(new ArrayList<User>());
		result.getAttendants().add(this.userService.findByPrincipal());
		result.setLinkedRendezvouses(new ArrayList<Rendezvous>());

		return result;

	}
	
	public Collection<Rendezvous> findAll() {
		Collection<Rendezvous> res;
		res = this.rendezvousRepository.findAll();
		Assert.notNull(res);
		return res;
	}

	public Rendezvous findOne(final int rendezvousId) {

		final Rendezvous result = this.rendezvousRepository
				.findOne(rendezvousId);
		return result;
	}

	public Rendezvous findOneToEdit(final int rendezvousId) {

		final Rendezvous result = this.findOne(rendezvousId);
		this.checkPrincipal(result);
		return result;
	}

	public Rendezvous save(final Rendezvous rendezvous) {

		Assert.notNull(rendezvous);
		Assert.notNull(rendezvous.getName());
		Assert.notNull(rendezvous.getDescription());
		Assert.notNull(rendezvous.getMoment());
		final Date actualDate = new Date();
		Assert.isTrue(rendezvous.getMoment().after(actualDate));
		this.checkPrincipal(rendezvous);

		final Rendezvous result;

		result = this.rendezvousRepository.save(rendezvous);
		
		User principal = userService.findByPrincipal();

		if (rendezvous.getId() == 0)
			principal.getOrganisedRendezvouses().add(result);
			principal.getRsvpdRendezvouses().add(result);

		return result;
	}

	public void delete(final Rendezvous rendezvous) {

		Assert.notNull(rendezvous);
		Assert.isTrue(rendezvous.getId() != 0);
		Assert.isTrue(this.adminService.findByPrincipal() != null);

		rendezvous.getOrganiser().getOrganisedRendezvouses().remove(rendezvous);
		for (final User attendant : rendezvous.getAttendants())
			attendant.getRsvpdRendezvouses().remove(rendezvous);
		for (final Announcement announcement : rendezvous.getAnnouncements())
			this.announcementService.delete(announcement);
		if(rendezvous.getComments().size()>0)
			this.commentService.deleteAll(rendezvous.getComments());
		for (final Comment comment : rendezvous.getComments())
			this.commentService.delete(comment);
		for (final Question question : rendezvous.getQuestions())
			this.questionService.delete(question);
		for (final Rendezvous linkedRendezvous : rendezvous.getLinkedRendezvouses())
			rendezvous.getLinkedRendezvouses().remove(linkedRendezvous);
		for (Rendezvous parentRendezvous : this.findParentRendezvouses(rendezvous.getId())){
			parentRendezvous.getLinkedRendezvouses().remove(rendezvous);
		}

		this.rendezvousRepository.delete(rendezvous);
}

	// Other business methods -------------------------------------------------

	public Rendezvous reconstruct(RendezvousForm rendezvousForm,
			BindingResult binding) {
		
		GpsCoordinate gpsCoordinate = new GpsCoordinate();
		gpsCoordinate.setLatitude(rendezvousForm.getLatitude());
		gpsCoordinate.setLongitude(rendezvousForm.getLongitude());

		Rendezvous result = new Rendezvous();

		result.setName(rendezvousForm.getName());
		result.setDescription(rendezvousForm.getDescription());
		result.setPicture(rendezvousForm.getPicture());
		result.setMoment(rendezvousForm.getMoment());
		result.setGpsCoordinate(gpsCoordinate);
		result.setAdult(rendezvousForm.getAdult());
		result.setFinalVersion(rendezvousForm.getFinalVersion());
		result.setDeleted(rendezvousForm.getDeleted());

		result.setAnnouncements(new ArrayList<Announcement>());
		result.setAttendants(new ArrayList<User>());
		result.setLinkedRendezvouses(new ArrayList<Rendezvous>());
		result.setComments(new ArrayList<Comment>());
		result.setQuestions(new ArrayList<Question>());

		validator.validate(result, binding);

		return result;
	}

	public void checkPrincipal(final Rendezvous rendezvous) {

		Assert.notNull(rendezvous);

		final User principal = this.userService.findByPrincipal();
		Assert.isTrue(rendezvous.getOrganiser().equals(principal));
	}

	public void changeToDeleted(final int rendezvousId) {

		final Rendezvous rendezvous = this.findOneToEdit(rendezvousId);

		Assert.isTrue(rendezvous.getDeleted() == false);
		Assert.isTrue(rendezvous.getFinalVersion() == false);

		rendezvous.setDeleted(true);
		this.save(rendezvous);
	}

	public Collection<Rendezvous> findFutureMomentAndNotAdult() {

		final Collection<Rendezvous> result = this.rendezvousRepository
				.findFutureMomentAndNotAdult();
		return result;
	}

	public Collection<Rendezvous> findFutureMoment() {

		final Collection<Rendezvous> result = this.rendezvousRepository
				.findFutureMoment();
		return result;
	}
	
	public Collection<Rendezvous> linkedRendezvousesFutureMomentByRendezvousId(int rendezvousId){
		
		Collection<Rendezvous> result = rendezvousRepository.linkedRendezvousesFutureMomentByRendezvousId(rendezvousId);
		return result;
	}
	
	public Collection<Rendezvous> linkedRendezvousesFutureMomentAndNotAdultByRendezvousId(int rendezvousId){
		
		Collection<Rendezvous> result = rendezvousRepository.linkedRendezvousesFutureMomentAndNotAdultByRendezvousId(rendezvousId);
		return result;
	}
	
	public Collection<Rendezvous> findByOrganiserId(int organiserId) {

		final Collection<Rendezvous> result = this.rendezvousRepository
				.findByOrganiserId(organiserId);
		return result;
	}
	
	public Collection<Rendezvous> findByOrganiserIdNotAdult(int organiserId) {

		final Collection<Rendezvous> result = this.rendezvousRepository
				.findByOrganiserIdNotAdult(organiserId);
		return result;
	}

	public Collection<Rendezvous> findByAttendantId(int attendantId) {

		final Collection<Rendezvous> result = this.rendezvousRepository
				.findByAttendantId(attendantId);
		return result;
	}
	
	public Collection<Rendezvous> findByAttendantIdNotAdult(int attendantId) {

		final Collection<Rendezvous> result = this.rendezvousRepository
				.findByAttendantIdNotAdult(attendantId);
		return result;
	}
	
	public Collection<Rendezvous> findOrganisedRendezvousesByPrincipal() {

		final User organiser = this.userService.findByPrincipal();
		Collection<Rendezvous> result = findByOrganiserId(organiser.getId());
		return result;
	}
	
	public Collection<Rendezvous> findRspvdRendezvousesByPrincipal() {

		final User attendant = this.userService.findByPrincipal();
		Collection<Rendezvous> result = findByAttendantId(attendant.getId());
		return result;
	}
	
	public Collection<Rendezvous> findParentRendezvouses(int rendezvousId){
		
		Collection<Rendezvous> result = rendezvousRepository.findParentRendezvouses(rendezvousId);
		return result;
	}

}
