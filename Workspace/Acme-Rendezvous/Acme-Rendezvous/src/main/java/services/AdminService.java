package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.AdminRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import domain.Admin;

@Service
@Transactional
public class AdminService {

	// Managed repository

	@Autowired
	private AdminRepository adminRepository;

	// Supporting services

	@Autowired
	private ActorService actorService;

	// Constructors

	public AdminService() {
		super();
	}

	// Simple CRUD methods

	public Admin create() {
		Admin res = new Admin();

		UserAccount userAccount = new UserAccount();
		Authority authority = new Authority();

		authority.setAuthority(Authority.ADMIN);
		userAccount.addAuthority(authority);
		res.setUserAccount(userAccount);

		return res;
	}

	public Collection<Admin> findAll() {
		Collection<Admin> res;
		res = this.adminRepository.findAll();
		Assert.notNull(res);
		return res;
	}

	public Admin findOne(int administratorId) {
		Assert.isTrue(administratorId != 0);
		Admin res;
		res = this.adminRepository.findOne(administratorId);
		Assert.notNull(res);
		return res;
	}

	public Admin save(Admin administrator) {
		Admin res;

		if (administrator.getId() == 0) {
			String pass = administrator.getUserAccount().getPassword();

			final Md5PasswordEncoder code = new Md5PasswordEncoder();

			pass = code.encodePassword(pass, null);

			administrator.getUserAccount().setPassword(pass);
		}

		res = this.adminRepository.save(administrator);
		return res;
	}

	// Ancillary methods
	public Admin findByPrincipal() {
		Admin res;
		UserAccount userAccount;
		userAccount = LoginService.getPrincipal();
		res = this.adminRepository.findAdministratorByUserAccountId(userAccount
				.getId());
		Assert.notNull(res);
		return res;
	}

	public void checkAuthority() {
		UserAccount userAccount;
		userAccount = LoginService.getPrincipal();
		Assert.notNull(userAccount);
		Collection<Authority> authority = userAccount.getAuthorities();
		Assert.notNull(authority);
		Authority res = new Authority();
		res.setAuthority("ADMIN");
		Assert.isTrue(authority.contains(res));
	}

}
