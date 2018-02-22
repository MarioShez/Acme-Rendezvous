package services;

import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.CommentRepository;
import domain.Comment;
import domain.Rendezvous;
import domain.User;

@Service
@Transactional
public class CommentService {

	// Managed repository

	@Autowired
	private CommentRepository commentRepository;
	
	private UserService userService;

	// Constructors

	public CommentService() {
		super();
	}

	// Simple CRUD methods

	public Comment create() {
		Comment res = new Comment();

		User u = new User();
		u = userService.findByPrincipal();
		Assert.notNull(u);

		Rendezvous rendezvous = new Rendezvous();

		Date d = new Date();

		res.setMoment(d);
		res.setUser(u);
		res.setRendezvous(rendezvous);
		return res;
	}

	public Collection<Comment> findAll() {
		Collection<Comment> res;
		res = this.commentRepository.findAll();
		Assert.notNull(res);
		return res;
	}

	public Comment findOne(int comment) {
		Assert.isTrue(comment != 0);
		Comment res;
		res = this.commentRepository.findOne(comment);
		Assert.notNull(res);
		return res;
	}

	public Comment save(Comment coment) {
		Assert.notNull(coment);

		Comment res;
		res = this.commentRepository.save(coment);
		Date fechaActual = new Date();
		res.setMoment(fechaActual);
		return res;
	}

	public void delete(Comment comment) {
		Assert.notNull(comment);
		Assert.isTrue(comment.getId() != 0);
		Assert.isTrue(this.commentRepository.exists(comment.getId()));
		this.commentRepository.delete(comment);
	}
	
	// pasar a servicio de Rendezvous
//	public Collection<Rendezvous> findRsvpdRendezvousByUserId(int attendantsId){
//		Assert.notNull(attendantsId);
//		Collection<Rendezvous> rendezvous;
//		rendezvous = this.commentRepository.findRsvpdRendezvousByUserId(attendantsId);
//		Assert.notNull(rendezvous);
//		return rendezvous;
//			
//	}

}
