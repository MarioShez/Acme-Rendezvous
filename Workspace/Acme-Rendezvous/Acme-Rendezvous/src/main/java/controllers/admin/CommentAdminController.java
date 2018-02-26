//package controllers.admin;
//
//import javax.validation.Valid;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.servlet.ModelAndView;
//import org.springframework.web.servlet.mvc.AbstractController;
//
//import services.CommentService;
//import domain.Comment;
//import forms.CommentForm;
//
//@Controller
//@RequestMapping("/administrator/comment")
//public class CommentAdminController extends AbstractController {
//
//	// Services -------------------------------------------------------
//
//	@Autowired
//	private CommentService commentService;
//
//	// Constructors ---------------------------------------------------------
//
//	public CommentAdminController() {
//		super();
//	}
//
//	// Deleting --------------------------------------------------------------
//
//	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
//	public ModelAndView delete(@Valid final CommentForm CommentForm,
//			final BindingResult binding) {
//		ModelAndView res;
//		Comment comment;
//		
//		comment = 
//		try {
//			this.commentService.delete(comment);
//			res = new ModelAndView("redirect:list.do");
//		} catch (final Throwable oops) {
//			res = this
//					.createEditModelAndView(survival, "survival.commit.error");
//		}
//
//		return res;
//	}
//
//	// Ancillary methods --------------------------------------------------
//
//	protected ModelAndView createEditModelAndView(final CommentForm commentForm) {
//		ModelAndView result;
//
//		result = this.createEditModelAndView(commentForm, null);
//
//		return result;
//	}
//
//	protected ModelAndView createEditModelAndView(
//			final CommentForm commentForm, final String message) {
//		ModelAndView result;
//
//		result = new ModelAndView("user/register");
//		result.addObject("userForm", commentForm);
//		result.addObject("message", message);
//
//		return result;
//	}
//
//}
