package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Admin;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Integer>{

	@Query("select a from Admin a where a.userAccount.id=?1")
	Admin findAdministratorByUserAccountId(int uA);
	
	//C-1
		@Query("select avg(u.organisedRendezvous.size), sqrt(sum(u.organisedRendezvous.size*u.organisedRendezvous.size)/count(u.organisedRendezvous.size)-(avg(u.organisedRendezvous.size)*avg(u.organisedRendezvous.size))) from User u")
		Object[] avgSqtrUser();
	//C-2
//		@Query("select cast(count(u) as float) /(select count(u) from User u)  from User u where u.reason is not null")
//		Double ratioTripsCancelled();
		
	//C-3
		@Query("select avg(r.attendants.size), sqrt(sum(r.attendants.size*r.attendants.size)/count(r.attendants.size)-(avg(r.attendants.size)*avg(r.attendants.size))) from Rendezvous r")
		Object[] avgSqtrUserPerRendezvous();
		
	//C-4
		@Query("select avg(u.rsvpdRendezvous.size), sqrt(sum(u.rsvpdRendezvous.size*u.rsvpdRendezvous.size)/count(u.rsvpdRendezvous.size)-(avg(u.rsvpdRendezvous.size)*avg(u.rsvpdRendezvous.size))) from User u")
		Object[] avgSqtrUser2();
		
	//C-5
		
		
	//B-1
		@Query("select avg(r.announcements.size), sqrt(sum(r.announcements.size*r.announcements.size)/count(r.announcements.size)-(avg(r.announcements.size)*avg(r.announcements.size))) from Rendezvous r")
		Object[] avgSqtrAnnouncementPerRendezvous();
}
