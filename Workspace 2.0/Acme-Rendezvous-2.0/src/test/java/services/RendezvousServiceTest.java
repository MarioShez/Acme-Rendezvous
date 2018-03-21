
package services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import utilities.AbstractTest;
import domain.GpsCoordinate;
import domain.Rendezvous;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@Transactional
public class RendezvousServiceTest extends AbstractTest {

	// System under test ------------------------------------------------------

	@Autowired
	private RendezvousService	rendezvousService;


	// Tests ------------------------------------------------------------------

	// 5.2 Acme-Rendezvous Create a rendezvous, which he’s implicitly assumed to attend.
	@Test
	public void driverCreate() {
		GpsCoordinate gpsCoordinate = new GpsCoordinate();
		gpsCoordinate = createGps();

		Object testingData[][] = {
			// positive test
			{
				"user1", "name", "description", "www.goole.es", "10/05/2000", gpsCoordinate, false, false, null
			},
			// negative test: usuario no valido
			{
				null, "name", "description", "www.goole.es", "10/05/2000", gpsCoordinate, false, false, IllegalArgumentException.class
			},
		};
		for (int i = 0; i < testingData.length; i++) {
			templateCreate((String) testingData[i][0], (String) testingData[i][1], (String) testingData[i][2], (String) testingData[i][3], (String) testingData[i][4], (GpsCoordinate) testingData[i][5], (boolean) testingData[i][6],
				(boolean) testingData[i][7], (Class<?>) testingData[i][8]);
		}

	}
	
	// 5.2 Acme-Rendezvous Create a rendezvous, Note that a user may edit his or her rendezvouses as long as they aren’t saved them in final mode.
		@Test
		public void driverEdit() {
			GpsCoordinate gpsCoordinate = new GpsCoordinate();
			gpsCoordinate = createGps();

			Object testingData[][] = {
				// positive test
				{
					"rendezvous1", "user1", "name", "description", "www.goole.es", "10/05/2000 19:32", gpsCoordinate, false, false, null
				},
				// negative test: usuario no válido
//				{
//					"rendezvous1", "user2", "name", "description", "www.goole.es", "10/05/2000", gpsCoordinate, false, true, javax.validation.ConstraintViolationException.class
//				},
			};
			for (int i = 0; i < testingData.length; i++) {
				templateEdit(getEntityId((String) testingData[i][0]), (String) testingData[i][1], (String) testingData[i][2], (String) testingData[i][3], (String) testingData[i][4], (String) testingData[i][5], (GpsCoordinate) testingData[i][6], (boolean) testingData[i][7],
					(boolean) testingData[i][8], (Class<?>) testingData[i][9]);
			}

		}

	// Ancillary methods ------------------------------------------------------

	private void templateCreate(String user, String name, String description, String picture, String date, GpsCoordinate gps, boolean adult, boolean finalVersion, Class<?> expected) {
		Rendezvous rendezvous;
		Class<?> caught;
		caught = null;
		Date fecha = new Date();

		SimpleDateFormat pattern = new SimpleDateFormat("dd/MM/yyyy");
		try {
			fecha = pattern.parse(date);
		} catch (ParseException e) {
			e.getClass();
		}

		try {
			authenticate(user);
			rendezvous = this.rendezvousService.create();
			rendezvous.setName(name);
			rendezvous.setDescription(description);
			rendezvous.setPicture(picture);
			rendezvous.setMoment(fecha);
			rendezvous.setGpsCoordinate(gps);
			rendezvous.setAdult(adult);
			rendezvous.setFinalVersion(finalVersion);

		} catch (Throwable oops) {
			System.out.println(oops);
			caught = oops.getClass();
		}
		checkExceptions(expected, caught);
		unauthenticate();
	}
	
	private void templateEdit(int rendezvousId, String user, String name, String description, String picture, String date, GpsCoordinate gps, boolean adult, boolean finalVersion, Class<?> expected) {
		Rendezvous rendezvous;
		Class<?> caught;
		caught = null;
		Date fecha = new Date();

		SimpleDateFormat pattern = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		try {
			fecha = pattern.parse(date);
		} catch (ParseException e) {
			e.getClass();
		}

		try {
			authenticate(user);
			rendezvous = this.rendezvousService.findOne(rendezvousId);
			
			rendezvous.setName(name);
			rendezvous.setDescription(description);
			rendezvous.setPicture(picture);
			rendezvous.setMoment(fecha);
			rendezvous.setGpsCoordinate(gps);
			rendezvous.setAdult(adult);
			rendezvous.setFinalVersion(finalVersion);
			
			this.rendezvousService.save(rendezvous);
			this.rendezvousService.flush();
			unauthenticate();
		} catch (Throwable oops) {
			caught = oops.getClass();
		}
		checkExceptions(expected, caught);
	}

	private GpsCoordinate createGps() {
		GpsCoordinate res = new GpsCoordinate();

		res.setLatitude(89.0);
		res.setLongitude(179.0);

		return res;
	}

}
