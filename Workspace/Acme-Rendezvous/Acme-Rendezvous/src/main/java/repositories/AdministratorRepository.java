package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import domain.Admin;

@Repository
public interface AdministratorRepository extends JpaRepository<Admin, Integer>{
	

}
