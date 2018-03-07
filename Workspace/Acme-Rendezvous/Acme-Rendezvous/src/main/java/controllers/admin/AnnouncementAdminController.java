package controllers.admin;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.AnnouncementService;

import controllers.AbstractController;

import domain.Announcement;
import domain.Comment;
import domain.Rendezvous;

@Controller
@RequestMapping("/announcement/admin")
public class AnnouncementAdminController extends AbstractController {

	// Services ---------------
	@Autowired
	private AnnouncementService announcementService;
	
	//Constructors -------------
	public AnnouncementAdminController(){
		super();
	}
	
	// List -----------
	@RequestMapping(value="/list", method=RequestMethod.GET)
	public ModelAndView list(){
		ModelAndView res;
		Collection<Announcement> announcements;
	
		announcements = this.announcementService.findAll();
		
		res = new ModelAndView("announcement/list");
		res.addObject("announcement",announcements);
		res.addObject("requestURI","announcement/list.do");
		
		return res;
	}
	
	// Delete ------------
	
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public ModelAndView delete(int announcementId){
		
		Announcement announcement = announcementService.findOne(announcementId);
		
		announcementService.delete(announcement);
		
		return new ModelAndView("redirect:list.do");
	}

	// Ancillary methods --------------------------------------------------
	
	private ModelAndView createEditModelAndView(final Announcement announcement) {
		ModelAndView res;
		
		res = this.createEditModelAndView(announcement, null);
		
		return res;
	}

	
	private ModelAndView createEditModelAndView(final Announcement announcement,
			final String message) {
		ModelAndView res;
		Rendezvous rendezvous;
		rendezvous = announcement.getRendezvous();
		
		res = new ModelAndView("announcement/edit");
		res.addObject("announcement", announcement);
		res.addObject("rendezvous", rendezvous);
		res.addObject("message",message);
		
		return res;
	}
	
}
