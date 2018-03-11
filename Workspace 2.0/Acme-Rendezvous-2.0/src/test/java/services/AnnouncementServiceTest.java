package services;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import utilities.AbstractTest;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring/junit.xml"})
@Transactional
public class AnnouncementServiceTest extends AbstractTest {

	// The SUT -----------------------
	@Autowired
	private AnnouncementService announcementService;
	
//	@Test 	for positive											//
//	@Test(expected=IllegalArgumentException.class) for negative	//
	
	@Test
	public void driver(){
		Object testingData[][] = {
				
		};
		
		for (int i = 0; i < testingData.length; i++){
//			template((String)testingData[i][0],
//					(int)super.getEntityId(testingData[i][1]),
//					(Class<?>)testingData[i][2]);
		}
		
	}
	

	protected void template(String username, int announcementId, Class<?> expected) {
		Class<?> caught;
		
		caught = null;
		try{
			authenticate(username);
		}catch (Throwable oops) {
			caught = oops.getClass();
		}
		checkExceptions(expected, caught);
	}
	
	
	
}
