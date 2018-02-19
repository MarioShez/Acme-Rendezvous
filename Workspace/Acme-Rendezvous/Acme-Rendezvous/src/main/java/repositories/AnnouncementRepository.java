package repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import domain.Announcement;

public interface AnnouncementRepository extends JpaRepository<Announcement, Integer> {

}
