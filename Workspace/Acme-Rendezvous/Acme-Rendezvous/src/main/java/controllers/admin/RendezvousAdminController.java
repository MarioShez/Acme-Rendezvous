package controllers.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.RendezvousService;
import controllers.AbstractController;
import domain.Rendezvous;

@Controller
@RequestMapping("/rendezvous/admin")
public class RendezvousAdminController extends AbstractController {

	// Services ------------------------------------------------------

	@Autowired
	private RendezvousService rendezvousService;

	// Constructors --------------------------------------------------

	public RendezvousAdminController() {
		super();
	}

	// Delete	------------------------------------------------------
	
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public ModelAndView delete(int rendezvousId){
		
		Rendezvous rendezvous = rendezvousService.findOne(rendezvousId);
		
		rendezvousService.delete(rendezvous);
		
		return new ModelAndView("redirect:welcome/index.do");
	}

}
