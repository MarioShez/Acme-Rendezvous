
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.QuestionRepository;
import security.UserAccountService;
import domain.Question;
import domain.Rendezvous;

@Service
@Transactional
public class QuestionService {

	// Managed repository

	@Autowired
	private QuestionRepository	questionRepository;

	// Supporting services

	@Autowired
	private UserAccountService	userAccountService;

	@Autowired
	private UserService			userService;


	//	@Autowired
	//	private RendezvousesService rendezvousesService;
	//	
	// Constructors

	public QuestionService() {
		super();
	}

	// Simple CRUD methods

	public Question create() {
		final Question res = new Question();

		final Rendezvous rendezvous = new Rendezvous();

		final Question a = new Question();

		a.setRendezvous(rendezvous);

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

	public Question save(final Question question) {
		Assert.notNull(question);
		Question res;
		res = this.questionRepository.save(question);
		return res;
	}

	public void delete(final Question question) {
		Assert.notNull(question);
		Assert.isTrue(question.getId() != 0);
		Assert.isTrue(this.questionRepository.exists(question.getId()));
		this.questionRepository.delete(question);
	}

	// Other business methods

}
