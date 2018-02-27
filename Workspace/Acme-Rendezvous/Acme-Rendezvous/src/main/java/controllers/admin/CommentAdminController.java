package controllers.admin;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.AdminService;
import services.CommentService;
import services.RendezvousService;
import services.UserService;

import controllers.AbstractController;
import domain.Comment;
import domain.Rendezvous;
import domain.User;

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
		
		// Deleting --------------------------------------------------------------

		@RequestMapping(value = "/edit", method = RequestMethod.GET)
		public ModelAndView create(@RequestParam final int commentId) {
			ModelAndView result;
			Comment comment = new Comment();

			comment = commentService.findOne(commentId);
			Assert.notNull(comment);
			result = this.createEditModelAndView(comment);

			return result;
		}
		
		@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
		public ModelAndView create(@Valid final Comment comment,
				final BindingResult binding) {
			ModelAndView result;
			
			Assert.notNull(comment);
			try{
				this.commentService.delete(comment);
				result = new ModelAndView("redirect:list.do");
			}catch (final Throwable oops) {
				result = this
						.createEditModelAndView(comment, "comment.commit.error");
			}
			
			return result;
		}
		
		// Ancillary methods --------------------------------------------------

		protected ModelAndView createEditModelAndView(final Comment comment) {
			ModelAndView result;

			result = this.createEditModelAndView(comment, null);

			return result;
		}

		protected ModelAndView createEditModelAndView(final Comment comment,
				final String message) {
			ModelAndView result;
			User u= new User();
			Rendezvous r= new Rendezvous();
			u= comment.getUser();
			r= comment.getRendezvous();
			Comment commentParent= new Comment();
			
			
			result = new ModelAndView("comment/edit");
			//result.addObject("user", u);
			//result.addObject("rendezvous", r);
			result.addObject("comment", comment);
			result.addObject("comment", commentParent);
			
			result.addObject("message", message);

			return result;
		}
	
}
