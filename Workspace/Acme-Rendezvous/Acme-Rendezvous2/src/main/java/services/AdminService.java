package services;

<<<<<<< HEAD
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
=======
import org.springframework.beans.factory.annotation.Autowired;
>>>>>>> a0a91cf2ced56cc7a8b0e232781a748438960939
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.AdminRepository;
<<<<<<< HEAD
import security.Authority;
import security.LoginService;
import security.UserAccount;
import domain.Admin;


=======
import security.LoginService;
import security.UserAccount;
import domain.Admin;
>>>>>>> a0a91cf2ced56cc7a8b0e232781a748438960939

@Service
@Transactional
public class AdminService {

	// Managed repository

	@Autowired
	private AdminRepository adminRepository;

	// Supporting services


	// Constructors

	public AdminService() {
		super();
	}

	// Simple CRUD methods

<<<<<<< HEAD
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
=======
//	public Admin create() {
//		Admin res = new Admin();
//
//		UserAccount userAccount = new UserAccount();
//		Authority authority = new Authority();
//
//		authority.setAuthority(Authority.ADMIN);
//		userAccount.addAuthority(authority);
//		res.setUserAccount(userAccount);
//
//		return res;
//	}
//
//	public Collection<Admin> findAll() {
//		Collection<Admin> res;
//		res = this.adminRepository.findAll();
//		Assert.notNull(res);
//		return res;
//	}
//
//	public Admin findOne(int administratorId) {
//		Assert.isTrue(administratorId != 0);
//		Admin res;
//		res = this.adminRepository.findOne(administratorId);
//		Assert.notNull(res);
//		return res;
//	}
//
//	public Admin save(Admin administrator) {
//		Admin res;
//
//		if (administrator.getId() == 0) {
//			String pass = administrator.getUserAccount().getPassword();
//
//			final Md5PasswordEncoder code = new Md5PasswordEncoder();
//
//			pass = code.encodePassword(pass, null);
//
//			administrator.getUserAccount().setPassword(pass);
//		}
//
//		res = this.adminRepository.save(administrator);
//		return res;
//	}
//
//	// Ancillary methods
>>>>>>> a0a91cf2ced56cc7a8b0e232781a748438960939
	public Admin findByPrincipal() {
		Admin res;
		UserAccount userAccount;
		userAccount = LoginService.getPrincipal();
		res = this.adminRepository.findAdministratorByUserAccountId(userAccount
				.getId());
		Assert.notNull(res);
		return res;
	}
<<<<<<< HEAD

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

	// C-1
	public Object[] avgSqtrUser() {
		Object[] res;
		res = this.adminRepository.avgSqtrUser();
		return res;
	}

=======
//
//	public void checkAuthority() {
//		UserAccount userAccount;
//		userAccount = LoginService.getPrincipal();
//		Assert.notNull(userAccount);
//		Collection<Authority> authority = userAccount.getAuthorities();
//		Assert.notNull(authority);
//		Authority res = new Authority();
//		res.setAuthority("ADMIN");
//		Assert.isTrue(authority.contains(res));
//	}
//
//	// C-1
//	public Object[] avgSqtrUser() {
//		Object[] res;
//		res = this.adminRepository.avgSqtrUser();
//		return res;
//	}
//
>>>>>>> a0a91cf2ced56cc7a8b0e232781a748438960939
//	// C-2
//	public Double ratioUserRendezvous() {
//		Double res;
//		res = this.adminRepository.ratioUserRendezvous();
//		return res;
//	}
//
	// C-3
	public Object[] avgSqtrUserPerRendezvous() {
		Object[] res;
		res = this.adminRepository.avgSqtrUserPerRendezvous();
		return res;
	}

	// C-4
	public Object[] avgSqtrUser2() {
		Object[] res;
		res = this.adminRepository.avgSqtrUser2();
		return res;
	}

	// C-5
	public Collection<Object> topRendezvous() {
		Collection<Object> res;
		res = this.adminRepository.topRendezvous();
		return res;
	}

	// B-1
	public Object[] avgSqtrAnnouncementPerRendezvous() {
		Object[] res;
		res = this.adminRepository.avgSqtrAnnouncementPerRendezvous();
		return res;
	}

//	// B-2
//	public Object[] rendezvousNumberAnnouncements() {
//		Object[] res;
//		res = this.adminRepository.rendezvousNumberAnnouncements();
//		return res;
//	}
//
//	// B-3
//	public Object[] rendezvousLinked() {
//		Object[] res;
//		res = this.adminRepository.rendezvousLinked();
//		return res;
//	}

	// A-1
	public Collection<Object> avgSqtrQuestionsPerRendezvous() {
		Collection<Object> res;
		res = this.adminRepository.avgSqtrQuestionsPerRendezvous();
		return res;
	}

	// A-2
	public Object[] avgSqtrAnswersPerRendezvous() {
		Object[] res;
		res = this.adminRepository.avgSqtrAnswersPerRendezvous();
		return res;
	}

	// A-3
	public Object[] avgSqtRrepliesPerComment() {
		Object[] res;
		res = this.adminRepository.avgSqtRrepliesPerComment();
		return res;
	}

}
