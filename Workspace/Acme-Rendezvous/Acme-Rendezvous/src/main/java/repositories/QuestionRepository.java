
package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import domain.domain.Question;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Integer> {

}
