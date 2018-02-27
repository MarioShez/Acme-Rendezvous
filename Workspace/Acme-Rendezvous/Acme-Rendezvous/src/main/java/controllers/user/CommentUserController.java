package controllers.user;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
	
	
	//Listing --------------------------------------------------------------
	
	
	// Creation ---------------------------------------------------------------


	// Ancillary methods --------------------------------------------------

		
		
		
		

	

}
