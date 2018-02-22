
package services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import domain.Admin;

@Service
@Transactional
public class AdminService {

	@Autowired
	private ActorService	actorService;


	//Ancillary methods
	public Admin findByPrincipal() {
		try {
			return (Admin) this.actorService.findByPrincipal();
		} catch (final Exception e) {
			return null;
		}
	}

}
