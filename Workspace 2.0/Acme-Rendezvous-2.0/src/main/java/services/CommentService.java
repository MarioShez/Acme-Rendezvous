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
	
	@Autowired
	private RendezvousService rendezvousService;

	// Constructors

	public CommentService() {
		super();
	}

	// Simple CRUD methods

	public Comment create(int rendezvousId) {
		userService.checkAuthority();
		Comment res = new Comment();
		Collection<Comment> replies = new ArrayList<Comment>();
		Rendezvous rendezvous = this.rendezvousService.findOne(rendezvousId);
		User u = userService.findByPrincipal();
		Assert.notNull(u);

		Date moment = new Date(System.currentTimeMillis()-1000);

		res.setMoment(moment);
		res.setUser(u);
		res.setReplies(replies);
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

	public Comment save(Comment comment) {
		
		userService.checkAuthority();
		
		User user = new User();
		user = this.userService.findByPrincipal();
		Rendezvous rendezvous;
		rendezvous = comment.getRendezvous();
		Assert.isTrue(user.getRsvpdRendezvouses().contains(rendezvous));
		
		Comment res;
		Assert.notNull(comment);
		
		comment.setUser(user);
		
		res = this.commentRepository.save(comment);
		
		
		if(comment.getId() == 0){
			Date moment;
			moment = new Date(System.currentTimeMillis()-1000);
			comment.setMoment(moment);
			comment.getRendezvous().getComments().add(res);
		}
		
		if(comment.getCommentParent() != null){
			this.actualizarParent(comment.getCommentParent(), res);
		}
		
		return res;
	}

	public void delete(Comment comment) {
		Assert.notNull(comment);
		Assert.isTrue(comment.getId() != 0);
		Assert.isTrue(this.commentRepository.findOne(comment.getId()) != null);
		this.adminService.checkAuthority();
		
		if (comment.getReplies().size() != 0){
			for (Comment c : comment.getReplies())
				this.delete(c);
		}
		this.commentRepository.delete(comment);
	}
	
	
	//Other bussines methods
	
	public void deleteAll(Collection<Comment> comments){
		//Assert.notNull(comments);
		this.adminService.checkAuthority();
		for(Comment c : comments)
			this.commentRepository.delete(c);
	}
	
	private void actualizarParent(Comment commentParent, Comment commentSon){
		Collection<Comment> replies = new ArrayList<Comment>();
		if(commentParent.getReplies() != null){
			replies.addAll(commentParent.getReplies());
		}
		replies.add(commentSon);
		commentParent.setReplies(replies);
		this.commentRepository.save(commentParent);
	}
	
	public Collection<Comment> commentsOfThisRendezvouseWithCommentNull(final int rendezvouseId) {
		Collection<Comment> commentsOfThisRendezvouse;

		commentsOfThisRendezvouse = this.commentRepository.commentsOfThisRendezvouseWithCommentNull(rendezvouseId);

		return commentsOfThisRendezvouse;
	}
	
	public void flush() {
		this.commentRepository.flush();
	}
	
	
	public Comment reconstruct(CommentForm commentForm, BindingResult binding) {
		
		Comment res= this.create(commentForm.getRendezvousId());
		
		if(commentForm.getCommentParentId() != null){
			Comment commentParent = this.findOne(commentForm.getCommentParentId());
			res.setCommentParent(commentParent);
		}
		
		Date moment = new Date(System.currentTimeMillis()-1);
		res.setMoment(moment);
		res.setPicture(commentForm.getPicture());
		res.setText(commentForm.getText());
		res.setRendezvous(rendezvousService.findOne(commentForm.getRendezvousId()));
		
		if(binding != null){
			validator.validate(res, binding);
		}
		
		return res;
	}
	
	public CommentForm construct(Comment comment) {
		CommentForm res= new CommentForm();
		Rendezvous rendezvous = comment.getRendezvous();
		Comment commentParent = comment.getCommentParent();
		
		res.setPicture(comment.getPicture());
		res.setText(comment.getText());
		res.setId(comment.getId());
		res.setRendezvousId(rendezvous.getId());
		if(commentParent != null){
			res.setCommentParentId(commentParent.getId());
		}
		
		return res;
	}
	
	public void checkPrincipalForm(final CommentForm commentForm) {

		Assert.notNull(commentForm);
		
		Comment comment = reconstruct(commentForm, null);
		final User principal = this.userService.findByPrincipal();
		
		Assert.isTrue(comment.getUser().equals(principal));
	}
	
}
