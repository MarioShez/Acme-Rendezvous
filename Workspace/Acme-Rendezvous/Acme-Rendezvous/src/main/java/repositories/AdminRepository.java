package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Admin;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Integer> {

	@Query("select a from Admin a where a.userAccount.id=?1")
	Admin findAdministratorByUserAccountId(int uA);

	// C-1
	@Query("select avg(u.organisedRendezvous.size), sqrt(sum(u.organisedRendezvous.size*u.organisedRendezvous.size)/count(u.organisedRendezvous.size)-(avg(u.organisedRendezvous.size)*avg(u.organisedRendezvous.size))) from User u")
	Object[] avgSqtrUser();

	// C-2
	@Query("select (select count(u) from User u where u.organisedRendezvous.size!=0)* 1.0/count(u) from User u where u.organisedRendezvous.size= 0) ")
	Double ratioUserRendezvous();

	// C-3
	@Query("select avg(r.attendants.size), sqrt(sum(r.attendants.size*r.attendants.size)/count(r.attendants.size)-(avg(r.attendants.size)*avg(r.attendants.size))) from Rendezvous r")
	Object[] avgSqtrUserPerRendezvous();

	// C-4
	@Query("select avg(u.rsvpdRendezvous.size), sqrt(sum(u.rsvpdRendezvous.size*u.rsvpdRendezvous.size)/count(u.rsvpdRendezvous.size)-(avg(u.rsvpdRendezvous.size)*avg(u.rsvpdRendezvous.size))) from User u")
	Object[] avgSqtrUser2();

	// C-5
	@Query("select r from Rendezvous r where r.attendants.size !=0 order by r.attendants.size desc")
	Object[] topRendezvous();

	// B-1
	@Query("select avg(r.announcements.size), sqrt(sum(r.announcements.size*r.announcements.size)/count(r.announcements.size)-(avg(r.announcements.size)*avg(r.announcements.size))) from Rendezvous r")
	Object[] avgSqtrAnnouncementPerRendezvous();

	// B-2
	@Query("select r from Rendezvous r where r.announcements.size> (select 0.75* avg(r.announcements.size) from Rendezvous r")
	Object[] rendezvousNumberAnnouncements();

	// B-3
	@Query("select r from Rendezvous r where r.linkedRendezvouses.size > (select avg(r.linkedRendezvouses.size)* 1.1 from Rendezvouses r)")
	Object[] rendezvousLinked();

	// A-1
	@Query("select (select count(q) from Question q where q.rendezvous.id= r.id) from Rendezvous r")
	Object[] avgSqtrQuestionsPerRendezvous();

	// A-2
	@Query("select (select count(a) from Answer a where a.question.rendezvous.id= r.id) from Rendezvous r")
	Object[] avgSqtrAnswersPerRendezvous();

	// A-3
	@Query("select avg(c.replies.size), stddev(c.replies.size) from Comment c")
	Object[] avgSqtRrepliesPerComment();

}
