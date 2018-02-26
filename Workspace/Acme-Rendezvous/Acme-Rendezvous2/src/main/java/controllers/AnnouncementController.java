package controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
	public ModelAndView list(@RequestParam final int rendezvousId){
		ModelAndView res;
		Collection<Announcement> announcements;
	
		announcements = this.announcementService.findAnnouncementsByRendezvous(rendezvousId);
		
		res = new ModelAndView("announcement/list");
		res.addObject("announcement",announcements);
		res.addObject("requestURI","announcement/list.do");
		
		return res;
	}
	
	@RequestMapping(value="/admin/list", method=RequestMethod.GET)
	public ModelAndView list(){
		ModelAndView res;
		Collection<Announcement> announcements;
	
		announcements = this.announcementService.findAll();
		
		res = new ModelAndView("announcement/list");
		res.addObject("announcement",announcements);
		res.addObject("requestURI","announcement/list.do");
		
		return res;
	}

	@RequestMapping(value="user/create", method=RequestMethod.GET)
	public ModelAndView create(@RequestParam final int rendezvousId){
		ModelAndView res;
		Announcement announcement;
		
		announcement = this.announcementService.create();
		res = this.createEditModelAndView(announcement);
		
		return res;
	}

	private ModelAndView createEditModelAndView(final Announcement announcement) {
		ModelAndView res;
		
		res = this.createEditModelAndView(announcement,null);
		
		return res;
	}

	private ModelAndView createEditModelAndView(final Announcement announcement,
			final String message) {
		ModelAndView res;
		res = new ModelAndView("announcement/edit");
		
		res.addObject("announcement",announcement);
		res.addObject("message",message);
		
		return res;
	}
}
