package controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.AnnouncementService;
import domain.Announcement;

@Controller
@RequestMapping("/announcement")
public class AnnouncementController extends AbstractController {
	
	// Services ----------------------
	@Autowired
	private AnnouncementService announcementService;
	
	// Constructors ------------------
	public AnnouncementController(){
		super();
	}
	
	// Listing ------------------------
	@RequestMapping(value="/list", method=RequestMethod.GET)
	public ModelAndView list(){
		ModelAndView res;
		Collection<Announcement> announcements;
		
		// TODO: ?? this.announcementService.findByRendezvous()
		announcements = this.announcementService.findAll();
		
		res = new ModelAndView("announcement/list");
		res.addObject("announcement",announcements);
		res.addObject("requestURI","announcement/list.do");
		
		return res;
	}

}
