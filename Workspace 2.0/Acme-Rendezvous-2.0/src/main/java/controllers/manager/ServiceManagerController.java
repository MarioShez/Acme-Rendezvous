package controllers.manager;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.ManagerService;
import services.ServiceService;
import controllers.AbstractController;
import domain.Service;
import domain.ServiceForm;

@Controller
@RequestMapping("/service/manager")
public class ServiceManagerController extends AbstractController{

	// Services ---------------
	@Autowired
	private ServiceService serviceService;
	
	@Autowired
	private ManagerService managerService;
	
	// Constructors -----------
	public ServiceManagerController(){
		super();
	}
	
	// Listing ----------------
	@RequestMapping(value="/list", method=RequestMethod.GET)
	public ModelAndView list(){
		this.managerService.checkAuthority();
		
		ModelAndView res;
		Collection<Service> services;
		
		services = this.serviceService.findAll();
		
		res = new ModelAndView("service/list");
		res.addObject("services",services);
		res.addObject("requestURI","service/list.do");
		
		return res;
	}
	
	// Create --------------
	@RequestMapping(value="/create",method=RequestMethod.GET)
	public ModelAndView create(){
		ModelAndView res;
		Service service;
		ServiceForm serviceForm;
		
		service = this.serviceService.create();
		serviceForm = this.serviceService.construct(service);
		
		res = this.createEditModelAndView(serviceForm);
		
		return res;
	}
	
	@RequestMapping(value="/edit",method=RequestMethod.POST, params = "save")
	public ModelAndView save( final ServiceForm serviceForm,
			final BindingResult binding){
		ModelAndView res;
		
		if(binding.hasErrors()){
			res = this.createEditModelAndView(serviceForm, "service.params.error");
		}else
			try{
				Service service = this.serviceService.reconstruct(serviceForm, binding);
				this.serviceService.save(service);

				res = new ModelAndView("redirect:/service/manager/list.do");
			}catch (final Throwable oops) {
				System.out.println(oops);
				System.out.println(binding);
				res = this.createEditModelAndView(serviceForm, "service.commit.error");
			}
		
		return res;
	}
	
	

	protected ModelAndView createEditModelAndView(final ServiceForm serviceForm) {
		ModelAndView res;
		
		res = this.createEditModelAndView(serviceForm,null);
		
		return res;
	}

	protected ModelAndView createEditModelAndView(final ServiceForm serviceForm,
			final String message) {
		ModelAndView res;
		
		res = new ModelAndView("service/edit");
		res.addObject("serviceForm", serviceForm);
		res.addObject("message",message);
		res.addObject("requestURI","service/manager/edit.do");
		
		return res;
	}
}
