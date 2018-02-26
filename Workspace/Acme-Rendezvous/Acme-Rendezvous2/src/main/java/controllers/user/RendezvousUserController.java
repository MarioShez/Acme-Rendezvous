package controllers.user;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.RendezvousService;
import services.UserService;
import controllers.AbstractController;
import domain.Rendezvous;
import forms.RendezvousForm;

@Controller
@RequestMapping("/rendezvous/user")
public class RendezvousUserController extends AbstractController {

	// Services ------------------------------------------------------

	@Autowired
	private RendezvousService rendezvousService;

	@Autowired
	private UserService userService;

	// Constructors --------------------------------------------------

	public RendezvousUserController() {
		super();
	}

	// Listing -------------------------------------------------------
	
	@RequestMapping(value = "/list-organised", method = RequestMethod.GET)
	public ModelAndView organised() {
		
		Collection<Rendezvous> rendezvouses = rendezvousService.findOrganisedRendezvousesByPrincipal();
		
		ModelAndView result = new ModelAndView("rendezvous/list");
		result.addObject("rendezvouses", rendezvouses);
		result.addObject("requestURI", "rendezvous/user/list-organised.do");
		
		return result;
	}
	
	@RequestMapping(value = "/list-rspv", method = RequestMethod.GET)
	public ModelAndView rspv() {
		
		Collection<Rendezvous> rendezvouses = rendezvousService.findRspvdRendezvousesByPrincipal();
		
		ModelAndView result = new ModelAndView("rendezvous/list");
		result.addObject("rendezvouses", rendezvouses);
		result.addObject("requestURI", "rendezvous/user/list-rspv.do");
		
		return result;
	}
	
	// Create ---------------------------------------------------------
	
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create(@RequestParam (required = false) int rendezvousId){
		
		RendezvousForm rendezvousForm = new RendezvousForm();
		rendezvousForm.setFinalVersion(false);
		rendezvousForm.setDeleted(false);
		rendezvousForm.setAdult(false);
	}
	

}
