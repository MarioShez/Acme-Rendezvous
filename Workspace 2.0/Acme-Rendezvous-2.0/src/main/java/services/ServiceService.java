
package services;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.ServiceRepository;
import domain.Category;
import domain.Manager;
import domain.Request;
import domain.Service;

@org.springframework.stereotype.Service
@Transactional
public class ServiceService {

	// Managed repository

	@Autowired
	private ServiceRepository	serviceRepository;

	// Supported services

	@Autowired
	private ManagerService		managerService;

	@Autowired
	private AdminService		adminService;


	//	@Autowired
	//	private CategoryService		categoryService;

	// Constructors

	public ServiceService() {
		super();
	}

	// Simple CRUD methods

	public Service create() {
		Assert.isTrue(this.managerService.checkAuthority());
		final Service res = new Service();
		final Manager manager = this.managerService.findByPrincipal();
		final boolean cancelled = false;
		final Collection<Category> categories = new ArrayList<Category>();
		final Collection<Request> requests = new ArrayList<Request>();

		res.setCategories(categories);
		res.setManager(manager);
		res.setCancelled(cancelled);
		res.setRequests(requests);

		return res;
	}

	public Collection<Service> findAll() {
		Collection<Service> res;
		res = this.serviceRepository.findAll();
		Assert.notNull(res);
		return res;
	}

	public Service findOne(final int serviceId) {
		Assert.isTrue(serviceId != 0);
		Service res;
		res = this.serviceRepository.findOne(serviceId);
		return res;
	}
	
	public Service findOneToEdit(final int serviceId) {
		Assert.isTrue(serviceId != 0);
		Service res;
		res = this.serviceRepository.findOne(serviceId);
		Assert.isTrue(res.getManager().equals(managerService.findByPrincipal()));
		Assert.isTrue(res.isCancelled() == false);
		return res;
	}

	public Service save(final Service service) {

		Assert.isTrue(service.getManager().equals(this.managerService.findByPrincipal()) || this.adminService.checkAuthority());
		Assert.notNull(service.getManager());

		Service res;

		res = this.serviceRepository.save(service);
		if (service.getId() == 0)
			service.getManager().getServices().add(res);

		return res;
	}

	public void delete(final Service service) {

		Assert.isTrue(service.getManager().equals(this.managerService.findByPrincipal()));
		Assert.isTrue(service.getRequests().isEmpty());

		service.getManager().getServices().remove(service);
		//		for (final Category c : service.getCategories()) {
		//			c.getServices().remove(service);
		//			this.categoryService.save(c);
		//		}
		
		this.serviceRepository.delete(service);
	}

	// Other business methods

	public Collection<Service> bestSellingService() {
		final Collection<Service> result = this.serviceRepository.bestSellingServices();
		return result;
	}

}
