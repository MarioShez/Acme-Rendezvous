package controllers.admin;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.AdminService;
import services.CommentService;
import services.RendezvousService;
import services.UserService;
import controllers.AbstractController;
import domain.Comment;

@Controller
@RequestMapping("/comment/admin")
public class CommentAdminController extends AbstractController{

	// Services -------------------------------------------------------------

		@Autowired
		private CommentService commentService;

		@Autowired
		private AdminService adminService;

		 @Autowired
		 private RendezvousService rendezvousService;

		 @Autowired
		 private UserService userService;
		 
		// Constructors ---------------------------------------------------------

		public CommentAdminController() {
			super();
		}
		
		// Listing --------------------------------------------------------------

		@RequestMapping(value = "/list", method = RequestMethod.GET)
		public ModelAndView display() {
			ModelAndView result;

			Collection<Comment> comment;
			comment = this.commentService.findAll();

			result = new ModelAndView("comment/list");

			result.addObject("comment", comment);
			result.addObject("requestURI", "comment/list.do");

			return result;
		}
		
		// Deleting --------------------------------------------------------------

		@RequestMapping(value = "/delete", method = RequestMethod.GET)
		public ModelAndView delete(int commentId){
			
			Comment comment = commentService.findOne(commentId);
			
			commentService.delete(comment);
			
			return new ModelAndView("redirect:list.do");
		}
		
}
