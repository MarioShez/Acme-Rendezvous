
package controllers;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.QuestionService;
import services.RendezvousService;
import services.UserService;
import domain.Question;
import domain.Rendezvous;
import domain.User;

@Controller
@RequestMapping("/question")
public class QuestionController extends AbstractController {

	// Services ------------------------------------------------------

	@Autowired
	private QuestionService		questionService;

	@Autowired
	private UserService			userService;

	@Autowired
	private RendezvousService	rendezvousService;


	// Constructors --------------------------------------------------

	public QuestionController() {
		super();
	}

	// Listing -------------------------------------------------------

	@RequestMapping(value = "/user/list", method = RequestMethod.GET)
	public ModelAndView list(@RequestParam(required = true) final Integer rendezvousId) {

		final Collection<Question> questions = new ArrayList<Question>();

		final User principal = this.userService.findByPrincipal();
		final Rendezvous r = this.rendezvousService.findOne(rendezvousId);
		System.out.println(rendezvousId);
		System.out.println(r);
		Assert.notNull(r);
		questions.addAll(this.questionService.findByRendezvousId(rendezvousId));

		final Boolean isPrincipal = principal == r.getOrganiser();

		final ModelAndView result = new ModelAndView("question/list");
		result.addObject("questions", questions);
		result.addObject("isPrincipal", isPrincipal);
		result.addObject("requestURI", "question/user/list.do");

		return result;
	}

	/*
	 * @RequestMapping(value = "/list-organised", method = RequestMethod.GET)
	 * public ModelAndView organised(@RequestParam final int userId) {
	 * 
	 * Collection<Rendezvous> rendezvouses;
	 * 
	 * final User principal = this.userService.findByPrincipal();
	 * 
	 * if (principal == null || !this.userService.isAdult(principal.getBirth()))
	 * rendezvouses = this.questionService.findByOrganiserIdNotAdult(userId);
	 * else
	 * rendezvouses = this.questionService.findByOrganiserId(userId);
	 * 
	 * final ModelAndView result = new ModelAndView("rendezvous/list");
	 * result.addObject("rendezvouses", rendezvouses);
	 * result.addObject("requestURI", "rendezvous/list-organised.do");
	 * 
	 * return result;
	 * }
	 * 
	 * @RequestMapping(value = "/list-rspv", method = RequestMethod.GET)
	 * public ModelAndView rspv(@RequestParam final int userId) {
	 * 
	 * Collection<Rendezvous> rendezvouses;
	 * 
	 * final User principal = this.userService.findByPrincipal();
	 * 
	 * if (principal == null || !this.userService.isAdult(principal.getBirth()))
	 * rendezvouses = this.questionService.findByAttendantIdNotAdult(userId);
	 * else
	 * rendezvouses = this.questionService.findByAttendantId(userId);
	 * 
	 * final ModelAndView result = new ModelAndView("rendezvous/list");
	 * result.addObject("rendezvouses", rendezvouses);
	 * result.addObject("requestURI", "rendezvous/list-rspv.do");
	 * 
	 * return result;
	 * }
	 * 
	 * // Display -------------------------------------------------------
	 * 
	 * @RequestMapping(value = "/display", method = RequestMethod.GET)
	 * public ModelAndView display(@RequestParam final int rendezvousId) {
	 * 
	 * final Rendezvous rendezvous = this.questionService.findOne(rendezvousId);
	 * final User principal = this.userService.findByPrincipal();
	 * 
	 * Assert.isTrue((principal == null && rendezvous.getAdult() == false) || (!this.userService.isAdult(principal.getBirth()) && rendezvous.getAdult() == false) || (this.userService.isAdult(principal.getBirth())));
	 * 
	 * final ModelAndView result = new ModelAndView("rendezvous/display");
	 * result.addObject("rendezvous", rendezvous);
	 * result.addObject("areRSPVd", null);
	 * 
	 * return result;
	 * }
	 */
}
