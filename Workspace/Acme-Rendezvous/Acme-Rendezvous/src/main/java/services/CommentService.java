package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

import repositories.CommentRepository;
import domain.Comment;
import domain.Rendezvous;
import domain.User;
import forms.CommentForm;

@Service
@Transactional
public class CommentService {

	// Managed repository

	@Autowired
	private CommentRepository commentRepository;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private CommentForm commentForm;
	
	private Validator validator;
	

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

		Date moment = new Date(System.currentTimeMillis()-1);
		Comment parent= new Comment();
		Collection<Comment> replies = new ArrayList<Comment>();

		res.setMoment(moment);
		res.setUser(u);
		res.setRendezvous(rendezvous);
//		res.setCommentParent(parent);
		res.setReplies(replies);
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

	//Other bussines methods
	
	public Comment reconstruct(CommentForm commentForm, BindingResult binding) {
		Comment res= new Comment();
		
		Date moment = new Date(System.currentTimeMillis()-1);
		
		res.setMoment(moment);
		res.setPicture(commentForm.getPicture());
		res.setText(commentForm.getText());
		
		validator.validate(res, binding);
		
		
		return res;

	}
}
