package controllers.user;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.AnnouncementService;
import controllers.AbstractController;
import domain.Announcement;
import domain.User;

@Controller
@RequestMapping("/announcements/user")
public class AnnouncementsUserController extends AbstractController {

	@Autowired
	private AnnouncementService announcementService;

	// Constructors -----------------------------------------------------------

	public AnnouncementsUserController() {
		super();
	}

	// Listing --------------------------------------------------------------

//	@RequestMapping(value = "/list", method = RequestMethod.GET)
//	public ModelAndView list() {
//		ModelAndView result;
//		Collection<Announcement> announcements;
//
//		announcements = this.announcementService.findAnnouncementsByAttendants();
//
//		result = new ModelAndView("user/list");
//		result.addObject("announcement", announcements);
//		result.addObject("requestURI", "announcement/list.do");
//
//		return result;
//	}
	
	

}
