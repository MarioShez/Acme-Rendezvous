package services;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.ServicesRepository;
import domain.Category;
import domain.Manager;
import domain.Services;

@Service
@Transactional
public class ServicesService {

	// Managed repository

	@Autowired
	private ServicesRepository servicesRepository;

	@Autowired
	private ManagerService managerService;

	@Autowired
	private AdminService adminService;

	// Constructors

	public ServicesService() {
		super();
	}

	// Simple CRUD methods

	public Services create() {
		managerService.checkAuthority();
		Services res = new Services();
		Manager manager = new Manager();
		Collection<Category> category= new ArrayList<Category>();
		
		res.setCategory(category);
		res.setManager(manager);
		

		return res;
	}

	public Collection<Services> findAll() {
		Collection<Services> res;
		res = this.servicesRepository.findAll();
		Assert.notNull(res);
		return res;
	}

	public Services findOne(int services) {
		Assert.isTrue(services != 0);
		Services res;
		res = this.servicesRepository.findOne(services);
		Assert.notNull(res);
		return res;
	}
	
	public Services save(Services services) {
		managerService.checkAuthority();
		Services res = new Services();
		Manager manager= this.managerService.findByPrincipal();
		
		services.setManager(manager);
		
		res= this.servicesRepository.save(services);
		
		

		return res;
	}
	
	public void delete(Services services){
		this.adminService.checkAuthority();
		
		this.servicesRepository.delete(services);
	}
	
}
