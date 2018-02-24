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

import repositories.UserRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import domain.Actor;
import domain.Answer;
import domain.Rendezvous;
import domain.User;
import forms.UserForm;

@Service
@Transactional
public class UserService {
	
	// Managed repository
	
	@Autowired
	private UserRepository	userRepository;
	
	// Supporting services
	
	@Autowired
	private Validator validator;
	
	// Constructors
	
	public UserService() {
		super();
	}
	
	
	// Simple CRUD methods
	
	public User create() {
		User res = new User();
		
		UserAccount userAccount = new UserAccount();
		Authority authority = new Authority();
		Collection<Answer> answer = new ArrayList<Answer>();
		Collection<Rendezvous> organisedrendezvous = new ArrayList<Rendezvous>();
		Collection<Rendezvous> rsvpdRendezvous = new ArrayList<Rendezvous>();
		
		authority.setAuthority(Authority.USER);
		userAccount.addAuthority(authority);
		res.setUserAccount(userAccount);
		res.setAnswer(answer);
		res.setOrganisedRendezvous(organisedrendezvous);
		res.setRsvpdRendezvous(rsvpdRendezvous);
		
		return res;
	}
	
	
	public Collection<User> findAll() {
		Collection<User> res;
		res = this.userRepository.findAll();
		Assert.notNull(res);
		return res;
	}

	public User findOne(int userId) {
		Assert.isTrue(userId != 0);
		User res;
		res = this.userRepository.findOne(userId);
		Assert.notNull(res);
		return res;
	}
	
	public Actor save(User user) {
		User res;
		
		if (user.getId() == 0) {
			String pass = user.getUserAccount().getPassword();
			
			final Md5PasswordEncoder code = new Md5PasswordEncoder();
			
			pass = code.encodePassword(pass, null);
			
			user.getUserAccount().setPassword(pass);
		}
		res = this.userRepository.save(user);
		return res;
	}
	
//	public void delete(User user) {
//		Assert.notNull(user);
//		Assert.isTrue(user.getId() != 0);
//		Assert.isTrue(this.userRepository.exists(user.getId()));
//		this.actorRepository.delete(user);
//	}
	
	// Other business methods

	public User findByPrincipal() {
		User res;
		UserAccount userAccount;
		userAccount = LoginService.getPrincipal();
		res = this.userRepository.findUserByUserAccountId(userAccount.getId());
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
		res.setAuthority("USER");
		Assert.isTrue(authority.contains(res));
	}
	
	// 4.3
	
	public Collection<User> findAttendsByRendezvousId(int rendezvousId){
		Collection<User> res;
		res = userRepository.findAttendsByRendezvousId(rendezvousId);
		Assert.notNull(res);
		
		return res;
	}
	
	// 4.3
	
	public User findOrganiserByRendezvousId(int rendezvousId){
		User res;
		res = userRepository.findOrganiserByRendezvousId(rendezvousId);
		Assert.notNull(res);
		
		return res;
	}


	public User reconstruct(UserForm userForm, BindingResult binding) {
		User res = new User();
		
		Collection<Answer> answers = new ArrayList<Answer>();
		Collection<Rendezvous> organisedRendezvous = new ArrayList<Rendezvous>();
		Collection<Rendezvous> rsvpdRendezvous = new ArrayList<Rendezvous>();
		
		UserAccount userAccount = userForm.getUserAccount();
		
		Authority authority = new Authority();
		authority.setAuthority(Authority.USER);
		userAccount.addAuthority(authority);

		res.setUserAccount(userAccount);
		res.setName(userForm.getName());
		res.setSurname(userForm.getSurname());
		res.setEmail(userForm.getEmail());
		res.setPhone(userForm.getPhone());
		res.setAddress(userForm.getAddress());
		res.setBirth(userForm.getBirth());
		
		res.setAnswer(answers);
		res.setRsvpdRendezvous(rsvpdRendezvous);
		res.setOrganisedRendezvous(organisedRendezvous);
		
		validator.validate(res, binding);
		
		return res;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
