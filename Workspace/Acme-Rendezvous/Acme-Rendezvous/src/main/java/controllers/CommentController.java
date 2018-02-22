package controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.AdminService;
import services.CommentService;
import services.RendezvousService;
import domain.Comment;
import domain.Rendezvous;

@Controller
@RequestMapping("/comment")
public class CommentController extends AbstractController {

	
	// Services -------------------------------------------------------------

		@Autowired
		private CommentService commentService;

		@Autowired
		private AdminService adminService;
		
		@Autowired
		private RendezvousService rendezvousService;

		// Constructors ---------------------------------------------------------

		public CommentController() {
			super();
		}
		
		//Listing --------------------------------------------------------------
		
		@RequestMapping(value = "/list", method = RequestMethod.GET)
		public ModelAndView display(@RequestParam int rendezvousId){
			ModelAndView result;
			
			Collection<Comment> comment;
			comment= this.commentService.findAll();
			
			result = new ModelAndView("comment/list");
			
			result.addObject("comment", comment);
			result.addObject("requestURI", "comment/list.do");
			
			return result;
		}
}
