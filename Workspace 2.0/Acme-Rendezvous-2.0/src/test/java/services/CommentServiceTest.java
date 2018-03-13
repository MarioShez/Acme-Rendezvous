package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import utilities.AbstractTest;
import domain.Comment;
import domain.Rendezvous;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/junit.xml" })
@Transactional
public class CommentServiceTest extends AbstractTest {

	// Supporting services ------------------------------------------

	@Autowired
	CommentService commentService;

	@Autowired
	RendezvousService rendezvousService;

	@SuppressWarnings("unchecked")
	@Test
	public void driverCreateAndSaveComment() {

		final Collection<Comment> listarComment = this
				.createCommentsForTesting();
		final Iterator<Comment> iterador = listarComment.iterator();

		final Object testingData[][] = {

		{
				// usuario "user1" crea un comment para un rendezvous
				// "rendezvous1" al que asiste
				null, "test create", "https://testPrueba.com", null, null,
				"rendezvous1", "user1", null }

		};

		for (int i = 0; i < testingData.length; i++) {

			this.templateCreateAndSave((Date) testingData[i][0],
					(String) testingData[i][1], (String) testingData[i][2],
					(List<Comment>) testingData[i][3],
					(Comment) testingData[i][4],
					super.getEntityId((String) testingData[i][5]),
					(String) testingData[i][6], (Class<?>) testingData[i][7]);
		}

	}

	private void templateCreateAndSave(final Date moment, final String text,
			final String picture, final List<Comment> replies,
			final Comment commentParent, final int rendezvousId,
			final String userName, final Class<?> expected) {
		final Rendezvous rendezvousComment;
		Comment comment;

		Class<?> caught = null;

		try {
			super.authenticate(userName);
			rendezvousComment = this.rendezvousService.findOne(rendezvousId);
			comment = this.commentService.create();
			comment.setMoment(moment);
			comment.setText(text);
			comment.setPicture(picture);
			comment.setReplies(replies);
			comment.setCommentParent(commentParent);
			comment.setRendezvous(rendezvousComment);
			comment = this.commentService.save(comment);

			this.unauthenticate();
			// this.commentService.flush();
		} catch (final Throwable oops) {
			caught = oops.getClass();

		}
		this.checkExceptions(expected, caught);
	}

	private Collection<Comment> createCommentsForTesting() {

		final Collection<Comment> res = new ArrayList<Comment>();
		final Comment comment;
		final Comment commentDiferentRendezvous;

		comment = this.commentService.findOne(this.getEntityId("comment1"));
		commentDiferentRendezvous = this.commentService.findOne(this
				.getEntityId("comment2"));

		res.add(comment);
		res.add(commentDiferentRendezvous);

		return res;
	}
}
