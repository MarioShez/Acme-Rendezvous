
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.ActorRepository;
import security.LoginService;
import security.UserAccount;
import security.UserAccountService;
import domain.Actor;
import domain.User;

@Service
@Transactional
public class ActorService {

	// Managed repository

	@Autowired
	private ActorRepository		actorRepository;

	// Supporting services

	@Autowired
	private UserAccountService	userAccountService;

	@Autowired
	private UserService			userService;


	//	@Autowired
	//	private RendezvousesService rendezvousesService;
	//	
	// Constructors

	public ActorService() {
		super();
	}

	// Simple CRUD methods

	public Collection<Actor> findAll() {
		Collection<Actor> res;
		res = this.actorRepository.findAll();
		Assert.notNull(res);
		return res;
	}

	public Actor findOne(final int actorId) {
		Assert.isTrue(actorId != 0);
		Actor res;
		res = this.actorRepository.findOne(actorId);
		Assert.notNull(res);
		return res;
	}

	public Actor save(final Actor actor) {
		Assert.notNull(actor);
		Actor res;
		res = this.actorRepository.save(actor);
		return res;
	}

	//	public void delete(Actor actor) {
	//		Assert.notNull(actor);
	//		Assert.isTrue(actor.getId() != 0);
	//		Assert.isTrue(this.actorRepository.exists(actor.getId()));
	//		this.actorRepository.delete(actor);
	//	}

	// Other business methods

	public void checkUserLogin() {
		final UserAccount userAccount = LoginService.getPrincipal();
		Assert.notNull(userAccount);
		final Actor actor = this.findByPrincipal();
		Assert.notNull(actor);
	}

	public Actor findByPrincipal() {
		Actor res;
		UserAccount userAccount;
		userAccount = LoginService.getPrincipal();
		res = this.actorRepository.findActorByUserAccountId(userAccount.getId());
		Assert.notNull(res);
		return res;
	}

	public boolean checkAuthority() {
		boolean res = false;
		UserAccount userAccount;
		userAccount = LoginService.getPrincipal();
		if (userAccount != null)
			res = true;
		return res;
	}

	public UserAccount findByUserAccount(final Actor actor) {
		Assert.notNull(actor);
		UserAccount res;
		res = this.userAccountService.findByActor(actor);
		Assert.notNull(res);
		return res;
	}

	// 4.2

	public Collection<User> findAllUser() {
		Collection<User> res;
		res = this.userService.findAll();
		return res;
	}

	// 20.1

	public Collection<User> findUserRsvpdRendezvous() {
		Collection<User> res;
		res = this.actorRepository.findUserRsvpdRendezvous();
		Assert.notNull(res);

		return res;
	}

}
