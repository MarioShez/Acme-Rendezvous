package controllers.user;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.CommentService;
import services.RendezvousService;
import services.UserService;
import controllers.AbstractController;
import domain.Comment;
import domain.Rendezvous;
import domain.User;

@Controller
@RequestMapping("/comment/user")
public class CommentUserController extends AbstractController {

	// Services -------------------------------------------------------------

	@Autowired
	private CommentService commentService;

	@Autowired
	private UserService userService;

	@Autowired
	private RendezvousService rendezvousService;

	// Constructors ---------------------------------------------------------

	public CommentUserController() {
		super();
	}

	// Listing --------------------------------------------------------------

	@RequestMapping(value = "/listReplies", method = RequestMethod.GET)
	public ModelAndView list(@RequestParam int commentId) {
		ModelAndView result;
		
		Comment comment = new Comment();
		comment = this.commentService.findOne(commentId);
		
		Collection<Comment> comments;
		comments = comment.getReplies();

		result = new ModelAndView("comment/list");

		result.addObject("comment", comments);
		result.addObject("requestURI", "comment/list.do");

		return result;
	}
	
	
	// Creation ---------------------------------------------------------------
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		Comment c;

		c = this.commentService.create();
		result = this.createEditModelAndView(c);
		return result;
	}
	
//	@RequestMapping(value = "/editReplies", method = RequestMethod.GET)
//	public ModelAndView createReplies(@RequestParam int commentId) {
//		ModelAndView result;
//		Comment c;
//		Comment commentParent;
//		
//		commentParent = this.commentService.findOne(commentId);
//		
//		c = this.commentService.create();
//		c.setCommentParent(commentParent);
//		
//		result = this.createEditModelAndView(c);
//		return result;
//	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(Comment comment, final BindingResult binding) {
		ModelAndView result;
		int rendezvousId = 0;

		if (binding.hasErrors())
			result = this.createEditModelAndView(comment, "comment.params.error");
		else
			try {
				this.commentService.save(comment);
				rendezvousId = comment.getRendezvous().getId();
				result = new ModelAndView("redirect:/comment/rendezvous/list.do?rendezvousId=" + rendezvousId);
			} catch (final Throwable oops) {
				result = this.createEditModelAndView(comment,
						"comment.commit.error");
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
		User u = new User();
		Collection<Rendezvous> rendezvous;

		u = this.userService.findByPrincipal();
		rendezvous = this.rendezvousService.findByAttendantId(u.getId());

		result = new ModelAndView("comment/edit");
		result.addObject("rendezvous", rendezvous);
		result.addObject("comment", comment);
		result.addObject("message", message);

		return result;
	}

}
