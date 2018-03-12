
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Manager;

@Repository
public interface ManagerRepository extends JpaRepository<Manager, Integer> {

	@Query("select m from Manager m where m.userAccount.id = ?1")
	Manager findManagerByUserAccountId(int uA);

	@Query("select m from Manager m where m.services.size > (select avg(p.services.size) from Manager p)")
	Collection<Manager> managersMoreServicesThanAvg();

	@Query("select m, m.services.size from Manager m join m.services s where s.cancelled = true order by m.services.size desc")
	Collection<Manager> managersMoreServicesCancelled();
}
