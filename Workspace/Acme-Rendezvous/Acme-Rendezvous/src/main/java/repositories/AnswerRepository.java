
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Answer;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, Integer> {

	@Query("select a from Answer a join a.question q where q.rendezvous.organiser.id = ?1")
	Collection<Answer> findByOrganiserId(int organiserId);

	@Query("select a from Answer a where a.question.id = ?1")
	Collection<Answer> findByQuestionId(int questionId);
	
	@Query("select a from Answer a where a.user.id = ?1 and a.question.id = ?2")
	Answer findByUserIdAndQuestionId(int userId, int rendezvousId);

}
