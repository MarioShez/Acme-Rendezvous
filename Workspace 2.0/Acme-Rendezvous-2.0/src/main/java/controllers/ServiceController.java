package controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import domain.Service;

import services.ServiceService;

@Controller
@RequestMapping("/service")
public class ServiceController extends AbstractController{
	
	// Services ---------------
	@Autowired
	private ServiceService serviceService;
	
	// Constructors -----------
	public ServiceController(){
		super();
	}
	
	// Listing ----------------
	@RequestMapping(value="/list", method=RequestMethod.GET)
	public ModelAndView list(){
		ModelAndView res;
		Collection<Service> services;
		
		services = this.serviceService.findAll();
		
		res = new ModelAndView("service/list");
		res.addObject("services",services);
		res.addObject("requestURI","service/list.do");
		
		return res;
	}
}
