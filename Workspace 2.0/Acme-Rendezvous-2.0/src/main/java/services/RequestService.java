
package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.RequestRepository;
import domain.Rendezvous;
import domain.Request;

@Service
@Transactional
public class RequestService {

	// Managed repository

	@Autowired
	private RequestRepository	requestRepository;

	// Supported services

	@Autowired
	private RendezvousService	rendezvousService;

	@Autowired
	private UserService			userService;

	@Autowired
	private ServiceService		serviceService;


	// Constructors

	public RequestService() {
		super();
	}

	// Simple CRUD methods

	public Request create(final Integer rendezvousId) {

		final Request result = new Request();
		if (rendezvousId != null) {
			final Rendezvous rendezvous = this.rendezvousService.findOneToEdit(rendezvousId);
			result.setRendezvous(rendezvous);
		}

		return result;

	}

	public Collection<Request> findAll() {

		final Collection<Request> result = this.requestRepository.findAll();
		return result;
	}

	public Request findOne(final int requestId) {

		Assert.isTrue(requestId != 0);

		final Request result = this.requestRepository.findOne(requestId);
		return result;
	}

	public Request save(final Request request) {

		Assert.isTrue(this.userService.findByPrincipal().getOrganisedRendezvouses().contains(request.getRendezvous()));
		Assert.notNull(request.getRendezvous());
		Assert.notNull(request.getService());

		final Request result = this.requestRepository.save(request);
		if (request.getId() == 0) {
			request.getRendezvous().getRequests().add(result);
			this.rendezvousService.save(request.getRendezvous());
			request.getService().getRequests().add(result);
			this.serviceService.save(request.getService());
		}

		return result;
	}

	public void delete(final Request request) {

		Assert.isTrue(this.userService.findByPrincipal().getOrganisedRendezvouses().contains(request.getRendezvous()));

		request.getRendezvous().getRequests().remove(request);
		this.rendezvousService.save(request.getRendezvous());
		request.getService().getRequests().remove(request);
		this.serviceService.save(request.getService());

		this.requestRepository.delete(request);
	}

	// Other business methods

}
