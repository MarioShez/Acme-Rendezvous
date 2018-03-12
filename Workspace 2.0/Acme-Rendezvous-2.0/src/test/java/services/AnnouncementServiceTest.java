package services;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import utilities.AbstractTest;
import domain.Announcement;
import domain.Rendezvous;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring/junit.xml"})
@Transactional
public class AnnouncementServiceTest extends AbstractTest {

	// The SUT -----------------------
	@Autowired
	private AnnouncementService announcementService;
	
	@Autowired
	private RendezvousService rendezvousService;
	
	
	// Create and Save Test  --------------------------------------------------------
	@Test
	public void driverCreateAndSave(){
		Object testingData[][] = {
				// positive test
				{"user1", "rendezvous1", "sample title", "sample descripion", null},
				// negative test: usuario no valido
				{"user2", "rendezvous1", "sample title", "sample descripion", IllegalArgumentException.class},
				// negative test: usuario no autentificado
				{null, "rendezvous1", "sample title", "sample descripion", IllegalArgumentException.class},
				// negative test: title blank
				{"user1", "rendezvous1", " ", "sample description", javax.validation.ConstraintViolationException.class},
				// negative test: description blank
				{"user1", "rendezvous1", "sample title", " ", javax.validation.ConstraintViolationException.class}
		
		};
		
		for (int i = 0; i < testingData.length; i++){
			templateCreateAndSave((String)testingData[i][0],
					super.getEntityId((String)testingData[i][1]),
					(String)testingData[i][2],
					(String)testingData[i][3],
					(Class<?>)testingData[i][4]);
		}
		
	}

	protected void templateCreateAndSave(String username, int rendezvousId, 
			String title, String description, Class<?> expected) {
		
		Rendezvous rendezvous;
		Announcement announcement;
		Class<?> caught;
		
		caught = null;
		
		try{
			authenticate(username);
			rendezvous = this.rendezvousService.findOne(rendezvousId);
			announcement = this.announcementService.create(rendezvous);
			announcement.setTitle(title);
			announcement.setDescription(description);
			announcement = this.announcementService.save(announcement);
			this.announcementService.flush();
			
		}catch (Throwable oops) {
			caught = oops.getClass();
		}
		checkExceptions(expected, caught);
		unauthenticate();
	
	}
	
	
	// Edit Test --------------------------------------------------------
	
	@Test
	public void driverEditTest(){
		Object testingData[][] = {
				// positive test
				{"user1", "announcement1", null},
				// negative test: usuario no autentificado
				{null, "announcement1", IllegalArgumentException.class},
				// negative test: usuario no válido
				{"user2", "announcement1", IllegalArgumentException.class},
				
		};
		for (int i=0; i<testingData.length; i++){
			templateEdit((String)testingData[i][0],
					super.getEntityId((String)testingData[i][1]),
					(Class<?>)testingData[i][2]);
		}
	}

	private void templateEdit(String username, int announcementId, Class<?> expected) {
		
		Class<?> caught;
		Announcement announcement;
		
		caught = null;
		
		try{
			authenticate(username);
			
			announcement = this.announcementService.findOne(announcementId);
			announcement.setTitle("Sample Title");
			announcement = this.announcementService.save(announcement);
			
			this.announcementService.flush();
			
		}catch (Throwable oops) {
			caught = oops.getClass();
		}
		checkExceptions(expected, caught);
		unauthenticate();
		
	}
	
	
	// Delete Test  --------------------------------------------------------

	@Test
	public void driverDelete(){
		Object testingData[][] = {
				// positive test
				{"admin", "announcement1", null},
				// negative test: user (user can not delete)
				{"user1", "announcement1", IllegalArgumentException.class},
				// negative test: user null
				{null, "announcement1", IllegalArgumentException.class},
		};
		
		for(int i=0; i<testingData.length; i++){
			templateDelete((String)testingData[i][0],
					super.getEntityId((String)testingData[i][1]),
					(Class<?>)testingData[i][2]);
		}
	}

	private void templateDelete(String username, int announcementId, Class<?> expected) {

		Class<?> caught;
		Announcement announcement;
		
		caught = null;
		
		try{
			authenticate(username);
			
			announcement = this.announcementService.findOne(announcementId);
			this.announcementService.delete(announcement);
			
			this.announcementService.flush();
			
		}catch (Throwable oops) {
			caught = oops.getClass();
		}
		checkExceptions(expected, caught);
		unauthenticate();
	}
	

}
