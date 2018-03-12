
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

import repositories.ManagerRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import domain.Actor;
import domain.Manager;
import forms.UserForm;

@Service
@Transactional
public class ManagerService {

	// Managed repository

	@Autowired
	private ManagerRepository	managerRepository;


	// Constructors

	public ManagerService() {
		super();
	}


	// Supporting services

	@Autowired
	private Validator	validator;


	// Simple CRUD methods

	public Manager create() {
		final Manager res = new Manager();

		final UserAccount userAccount = new UserAccount();
		final Authority authority = new Authority();

		authority.setAuthority(Authority.MANAGER);
		userAccount.addAuthority(authority);
		res.setUserAccount(userAccount);

		return res;
	}

	public Collection<Manager> findAll() {
		Collection<Manager> res;
		res = this.managerRepository.findAll();
		Assert.notNull(res);
		return res;
	}

	public Manager findOne(final int managerId) {
		Assert.isTrue(managerId != 0);
		Manager res;
		res = this.managerRepository.findOne(managerId);
		Assert.notNull(res);
		return res;
	}

	public Actor save(final Manager manager) {
		Manager res;

		if (manager.getId() == 0) {
			String pass = manager.getUserAccount().getPassword();

			final Md5PasswordEncoder code = new Md5PasswordEncoder();

			pass = code.encodePassword(pass, null);

			manager.getUserAccount().setPassword(pass);
		}
		res = this.managerRepository.save(manager);
		return res;
	}

	// Other businesss methods

	public Manager findByPrincipal() {
		Manager res;
		UserAccount userAccount;
		userAccount = LoginService.getPrincipal();
		if (userAccount == null)
			res = null;
		else
			res = this.managerRepository.findManagerByUserAccountId(userAccount.getId());
		return res;
	}

	public boolean checkAuthority() {
		UserAccount userAccount;
		userAccount = LoginService.getPrincipal();
		Assert.notNull(userAccount);
		final Collection<Authority> authority = userAccount.getAuthorities();
		Assert.notNull(authority);
		final Authority res = new Authority();
		res.setAuthority("MANAGER");
		if (authority.contains(res))
			return true;
		else
			return false;
	}

	public Manager reconstruct(final UserForm managerForm, final BindingResult binding) {
		final Manager res = new Manager();

		final UserAccount userAccount = managerForm.getUserAccount();

		final Authority authority = new Authority();
		authority.setAuthority(Authority.MANAGER);
		userAccount.addAuthority(authority);

		res.setUserAccount(userAccount);
		res.setName(managerForm.getName());
		res.setSurname(managerForm.getSurname());
		res.setEmail(managerForm.getEmail());
		res.setPhone(managerForm.getPhone());
		res.setAddress(managerForm.getAddress());
		res.setBirth(managerForm.getBirth());

		this.validator.validate(res, binding);

		return res;
	}

	public Collection<Manager> managersMoreServicesThanAvg() {
		final Collection<Manager> result = this.managerRepository.managersMoreServicesThanAvg();
		return result;
	}

	public Collection<Manager> managersMoreServicesCancelled() {
		final Collection<Manager> result = this.managerRepository.managersMoreServicesCancelled();
		return result;
	}
}
