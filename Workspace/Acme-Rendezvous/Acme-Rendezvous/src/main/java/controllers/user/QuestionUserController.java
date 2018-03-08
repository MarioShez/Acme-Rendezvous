
package controllers.user;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.QuestionService;
import services.RendezvousService;
import controllers.AbstractController;
import domain.Question;
import domain.Rendezvous;
import forms.QuestionForm;

@Controller
@RequestMapping("/question/user")
public class QuestionUserController extends AbstractController {

	// Services ----------------------------------------------------

	@Autowired
	private QuestionService		questionService;

	@Autowired
	private RendezvousService	rendezvousService;


	// Constructors ------------------------------------------------

	public QuestionUserController() {
		super();
	}

	// Listing ---------------------------------------------------

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(@RequestParam final int rendezvousId) {

		final Collection<Question> questions = rendezvousService.findOneToEdit(rendezvousId).getQuestions();

		final ModelAndView result = new ModelAndView("question/list");
		result.addObject("questions", questions);
		result.addObject("rendezvousId", rendezvousId);

		return result;
	}
	
	// Creation ---------------------------------------------------

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create(@RequestParam final int rendezvousId) {
		
		ModelAndView result;
		
		Rendezvous rendezvous = rendezvousService.findOneToEdit(rendezvousId);	
		Question question = questionService.create(rendezvous);
		QuestionForm questionForm = questionService.construct(question);
		
		result = createEditModelAndView(questionForm); 
		
		return result;
	}
	
	// Edition -----------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int questionId) {
		
		ModelAndView result;
		
		Question question = this.questionService.findOneToEdit(questionId);
		QuestionForm questionForm = questionService.construct(question);
		
		result = createEditModelAndView(questionForm);
		
		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(final QuestionForm questionForm, final BindingResult binding) {
		
		ModelAndView result;
		
		if (binding.hasErrors())
			result = this.createEditModelAndView(questionForm);
		else
			try {
				final Question question = this.questionService.reconstruct(questionForm, binding);
				this.questionService.save(question);
				result = new ModelAndView("redirect:list.do");
			} catch (final Throwable oops) {
				result = this.createEditModelAndView(questionForm, "question.commit.error");
			}
		return result;
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(final QuestionForm questionForm, final BindingResult binding) {

		ModelAndView result;

		try {
			Question question = questionService.reconstruct(questionForm, binding);
			this.questionService.delete(question);
			result = new ModelAndView("redirect:list.do");
		} catch (final Throwable oops) {
			result = this.createEditModelAndView(questionForm, "question.commit.error");
		}

		return result;
	}

	//Ancillary methods

	protected ModelAndView createEditModelAndView(final QuestionForm questionForm) {
		ModelAndView result;

		result = this.createEditModelAndView(questionForm, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(final QuestionForm questionForm, final String message) {
		
		ModelAndView result;
		result = new ModelAndView("question/edit");

		result.addObject("question", questionForm);
		result.addObject("message", message);

		return result;
	}
}
