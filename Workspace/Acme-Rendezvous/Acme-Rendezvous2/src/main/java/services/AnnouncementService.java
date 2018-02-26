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

import repositories.AnnouncementRepository;
import domain.Announcement;
import domain.Rendezvous;
import domain.User;
import forms.AnnouncementForm;

@Service
@Transactional
public class AnnouncementService {

	// Managed repository
	
	@Autowired
	private AnnouncementRepository announcementRepository;
	
	// Supporting services
//	@Autowired
//	private RendezvousService rendezvousService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private AdminService adminService;

	@Autowired
	private Validator validator;
	
	// Constructors
	
	public AnnouncementService(){
		super();
	}
	
	// Simple CRUD methods 
	
	public Announcement create(){
		Announcement res = new Announcement();
		
		Date moment = new Date(System.currentTimeMillis()-1);
//		Rendezvous rendezvous = new Rendezvous();
//		
//		res.setRendezvous(rendezvous);
		res.setMoment(moment);
		
		return res;
		
	}
	
	public Collection<Announcement> findAll(){
		Collection<Announcement> res;
		res = new ArrayList<Announcement>();
		
		res = this.announcementRepository.findAll();
		
		Assert.notNull(res);
		return res;
	}
	
	
	public Announcement findOne(int announcementId){
		Assert.isTrue(announcementId!=0);
		Announcement res;
		
		res = this.announcementRepository.findOne(announcementId);
		
		Assert.notNull(res);
		return res;
	}
	
	public Announcement save(Announcement announcement){
		Assert.notNull(announcement);
		Announcement res;
		User user = new User();
		Collection<Rendezvous> rendezvousByPrincipal = new ArrayList<Rendezvous>();
		Date moment;
		
		//editar sus propios announcements
		if(announcement.getId() != 0){
			Collection<Announcement> announcementUser;
			announcementUser = this.announcementRepository.announcementsByUser(user.getId());
			Assert.isTrue(announcementUser.contains(announcement));
		}
		
		//crear announcements para los rendezvous que ha organizado
		rendezvousByPrincipal = user.getOrganisedRendezvouses();
		Assert.isTrue(rendezvousByPrincipal.contains(announcement.getRendezvous()));
		
		moment = new Date(System.currentTimeMillis()-1);
		announcement.setMoment(moment);
		
		res = this.announcementRepository.save(announcement);
		
		Assert.notNull(res);
		return res;
	}
	
	public void delete(Announcement announcement){
		this.adminService.checkAuthority();
		
		Assert.notNull(announcement);
		Assert.isTrue(announcement.getId()!=0);
		Assert.isTrue(this.announcementRepository.exists(announcement.getId()));
//		
//		Rendezvous rendezvous = new Rendezvous();
//		rendezvous = announcement.getRendezvous();
//		rendezvous.getAnnouncements().remove(announcement);
		
		this.announcementRepository.delete(announcement);
	}
	
	// Other busines methods
	
	public Collection<Announcement> findAnnouncementsByRendezvous(int rendezvousId){
		Collection<Announcement> res = new ArrayList<Announcement>();
		
		res = announcementRepository.findAnnouncementsByRendezvous(rendezvousId);
		Assert.notNull(res);
		
		return res;
	}
	
	public Announcement reconstruct(AnnouncementForm announcementForm, BindingResult binding){
		Announcement res = new Announcement();
		
		Date moment = new Date(System.currentTimeMillis()-1);
		
		res.setMoment(moment);
		res.setTitle(announcementForm.getTitle());
		res.setDescription(announcementForm.getDescription());
		
		validator.validate(res, binding);
		
		return res;
	}
	
//	public Collection<Announcement> findAnnouncementsByAttendants(){
//		Collection<Announcement> res;
//		User user = new User();
//		user = this.userService.findByPrincipal();
//		res = this.announcementRepository.findAnnouncementsByAttendants(user.getId());
//		Assert.notNull(res);
//		return res;
//	}


}
