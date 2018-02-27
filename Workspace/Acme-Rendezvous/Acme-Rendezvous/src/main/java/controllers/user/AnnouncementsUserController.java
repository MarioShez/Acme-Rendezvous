package controllers.user;

import java.util.Collection;
import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.AnnouncementService;
import services.RendezvousService;
import controllers.AbstractController;
import domain.Announcement;
import domain.Rendezvous;

@Controller
@RequestMapping("/announcements/user")
public class AnnouncementsUserController extends AbstractController {

	@Autowired
	private AnnouncementService announcementService;
	
	@Autowired
	private RendezvousService rendezvousService;

	// Constructors -----------------------------------------------------------

	public AnnouncementsUserController() {
		super();
	}

	// Listing --------------------------------------------------------------

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		Collection<Announcement> announcements;

		announcements = this.announcementService.findAnnouncementsByAttendants();

		result = new ModelAndView("announcement/list");
		result.addObject("announcement", announcements);
		result.addObject("requestURI", "announcement/list.do");

		return result;
	}
	
	// Create --------------
		@RequestMapping(value="/create", method=RequestMethod.GET)
		public ModelAndView create(@RequestParam final int rendezvousId){
			ModelAndView res;
			Announcement announcement;
			Rendezvous rendezvous;
			Date moment;
			
			
			rendezvous = this.rendezvousService.findOne(rendezvousId);
			
			announcement = this.announcementService.create(rendezvous);
			
			
//			moment = new Date(System.currentTimeMillis()-1);
//			
//			announcement.setRendezvous(rendezvous);
			//announcement.setMoment(moment);
			
			res = this.createEditModelAndView(announcement);
			
			return res;
		}
		
		@RequestMapping(value="/create",method=RequestMethod.POST, params = "save")
		public ModelAndView save(@Valid final Announcement announcement,
				final BindingResult binding){
			ModelAndView res;
			
			if(binding.hasErrors()){
				res = this.createEditModelAndView(announcement, "announcement.params.error");
			}else
				try{
					this.announcementService.save(announcement);
					res = new ModelAndView("redirect:../announcement/list.do");
				}catch (final Throwable oops) {
					res = this.createEditModelAndView(announcement, "announcement.commit.error");
				}
			
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
			res.addObject("requestURI","announcement/user/edit.do");
			
			return res;
		}
	
	

}
