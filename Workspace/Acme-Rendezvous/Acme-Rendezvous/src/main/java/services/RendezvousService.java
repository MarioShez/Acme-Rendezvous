package services;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.RendezvousRepository;
import domain.Announcement;
import domain.Comment;
import domain.Question;
import domain.Rendezvous;
import domain.User;

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
//	
//	@Autowired
//	private QuestionService questionService;
//	
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
		result.setLinkedRendezvouses(new ArrayList<Rendezvous>());

		return result;

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
		Assert.isTrue(rendezvous.getFinalVersion() == false);
		Assert.isTrue(rendezvous.getDeleted() == false);
		this.checkPrincipal(rendezvous);

		final Rendezvous result;

		result = this.rendezvousRepository.save(rendezvous);

		if (rendezvous.getId() == 0) {
			result.getOrganiser().getOrganisedRendezvous().add(result);
		}

		return result;
	}
	
//	public void delete(final Rendezvous rendezvous) {
//
//		Assert.notNull(rendezvous);
//		Assert.isTrue(rendezvous.getId() != 0);
//		Assert.isTrue(adminService.findByPrincipal() != null);
//
//		rendezvous.getOrganiser().getOrganisedRendezvous().remove(rendezvous);
//		for(User attendant:rendezvous.getAttendants()){
//			attendant.getRsvpdRendezvous().remove(rendezvous);
//		}
//		for(Announcement announcement:rendezvous.getAnnouncements()){
//			announcementService.delete(announcement);
//		}
//		for(Comment comment:rendezvous.getComments()){
//			commentService.delete(comment);
//		}
//		for(Question question:rendezvous.getQuestions()){
//			questionService.delete(question);
//		}
//		
//		this.rendezvousRepository.delete(rendezvous);
//	}

	// Other business methods -------------------------------------------------

	public void checkPrincipal(final Rendezvous rendezvous) {

		Assert.notNull(rendezvous);

		final User principal = this.userService.findByPrincipal();
		Assert.isTrue(rendezvous.getOrganiser().equals(principal));
	}
	
	public Collection<Rendezvous> findFutureMomentAndNotAdult(){
		
		Collection<Rendezvous> result = rendezvousRepository.findFutureMomentAndNotAdult();
		return result;
	}
	
	public Collection<Rendezvous> findFutureMoment(){
		
		Collection<Rendezvous> result = rendezvousRepository.findFutureMoment();
		return result;
	}
	
	public Collection<Rendezvous> findByPrincipal(){
		
		User organiser = userService.findByPrincipal();
		Collection<Rendezvous> result = rendezvousRepository.findByOrganiserId(organiser.getId());
		return result;
	}

}
