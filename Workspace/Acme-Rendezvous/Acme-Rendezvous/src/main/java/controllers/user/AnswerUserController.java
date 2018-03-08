package controllers.user;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.AnswerService;
import services.QuestionService;
import services.RendezvousService;
import services.UserService;
import controllers.AbstractController;
import domain.Answer;
import domain.Question;
import domain.Rendezvous;
import domain.User;
import forms.AnswerForm;

@Controller
@RequestMapping("/answer/user")
public class AnswerUserController extends AbstractController {

	@Autowired
	private QuestionService		questionService;
	
	@Autowired
	private RendezvousService	rendezvousService;
	
	@Autowired
	private UserService			userService;
	
	@Autowired
	private AnswerService		answerService;
	
	// Listing ----------------------------------------------------
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(@RequestParam final int rendezvousId){
		
		ModelAndView result;
		
		User user = userService.findByPrincipal();
		Collection<AnswerForm> answerForms = new ArrayList<AnswerForm>();
		Collection<Question> questions = new ArrayList<Question>();
		
		Rendezvous rendezvous = rendezvousService.findOne(rendezvousId);
		questions.addAll(rendezvous.getQuestions());
		
		Collection<Answer> answers = new ArrayList<Answer>();
		for(Question question:questions){
			AnswerForm answerForm = new AnswerForm();
			answerForm.setQuestionId(question.getId());
			answerForm.setQuestionContent(question.getContent());
			
			Answer answer = answerService.findByUserIdAndQuestionId(user.getId(), question.getId());
			answerForm.setContent(answer.getContent());
			answerForms.add(answerForm);
		}
		
		result = new ModelAndView("answer/list");
		result.addObject("answerForms", answerForms);
		result.addObject("requestURI", "answer/user/list.do");
		if(questions.size() == answers.size()){
			result.addObject("allAnswered", true);
		}else{
			result.addObject("allAnswered", false);
		}
		result.addObject("rendezvousId", rendezvousId);
		result.addObject("canAnswer", true);
		
		return result;
	}
	
	@RequestMapping(value = "/respond", method = RequestMethod.GET)
	public ModelAndView respond(int questionId){
		
		ModelAndView result;
		
		User user = userService.findByPrincipal();
		AnswerForm answerForm = new AnswerForm();
		
		Question question = questionService.findOne(questionId);
		answerForm.setQuestionId(questionId);
		answerForm.setQuestionContent(question.getContent());
		
		Answer answer = answerService.findByUserIdAndQuestionId(user.getId(), questionId);
		if(answer != null){
			answerForm.setId(answer.getId());
			answerForm.setContent(answer.getContent());
		}else{
			answerForm.setId(0);
			answerForm.setContent("");
		}
		
		result = new ModelAndView("answer/edit");
		result.addObject("answerForm", answerForm);
		result.addObject("rendezvousId", question.getRendezvous().getId());
		
		return result;
	}
	
	@RequestMapping(value = "/respond", method = RequestMethod.POST, params = "save")
	public ModelAndView save(AnswerForm answerForm, BindingResult binding) {
		
		ModelAndView result;
		
		try {
			Question question = this.questionService.findOne(answerForm.getQuestionId());
			Answer answer = null;
			if (answerForm.getId() == 0) {
				answer = this.answerService.create();
				answer.setQuestion(question);
				answer.setContent(answerForm.getContent());
				this.answerService.save(answer);
			} else {
				answer = this.answerService.findOne(answer.getId());
				answer.setContent(answerForm.getContent());
				this.answerService.save(answer);
			}

			result = new ModelAndView("redirect:list.do?rendezvousId=" + question.getRendezvous().getId());

		} catch (final Throwable oops) {
			result = this.createEditModelAndView(answerForm, "answer.commit.error");
		}
		
		return result;
	}
	
	// Ancillary methods ---------------------------------------------------------------
	
	private ModelAndView createEditModelAndView(final AnswerForm answerForm) {
		
		ModelAndView result;

		result = this.createEditModelAndView(answerForm, null);

		return result;
	}
	
	private ModelAndView createEditModelAndView(final AnswerForm answerForm, final String message) {
		
		ModelAndView result;
		
		result = new ModelAndView("answer/edit");
		result.addObject("answerForm", answerForm);
		result.addObject("message", message);
		result.addObject("rendezvousId", questionService.findOne(answerForm.getQuestionId()).getRendezvous().getId());

		return result;
	}

}
