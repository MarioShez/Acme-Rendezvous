
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.AnswerRepository;
import domain.Answer;
import domain.User;

@Service
@Transactional
public class AnswerService {

	// Managed repository

	@Autowired
	private AnswerRepository	answerRepository;

	// Supporting services

	@Autowired
	private UserService			userService;

	// Constructors

	public AnswerService() {
		super();
	}

	// Simple CRUD methods

	public Answer create() {
		final Answer res = new Answer();

		User u = new User();
		u = this.userService.findByPrincipal();
		Assert.notNull(u);

		final Answer a = new Answer();

		a.setUser(u);

		return res;
	}

	public Collection<Answer> findAll() {
		Collection<Answer> res;
		res = this.answerRepository.findAll();
		Assert.notNull(res);
		return res;
	}

	public Answer findOne(final int answerId) {
		Assert.isTrue(answerId != 0);
		Answer res;
		res = this.answerRepository.findOne(answerId);
		Assert.notNull(res);
		return res;
	}

	public Answer save(final Answer answer) {
		Assert.notNull(answer);
		Assert.isTrue(this.answerRepository.findByQuestionId(answer.getQuestion().getId()) != null);
		Answer res;
		res = this.answerRepository.save(answer);
		if(answer.getId() == 0){
			answer.getQuestion().getAnswers().add(res);
			answer.getUser().getAnswer().add(res);
		}
		return res;
	}

	public void delete(final Answer answer) {
		Assert.notNull(answer);
		Assert.isTrue(answer.getId() != 0);
		Assert.isTrue(this.answerRepository.exists(answer.getId()));
		answer.getUser().getAnswer().remove(answer);
		answer.getQuestion().getAnswers().remove(answer);
		this.answerRepository.delete(answer);
	}

	// Other business methods

	public Collection<Answer> findByQuestionId(final Integer id) {
		return this.answerRepository.findByQuestionId(id);
	}
	
	public Answer findByUserIdAndQuestionId(int userId, int questionId) {
		return this.answerRepository.findByUserIdAndQuestionId(userId, questionId);
	}

}
