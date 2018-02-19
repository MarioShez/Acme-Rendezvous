package services;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.util.Assert;


import org.springframework.beans.factory.annotation.Autowired;

import repositories.AnnouncementRepository;

import domain.Announcement;

public class AnnouncementService {

	// Managed repository
	
	@Autowired
	private AnnouncementRepository announcementRepository;
	
	// Supporting services
	
	
	// Constructors
	
	public AnnouncementService(){
		super();
	}
	
	// Simple CRUD methods 
	
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
