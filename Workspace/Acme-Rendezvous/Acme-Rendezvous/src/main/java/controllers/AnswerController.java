
package controllers;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.AnswerService;
import services.RendezvousService;
import domain.Answer;
import domain.Question;
import domain.Rendezvous;
import forms.AnswerForm;

@Controller
@RequestMapping("/answer")
public class AnswerController extends AbstractController {

	// Services ------------------------------------------------------

	@Autowired
	private AnswerService		answerService;

	@Autowired
	private RendezvousService	rendezvousService;


	// Constructors --------------------------------------------------

	public AnswerController() {
		super();
	}

	// Listing -------------------------------------------------------

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(@RequestParam final int rendezvousId, @RequestParam final int userId) {
		
		ModelAndView result;
		Collection<AnswerForm> answerForms = new ArrayList<AnswerForm>();
		Collection<Question> questions = new ArrayList<Question>();
		
		Rendezvous rendezvous = this.rendezvousService.findOne(rendezvousId);
		questions.addAll(rendezvous.getQuestions());
		
		for (Question question : questions){
			AnswerForm answerForm = new AnswerForm();
			answerForm.setQuestionId(question.getId());
			answerForm.setQuestionContent(question.getContent());
			
			Answer answer = answerService.findByUserIdAndQuestionId(userId, question.getId());
			if(answer != null){
				answerForm.setContent(answer.getContent());
				answerForms.add(answerForm);
			}
		}
			
		result = new ModelAndView("answer/list");
		result.addObject("answerForms", answerForms);
		result.addObject("canAnswer", false);
		result.addObject("allAnswered", false);
		result.addObject("requestURI", "answer/list.do");
			
		return result;	
	}
}
