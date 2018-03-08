
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

import repositories.QuestionRepository;
import domain.Answer;
import domain.Question;
import domain.Rendezvous;
import domain.User;
import forms.QuestionForm;

@Service
@Transactional
public class QuestionService {

	// Managed repository

	@Autowired
	private QuestionRepository	questionRepository;

	// Supporting services

	@Autowired
	private AnswerService		answerService;

	@Autowired
	private RendezvousService	rendezvousService;

	@Autowired
	private UserService			userService;
	
	@Autowired
	private Validator			validator;


	public QuestionService() {
		super();
	}

	// Simple CRUD methods

	public Question create(final Rendezvous r) {

		final Question res = new Question();

		res.setRendezvous(r);

		return res;
	}

	public Collection<Question> findAll() {
		Collection<Question> res;
		res = this.questionRepository.findAll();
		Assert.notNull(res);
		return res;
	}

	public Question findOne(final int questionId) {
		Assert.isTrue(questionId != 0);
		Question res;
		res = this.questionRepository.findOne(questionId);
		Assert.notNull(res);
		return res;
	}
	
	public Question findOneToEdit(final int questionId) {
		Assert.isTrue(questionId != 0);
		Question res;
		res = this.questionRepository.findOne(questionId);
		checkPrincipal(res);
		return res;
	}

	public Question save(final Question question) {
		Assert.notNull(question);
		Question res;
		res = this.questionRepository.save(question);
		
		if(question.getId() == 0){
			res.getRendezvous().getQuestions().add(res);
		}
		
		return res;
	}
	
	public void delete(final Question question) {
		
		Assert.notNull(question);
		Assert.isTrue(question.getId() != 0);
		
		for (Answer answer : question.getAnswers()){
			answer.getUser().getAnswer().remove(answer);
			this.answerService.delete(answer);
		}
		
		question.getRendezvous().getQuestions().remove(question);
		
		this.questionRepository.delete(question);
	}

	// Other business methods
	
	public QuestionForm construct(Question question){
		
		Assert.notNull(question);
		
		QuestionForm questionForm = new QuestionForm();
		
		questionForm.setId(question.getId());
		questionForm.setRendezvousId(question.getRendezvous().getId());
		questionForm.setContent(question.getContent());
		
		return questionForm;
	}
	
	public Question reconstruct(final QuestionForm questionForm, final BindingResult binding) {

		Assert.notNull(questionForm);

		Question question;
		
		Rendezvous rendezvous = rendezvousService.findOne(questionForm.getRendezvousId());

		if (questionForm.getId() != 0)
			question = this.findOne(questionForm.getId());
		else
			question = this.create(rendezvous);

		question.setId(questionForm.getId());
		question.setRendezvous(rendezvous);
		question.setContent(questionForm.getContent());
		
		if (binding != null)
			this.validator.validate(rendezvous, binding);

		return question;
	}
	
	public void checkPrincipal(Question question){
		
		Rendezvous rendezvous = question.getRendezvous();
		User principal = userService.findByPrincipal();
		
		Assert.isTrue(principal.equals(rendezvous.getOrganiser()));	
	}

	public Collection<Question> findByRendezvousId(final int rendezvousId) {
		return this.questionRepository.findByRendezvousId(rendezvousId);
	}

	public Collection<Question> findByUserId(final int userId) {
		return this.questionRepository.findByRendezvousId(userId);
	}

}
