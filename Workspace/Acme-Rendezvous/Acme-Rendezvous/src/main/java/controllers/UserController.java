package controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.UserService;
import domain.User;

@Controller
@RequestMapping("/user")
public class UserController extends AbstractController {
	
	// Services -------------------------------------------------------------

	@Autowired
	private UserService userService;
	
	// Constructors ---------------------------------------------------------

	public UserController(){
		super();
	}
	
	
	// Listing --------------------------------------------------------------

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		Collection<User> users;
		
		users = userService.findAll();
		
		result = new ModelAndView("user/list");
		result.addObject("user", users);
		result.addObject("requestURI", "user/list.do");
		
		return result;
	}
	
	@RequestMapping(value = "/rendezvousCreator/list", method = RequestMethod.GET)
	public ModelAndView listRendezvousCreator(@RequestParam int rendezvousId) {
		ModelAndView result;
		User organizer;
		
		organizer = userService.findOrganiserByRendezvousId(rendezvousId);
		
		result = new ModelAndView("user/list");
		result.addObject("user", organizer);
		result.addObject("requestURI", "user/rendezvousCreator/list.do");
		
		return result;
	}
	
	@RequestMapping(value = "/RendezvousAttends/list", method = RequestMethod.GET)
	public ModelAndView listRendezvousAttendant(@RequestParam int rendezvousId) {
		ModelAndView result;
		Collection<User> attends;
		
		attends = userService.findAttendsByRendezvousId(rendezvousId);
		
		result = new ModelAndView("user/list");
		result.addObject("user", attends);
		result.addObject("requestURI", "user/rendezvousAttends/list.do");
		
		return result;
	}
	
}
