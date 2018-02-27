
package controllers;

import java.util.ArrayList;
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
		Assert.notNull(r);
		questions.addAll(this.questionService.findByRendezvousId(rendezvousId));

		final Boolean isPrincipal = principal == r.getOrganiser();

		final ModelAndView result = new ModelAndView("question/list");
		result.addObject("questions", questions);
		result.addObject("isPrincipal", isPrincipal);
		result.addObject("requestURI", "question/user/list.do");

		return result;
	}

	@RequestMapping(value = "/user/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView create(@Valid final Question question, final BindingResult binding) {
		ModelAndView result;

		Assert.notNull(question);
		try {
			this.questionService.delete(question);
			result = new ModelAndView("redirect:list.do");
		} catch (final Throwable oops) {
			result = this.createEditModelAndView(question, "comment.commit.error");
		}

		return result;
	}

	//Ancillary methods

	protected ModelAndView createEditModelAndView(final Question question) {
		ModelAndView result;

		result = this.createEditModelAndView(question, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(final Question question, final String message) {
		ModelAndView result;
		final Rendezvous r = question.getRendezvous();
		result = new ModelAndView("question/edit");

		result.addObject("question", question);
		result.addObject("rendezvous", r);
		result.addObject("message", message);

		return result;
	}
}
