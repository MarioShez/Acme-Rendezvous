package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import repositories.AnnouncementRepository;
import domain.Announcement;
import domain.Rendezvous;

public class AnnouncementService {

	// Managed repository
	
	@Autowired
	private AnnouncementRepository announcementRepository;
	
	// Supporting services
	@Autowired
	private RendezvousService rendezvousService;
	
	// Constructors
	
	public AnnouncementService(){
		super();
	}
	
	// Simple CRUD methods 
	
	public Announcement create(){
		Announcement res = new Announcement();
		
		Date moment = new Date(System.currentTimeMillis()-1);
		Rendezvous rendezvous = new Rendezvous();
		
		res.setRendezvous(rendezvous);
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
		
		res = this.announcementRepository.save(announcement);
		
		Assert.notNull(res);
		return res;
	}
	
	public void delete(Announcement announcement){
		Assert.notNull(announcement);
		Assert.isTrue(announcement.getId()!=0);
		Assert.isTrue(this.announcementRepository.exists(announcement.getId()));
		
		this.announcementRepository.delete(announcement);
	}


}
