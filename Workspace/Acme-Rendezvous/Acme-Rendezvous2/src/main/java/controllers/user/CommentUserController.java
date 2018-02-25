//package controllers.user;
//
//import java.util.Collection;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.servlet.ModelAndView;
//
//import services.CommentService;
//import services.RendezvousService;
//import services.UserService;
//import controllers.AbstractController;
//import domain.Comment;
//import domain.Rendezvous;
//import domain.User;
//
//@Controller
//@RequestMapping("/comment/user")
//public class CommentUserController extends AbstractController {
//
//	// Services -------------------------------------------------------------
//
//	@Autowired
//	private CommentService commentService;
//
//	@Autowired
//	private UserService userService;
//	
//	@Autowired
//	private RendezvousService rendezvousService;
//
//	// Constructors ---------------------------------------------------------
//
//	public CommentUserController() {
//		super();
//	}
//	
//	
//	//Listing --------------------------------------------------------------
//	
//	@RequestMapping(value = "/list", method = RequestMethod.GET)
//	public ModelAndView display(@RequestParam int rendezvousId){
//		ModelAndView result;
//		Rendezvous rendezvous;
//		Collection<Comment> comment;
//		
//		rendezvous = this.rendezvousService.findOne(rendezvousId);
//		
//		comment= rendezvous.getComments();
//		
//		result = new ModelAndView("comment/list");
//		
//		result.addObject("comment", comment);
//		result.addObject("requestURI", "comment/user/list.do");
//		
//		return result;
//	}
//	
//	// Creation ---------------------------------------------------------------
//
//		@RequestMapping(value = "/create", method = RequestMethod.GET)
//		public ModelAndView create() {
//			ModelAndView result;
//			Comment c;
//
//			c = this.commentService.create();
//			result = this.createModelAndView(c);
//			return result;
//		}
//
//		// Ancillary methods --------------------------------------------------
//
//		protected ModelAndView createModelAndView(final Comment comment) {
//			ModelAndView result;
//
//			result = this.createModelAndView(comment, null);
//
//			return result;
//		}
//		
//		protected ModelAndView createModelAndView(
//				final Comment comment, final String message) {
//			ModelAndView result;
//			result = new ModelAndView("application/explorer/edit");
//			
//			Rendezvous r = new Rendezvous();
//			
//			User user = userService.findOrganiserByRendezvousId(r.getId());
//			
//			result.addObject("user", user);
//			result.addObject("comment", comment);
//			result.addObject("message", message);
//			return result;
//		}
//		
//		
//		
//		
//
//	
//
//}
