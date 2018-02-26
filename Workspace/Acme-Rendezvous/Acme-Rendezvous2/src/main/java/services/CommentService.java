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
	private Validator validator;
	
	@Autowired
	private AdminService adminService;
	

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


		Date moment = new Date(System.currentTimeMillis()-1);
		Comment parent= new Comment();
		Collection<Comment> replies = new ArrayList<Comment>();

		//res.setMoment(moment);
		res.setUser(u);
		res.setCommentParent(parent);
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
		
		Date moment;
		User userConnected;

		moment = new Date();
		userConnected = this.userService.findByPrincipal();
		
		Assert.notNull(coment);
		Assert.isTrue(coment.getRendezvous().getAttendants().contains(userConnected));

		Comment res;
		
		moment = new Date(System.currentTimeMillis() - 1000);
		
		this.userService.checkAuthority();
		Assert.isTrue(coment.getUser().equals(userConnected));
		Assert.isTrue(coment.getId() == 0);
		
		coment.setMoment(moment);
		res = this.commentRepository.save(coment);
		return res;
	}

	public void delete(Comment comment) {
		Assert.notNull(comment);
		Assert.isTrue(comment.getId() != 0);
		
		Assert.isTrue(this.commentRepository.findOne(comment.getId()) != null);
		
		this.adminService.checkAuthority();
		
		if (comment.getReplies().size() != 0)
			for (final Comment c : comment.getReplies())
				this.delete(c);
		
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
		
		Collection<Comment> replies = new ArrayList<Comment>();
		User user = new User();
		Rendezvous rendezvous = new Rendezvous();
		
		Date moment = new Date(System.currentTimeMillis()-1);
		
		res.setMoment(moment);
		res.setPicture(commentForm.getPicture());
		res.setText(commentForm.getText());
		
		res.setReplies(replies);
		res.setUser(user);
		res.setRendezvous(rendezvous);
		
		validator.validate(res, binding);
		
		
		return res;
	}
	
	public CommentForm reconstruct(Comment comment) {
		CommentForm res= new CommentForm();
		
		res.setPicture(comment.getPicture());
		res.setText(comment.getText());
		res.setId(comment.getId());
		
		return res;
	}
	
}
