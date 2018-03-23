package services;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import utilities.AbstractTest;
import domain.CreditCard;
import domain.Request;
import domain.Service;

@ContextConfiguration(locations = { "classpath:spring/junit.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class RequestServiceTest extends AbstractTest {

	// Suporting Services ------------------------------------------------------

	@Autowired
	private RequestService requestService;

	@Autowired
	private ServiceService serviceService;

	@Autowired
	RendezvousService rendezvousService;

	@Autowired
	UserService userService;

	// Test CreateAndSave
	// ----------------------------------------------------------------------------------
	// 4.3 Acme-Rendezvous-2.0 Request a service for one of the rendezvouses
	// that he or she’s created. He or she must specify a valid credit card in
	// every request for a service. Optionally, he or she can provide some
	// comments in the request.

	@Test
	public void driverCreateAndSaveComment() {

		CreditCard cc = createCCOk();
		CreditCard ccNoHolder = createCCNoHolder();
		CreditCard ccNoBrand = createCCNoBrand();
		CreditCard ccBadCVV = createCCBadCVV();
		CreditCard ccBadMonth = createCCBadMonth();
		CreditCard ccBadNumber = createCCBadNumber();
		CreditCard ccBadYear = createCCBadYear();

		final Object testingData[][] = {

				{
						// positive test: creando un request correctamente
						"rendezvous1", "service1", cc, "comment1", "user1",
						null },
				{

						// negative test: creando un request sin holder
						"rendezvous1", "service1", ccNoHolder, "comment1",
						"user1",
						javax.validation.ConstraintViolationException.class },
				{

						// negative test: creando un request sin brand
						"rendezvous1", "service1", ccNoBrand, "comment1",
						"user1",
						javax.validation.ConstraintViolationException.class },
				{

						// negative test: creando un request mal CVV
						"rendezvous1", "service1", ccBadCVV, "comment1",
						"user1",
						javax.validation.ConstraintViolationException.class },
				{

						// negative test: creando un request insertando mal el mes
						"rendezvous1", "service1", ccBadMonth, "comment1",
						"user1",
						javax.validation.ConstraintViolationException.class },
				{

						// negative test: creando un request insertando mal un number
						"rendezvous1", "service1", ccBadNumber, "comment1",
						"user1",
						javax.validation.ConstraintViolationException.class },
				{

						// negative test: creando un request insertando mal un year
						"rendezvous1", "service1", ccBadYear, "comment1",
						"user1",
						javax.validation.ConstraintViolationException.class },

		};

		for (int i = 0; i < testingData.length; i++) {

			this.templateCreateAndSave(
					super.getEntityId((String) testingData[i][0]),
					super.getEntityId((String) testingData[i][1]),
					(CreditCard) testingData[i][2], (String) testingData[i][3],
					(String) testingData[i][4], (Class<?>) testingData[i][5]);
		}

	}

	protected void templateCreateAndSave(final int rendezvousId,
			final int serviceId, final CreditCard creditCard,
			final String comment, final String user, Class<?> expected) {

		Request request;
		Service service;
		Class<?> caught;

		caught = null;

		try {
			super.authenticate(user);

			service = this.serviceService.findOne(serviceId);

			request = this.requestService.create(rendezvousId);
			request.setComment(comment);
			request.setCreditCard(creditCard);
			request.setService(service);

			request = this.requestService.save(request);
			this.requestService.flush();
			this.unauthenticate();

		} catch (Throwable oops) {
			caught = oops.getClass();
		}
		checkExceptions(expected, caught);
		unauthenticate();

	}

	private CreditCard createCCOk() {
		final CreditCard creditCard;
		creditCard = new CreditCard();

		creditCard.setHolder("Juan Carlos");
		creditCard.setBrand("La Caixa");
		creditCard.setCvv(285);
		creditCard.setExpirationMonth(02);
		creditCard.setNumber("4548812049400004");
		creditCard.setExpirationYear(2020);

		return creditCard;
	}

	private CreditCard createCCNoHolder() {
		final CreditCard creditCard;
		creditCard = new CreditCard();

		creditCard.setHolder("");
		creditCard.setBrand("La Caixa");
		creditCard.setCvv(285);
		creditCard.setExpirationMonth(02);
		creditCard.setNumber("4548812049400004");
		creditCard.setExpirationYear(2020);

		return creditCard;
	}

	private CreditCard createCCNoBrand() {
		final CreditCard creditCard;
		creditCard = new CreditCard();

		creditCard.setHolder("Juan Carlos");
		creditCard.setBrand("");
		creditCard.setCvv(285);
		creditCard.setExpirationMonth(02);
		creditCard.setNumber("4548812049400004");
		creditCard.setExpirationYear(2020);

		return creditCard;
	}

	private CreditCard createCCBadCVV() {
		final CreditCard creditCard;
		creditCard = new CreditCard();

		creditCard.setHolder("Juan Carlos");
		creditCard.setBrand("La Caixa");
		creditCard.setCvv(25);
		creditCard.setExpirationMonth(02);
		creditCard.setNumber("4548812049400004");
		creditCard.setExpirationYear(2020);

		return creditCard;
	}

	private CreditCard createCCBadMonth() {
		final CreditCard creditCard;
		creditCard = new CreditCard();

		creditCard.setHolder("Juan Carlos");
		creditCard.setBrand("La Caixa");
		creditCard.setCvv(285);
		creditCard.setExpirationMonth(20);
		creditCard.setNumber("4548812049400004");
		creditCard.setExpirationYear(2020);

		return creditCard;
	}

	private CreditCard createCCBadNumber() {
		final CreditCard creditCard;
		creditCard = new CreditCard();

		creditCard.setHolder("Juan Carlos");
		creditCard.setBrand("La Caixa");
		creditCard.setCvv(285);
		creditCard.setExpirationMonth(02);
		creditCard.setNumber("4548812063");
		creditCard.setExpirationYear(2020);

		return creditCard;
	}

	private CreditCard createCCBadYear() {
		final CreditCard creditCard;
		creditCard = new CreditCard();

		creditCard.setHolder("Juan Carlos");
		creditCard.setBrand("La Caixa");
		creditCard.setCvv(285);
		creditCard.setExpirationMonth(02);
		creditCard.setNumber("4548812049400004");
		creditCard.setExpirationYear(202555);

		return creditCard;
	}

}
