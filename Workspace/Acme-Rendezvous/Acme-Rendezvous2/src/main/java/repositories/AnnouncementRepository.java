package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Announcement;

@Repository
public interface AnnouncementRepository extends JpaRepository<Announcement, Integer> {
	
	@Query("select a from Announcement a where a.rendezvous.id =?1")
	Collection<Announcement> findAnnouncementsByRendezvous(int rendezvousId);

//	@Query("select r.announcements from Rendezvous r join r.attendants a where a.id=?1 order by moment desc")
//	Collection<Announcement> findAnnouncementsByAttendants(int rendezvousId);

	
}
