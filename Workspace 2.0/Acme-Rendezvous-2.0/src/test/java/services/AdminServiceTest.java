package services;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import utilities.AbstractTest;
import domain.Comment;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring/junit.xml"})
@Transactional
public class AdminServiceTest extends AbstractTest{
	
	
	@Autowired
	private AdminService adminService;
	
	@Autowired
	private CommentService commentService;
	
	@PersistenceContext
	private EntityManager entityManager;
	
	
	//Test Login---------------------------------------
	
		@Test
		public void loginDriver(){
			final Object testingData[][] = {
				{
					//creando user correctamente
					"admin",null
				}
				,{
					//creando user sin name
					"DonManuee", IllegalArgumentException.class
				}
			};
			
			for (int i = 0; i<testingData.length; i++){
				this.templateLoginAdmin((String) testingData[i][0], (Class<?>) testingData[i][1]);
			}
		}
		
		private void templateLoginAdmin(final String admin, final Class<?> expected){
			Class<?> caught;
			caught = null;
			
			try{
				super.authenticate(admin);
				this.unauthenticate();
				this.adminService.flush();
				
			}catch (final Throwable oops){
				caught = oops.getClass();
				this.entityManager.clear();
			}
			
			this.checkExceptions(expected, caught);
		}
		
		@Test
		public void driveRemoveComment() {

			final Object testingData[][] = {
				//admin está registrado
				{
					"admin", "comment1", null
				}, {
					"user1", "comment1", IllegalArgumentException.class
				}
			};

			for (int i = 0; i < testingData.length; i++)
				this.templateRemoveComment((String) testingData[i][0], (String) testingData[i][1], (Class<?>) testingData[i][2]);

		}

		public void templateRemoveComment(final String username, final String commentId, final Class<?> expected) {

			Class<?> caught;
			Comment comment;

			caught = null;
			comment = this.commentService.findOne(super.getEntityId(commentId));

			try {
				super.authenticate(username);
				this.commentService.delete(comment);
				this.unauthenticate();
				this.commentService.flush();
			} catch (final Throwable oops) {
				caught = oops.getClass();
				//Se borra la cache para que no salte siempre el error del primer objeto que ha fallado en el test
				this.entityManager.clear();
			}

			this.checkExceptions(expected, caught);

		}
		
		

}
