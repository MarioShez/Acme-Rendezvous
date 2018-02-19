package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Rendezvous;

@Repository
public interface RendezvousRepository extends JpaRepository<Rendezvous, Integer> {

	@Query("select r from Rendezvous r where r.moment > current_timestamp")
	Collection<Rendezvous> findFutureMoment();
	
	@Query("select r from Rendezvous r where r.adult = false and r.moment > current_timestamp")
	Collection<Rendezvous> findFutureMomentAndNotAdult();

	@Query("select r from Rendezvous r where r.ornaniser = ?1")
	Collection<Rendezvous> findByOrganiserId(int organiserId);
	

}
