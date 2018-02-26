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

@Controller
@RequestMapping("/announcement")
public class AnnouncementAdminController extends AbstractController {

	// Services ---------------
	@Autowired
	private AnnouncementService announcementService;
	
	//Constructors -------------
	public AnnouncementAdminController(){
		super();
	}
	
	// List -----------
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
	
	// Delete ------------
	
	@RequestMapping(value = "/admin/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int announcementId) {
		ModelAndView result;
		Announcement announcement = new Announcement();

		announcement = this.announcementService.findOne(announcementId);
		Assert.notNull(announcement);
		result = this.createEditModelAndView(announcement);

		return result;
	}
	
	
	///admin/delete.do?announcementId=${row.id}
	@RequestMapping(value="/admin/edit", method=RequestMethod.POST, params ="delete")
	public ModelAndView create(@Valid final Announcement announcement, 
			final BindingResult binding){
		ModelAndView res;
		
		Assert.notNull(announcement);
		try{
			this.announcementService.delete(announcement);
			res = new ModelAndView("redirect:list.do");
		}catch (final Throwable oops) {
			res = this.createEditModelAndView(announcement, "announcement.commit.error");
		}
		
		return res;
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
		
		res = new ModelAndView("announcement/edit");
		res.addObject("announcement", announcement);
		res.addObject("message",message);
		
		return res;
	}
	
}
