package controllers;

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
	
	@RequestMapping(value = "/rendezvous/list", method = RequestMethod.GET)
	public ModelAndView display(@RequestParam int rendezvousId) {
		ModelAndView result;
		Rendezvous rendezvous;
		
		Collection<Comment> comment;
		rendezvous = this.rendezvousService.findOne(rendezvousId);
		comment = rendezvous.getComments();

		result = new ModelAndView("comment/list");

		result.addObject("comment", comment);
		result.addObject("requestURI", "comment/rendezvous/list.do");

		return result;
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

		result = new ModelAndView("comment/edit");
		result.addObject("comment", comment);
		result.addObject("message", message);

		return result;
	}
	
//	protected ModelAndView createEditModelAndView(final CommentForm comment) {
//		ModelAndView result;
//
//		result = this.createEditModelAndView(comment, null);
//
//		return result;
//	}
//
//	protected ModelAndView createEditModelAndView(final CommentForm comment,
//			final String message) {
//		ModelAndView result;
//
//		result = new ModelAndView("comment/edit");
//		result.addObject("commentForm", comment);
//		result.addObject("message", message);
//
//		return result;
//	}
}
