package services;

import java.util.ArrayList;
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
	private ManagerRepository managerRepository;

	// Constructors

	public ManagerService() {
		super();
	}

	// Supporting services

	@Autowired
	private Validator validator;

	// Simple CRUD methods

	public Manager create() {
		Manager res = new Manager();

		UserAccount userAccount = new UserAccount();
		Authority authority = new Authority();

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

	public Manager findOne(int managerId) {
		Assert.isTrue(managerId != 0);
		Manager res;
		res = this.managerRepository.findOne(managerId);
		Assert.notNull(res);
		return res;
	}

	public Actor save(Manager manager) {
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

	public Manager findByPrincipal() {
		Manager res;
		UserAccount userAccount;
		userAccount = LoginService.getPrincipal();
		if (userAccount == null) {
			res = null;
		} else {
			res = this.managerRepository.findManagerByUserAccountId(userAccount
					.getId());
		}
		return res;
	}

	public void checkAuthority() {
		UserAccount userAccount;
		userAccount = LoginService.getPrincipal();
		Assert.notNull(userAccount);
		Collection<Authority> authority = userAccount.getAuthorities();
		Assert.notNull(authority);
		Authority res = new Authority();
		res.setAuthority("MANAGER");
		Assert.isTrue(authority.contains(res));
	}

	public Manager reconstruct(UserForm managerForm, BindingResult binding) {
		Manager res = new Manager();

		UserAccount userAccount = managerForm.getUserAccount();

		Authority authority = new Authority();
		authority.setAuthority(Authority.MANAGER);
		userAccount.addAuthority(authority);

		res.setUserAccount(userAccount);
		res.setName(managerForm.getName());
		res.setSurname(managerForm.getSurname());
		res.setEmail(managerForm.getEmail());
		res.setPhone(managerForm.getPhone());
		res.setAddress(managerForm.getAddress());
		res.setBirth(managerForm.getBirth());

		validator.validate(res, binding);

		return res;
	}

}
