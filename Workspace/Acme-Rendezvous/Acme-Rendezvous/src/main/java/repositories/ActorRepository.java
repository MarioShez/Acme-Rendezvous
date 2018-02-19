
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Actor;
import domain.User;

@Repository
public interface ActorRepository extends JpaRepository<Actor, Integer> {
	
	@Query("select a from Actor a where a.userAccount.id = ?1")
	Actor findActorByUserAccountId(int uA);
	
//	@Query("select a from Rendezvous r join r.announcement a where r.id = ?1")
//	Actor findAnnouncementByRendezvous(int idRendezvous);
	
	@Query("select u.rsvpdRendezvous from User u")
	Collection<User> findUserRsvpdRendezvous();
	
}
