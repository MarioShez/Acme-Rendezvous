
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Answer;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, Integer> {

<<<<<<< HEAD
	@Query("select a from Answer a join a.rendezvous r where r.organiser.id = ?1")
=======
	@Query("select a from Answer a join a.question q where q.rendezvous.organiser.id = ?1")
>>>>>>> 4fccbc61e09601464098103f2fe3c774525b9f70
	Collection<Answer> findByOrganiserId(int organiserId);

}
