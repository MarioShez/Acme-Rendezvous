package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Admin;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Integer> {

	@Query("select a from Admin a where a.userAccount.id=?1")
	Admin findAdministratorByUserAccountId(int uA);

	// C-1
	@Query("select avg(u.organisedRendezvouses.size), sqrt(sum(u.organisedRendezvouses.size*u.organisedRendezvouses.size)/count(u.organisedRendezvouses.size)-(avg(u.organisedRendezvouses.size)*avg(u.organisedRendezvouses.size))) from User u")
	Object[] avgSqtrUser();

//	// C-2
//	@Query("select (select count(u) from User u where u.organisedRendezvous.size!=0)* 1.0/count(u) from User u where u.organisedRendezvous.size= 0) ")
//	Double ratioUserRendezvous();

	// C-3
	@Query("select avg(r.attendants.size), sqrt(sum(r.attendants.size*r.attendants.size)/count(r.attendants.size)-(avg(r.attendants.size)*avg(r.attendants.size))) from Rendezvous r")
	Object[] avgSqtrUserPerRendezvous();

	// C-4
	@Query("select avg(u.rsvpdRendezvouses.size), sqrt(sum(u.rsvpdRendezvouses.size*u.rsvpdRendezvouses.size)/count(u.rsvpdRendezvouses.size)-(avg(u.rsvpdRendezvouses.size)*avg(u.rsvpdRendezvouses.size))) from User u")
	Object[] avgSqtrUser2();

	// C-5
	@Query("select r from Rendezvous r where r.attendants.size !=0 order by r.attendants.size desc")
	Collection<Object> topRendezvous();

	// B-1
	@Query("select avg(r.announcements.size), sqrt(sum(r.announcements.size*r.announcements.size)/count(r.announcements.size)-(avg(r.announcements.size)*avg(r.announcements.size))) from Rendezvous r")
	Object[] avgSqtrAnnouncementPerRendezvous();

	// B-2
//	@Query("select r from Rendezvous r where r.announcements.size> (select 0.75* avg(r.announcements.size) from Rendezvous r")
//	Object[] rendezvousNumberAnnouncements();

//	// B-3
//	@Query("select r from Rendezvous r where r.linkedRendezvouses.size > (select avg(r.linkedRendezvouses.size)* 1.1 from Rendezvouses r)")
//	Object[] rendezvousLinked();

	// B-2
	@Query("select r from Rendezvous r where r.announcements.size> (select 0.75* avg(r.announcements.size) from Rendezvous r)")
	Collection<Object> rendezvousNumberAnnouncements();

	// B-3
	@Query("select r from Rendezvous r where r.linkedRendezvouses.size > (select avg(r.linkedRendezvouses.size)* 1.1 from Rendezvous r)")
	Collection<Object> rendezvousLinked();

	// A-1
	@Query("select avg(r.questions.size), sqrt(sum(r.questions.size*r.questions.size)/count(r.questions.size)-(avg(r.questions.size)*avg(r.questions.size))) from Rendezvous r")
	Object[] avgSqtrQuestionsPerRendezvous();

	// A-2
	@Query("select (select count(a) from Answer a where a.question.rendezvous.id= r.id) from Rendezvous r")
	Object[] avgSqtrAnswersPerRendezvous();

	// A-3
	@Query("select avg(c.replies.size), sqrt(sum(c.replies.size*c.replies.size)/count(c.replies.size)-(avg(c.replies.size)*avg(c.replies.size))) from Comment c")
	Object[] avgSqtRrepliesPerComment();

}