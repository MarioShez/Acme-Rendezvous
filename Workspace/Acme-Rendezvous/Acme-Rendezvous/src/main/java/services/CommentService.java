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
	private CommentRepository	commentRepository;

	@Autowired
	private UserService			userService;

	@Autowired
	private Validator			validator;

	@Autowired
	private AdminService		adminService;


	// Constructors

	public CommentService() {
		super();
	}

	// Simple CRUD methods

	public Comment create() {
		this.userService.checkAuthority();
		final Comment res = new Comment();
		final Collection<Comment> replies = new ArrayList<Comment>();
		final Rendezvous rendezvous = new Rendezvous();
		final User u = new User();
		final Comment parent = new Comment();
		Assert.notNull(u);

		final Date moment = new Date(System.currentTimeMillis() - 1000);

		res.setMoment(moment);
		res.setUser(u);
		res.setCommentParent(parent);
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

	public Comment findOne(final int comment) {
		Assert.isTrue(comment != 0);
		Comment res;
		res = this.commentRepository.findOne(comment);
		Assert.notNull(res);
		return res;
	}

	public Comment save(final Comment comment) {

		this.userService.checkAuthority();

		User user = new User();
		user = this.userService.findByPrincipal();
		Rendezvous rendezvous;
		rendezvous = comment.getRendezvous();
		Assert.isTrue(user.getRsvpdRendezvouses().contains(rendezvous));

		Comment res;
		Assert.notNull(comment);

		comment.setUser(user);

		res = this.commentRepository.save(comment);

		//		if(comment.getId() == 0){
		//			Date moment;
		//			moment = new Date(System.currentTimeMillis()-1000);
		//			comment.setMoment(moment);
		//		}

		if (comment.getCommentParent() != null)
			this.actualizarParent(comment.getCommentParent(), res);

		return res;
	}

	public void delete(final Comment comment) {
		Assert.notNull(comment);
		Assert.isTrue(comment.getId() != 0);
		Assert.isTrue(this.commentRepository.findOne(comment.getId()) != null);
		this.adminService.checkAuthority();

		if (comment.getReplies().size() != 0)
			for (final Comment c : comment.getReplies())
				this.delete(c);
		this.commentRepository.delete(comment);
	}

	//Other bussines methods

	public void deleteAll(final Collection<Comment> comments) {
		//Assert.notNull(comments);
		this.adminService.checkAuthority();
		for (final Comment c : comments)
			this.commentRepository.delete(c);
	}

	private void actualizarParent(final Comment commentParent, final Comment commentSon) {
		final Collection<Comment> replies = new ArrayList<Comment>();
		if (commentParent.getReplies() != null)
			replies.addAll(commentParent.getReplies());
		replies.add(commentSon);
		commentParent.setReplies(replies);
		this.commentRepository.save(commentParent);
	}

	public Collection<Comment> commentsOfThisRendezvouseWithCommentNull(final int rendezvouseId) {
		Collection<Comment> commentsOfThisRendezvouse;

		commentsOfThisRendezvouse = this.commentRepository.commentsOfThisRendezvouseWithCommentNull(rendezvouseId);

		return commentsOfThisRendezvouse;
	}

	public Comment reconstruct(final CommentForm commentForm, final BindingResult binding) {
		final Comment res = new Comment();

		final Collection<Comment> replies = new ArrayList<Comment>();
		final User user = new User();
		final Rendezvous rendezvous = new Rendezvous();

		final Date moment = new Date(System.currentTimeMillis() - 1);

		res.setMoment(moment);
		res.setPicture(commentForm.getPicture());
		res.setText(commentForm.getText());

		res.setReplies(replies);
		res.setUser(user);
		res.setRendezvous(rendezvous);

		this.validator.validate(res, binding);

		return res;
	}

	public CommentForm reconstruct(final Comment comment) {
		final CommentForm res = new CommentForm();

		res.setPicture(comment.getPicture());
		res.setText(comment.getText());
		res.setId(comment.getId());

		return res;
	}

}