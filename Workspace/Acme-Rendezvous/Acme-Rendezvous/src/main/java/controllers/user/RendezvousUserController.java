package controllers.user;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.RendezvousService;
import controllers.AbstractController;
import domain.Rendezvous;

@Controller
@RequestMapping("/rendezvous/user")
public class RendezvousUserController extends AbstractController {

	// Services ------------------------------------------------------

	@Autowired
	private RendezvousService rendezvousService;


	// Constructors --------------------------------------------------

	public RendezvousUserController() {
		super();
	}

	// Listing -------------------------------------------------------

	@RequestMapping(value = "/list-organised", method = RequestMethod.GET)
	public ModelAndView organised() {

		Collection<Rendezvous> rendezvouses = rendezvousService
				.findOrganisedRendezvousesByPrincipal();

		ModelAndView result = new ModelAndView("rendezvous/list");
		result.addObject("rendezvouses", rendezvouses);
		result.addObject("rendezvousSource", null);
		result.addObject("requestURI", "rendezvous/user/list-organised.do");

		return result;
	}

	@RequestMapping(value = "/list-rspv", method = RequestMethod.GET)
	public ModelAndView rspv() {

		Collection<Rendezvous> rendezvouses = rendezvousService
				.findRspvdRendezvousesByPrincipal();

		ModelAndView result = new ModelAndView("rendezvous/list");
		result.addObject("rendezvouses", rendezvouses);
		result.addObject("rendezvousSource", null);
		result.addObject("requestURI", "rendezvous/user/list-rspv.do");

		return result;
	}
	
	@RequestMapping(value = "/list-link", method = RequestMethod.GET)
	public ModelAndView link(@RequestParam int rendezvousId) {

		Collection<Rendezvous> rendezvouses = rendezvousService
				.findOrganisedRendezvousesByPrincipal();
		Rendezvous rendezvousSource = rendezvousService.findOneToEdit(rendezvousId);
		
		rendezvouses.remove(rendezvousSource);

		ModelAndView result = new ModelAndView("rendezvous/list");
		result.addObject("rendezvouses", rendezvouses);
		result.addObject("rendezvousSource", rendezvousSource);
		result.addObject("requestURI", "rendezvous/user/list-link.do");

		return result;
	}
	
	// Create ---------------------------------------------------------

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {

		Rendezvous rendezvous = rendezvousService.create();
		
		ModelAndView result = createEditModelAndView(rendezvous);

		return result;
	}

	// Edition ----------------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam int rendezvousId) {

		Rendezvous rendezvous = rendezvousService.findOneToEdit(rendezvousId);

		ModelAndView result = createEditModelAndView(rendezvous);

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid Rendezvous rendezvous, BindingResult binding) {

		ModelAndView result;
		if (binding.hasErrors())
			result = this.createEditModelAndView(rendezvous);
		else
			try {
				this.rendezvousService.save(rendezvous);
				result = new ModelAndView("redirect:list-organised.do");
			} catch (final Throwable oops) {
				result = this.createEditModelAndView(rendezvous, "rendezvous.commit.error");
			}
		return result;
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(Rendezvous rendezvous, BindingResult binding) {

		ModelAndView result;

		try {
			this.rendezvousService.changeToDeleted(rendezvous.getId());
			result = new ModelAndView("redirect:list-organised.do");
		} catch (final Throwable oops) {
			result = this.createEditModelAndView(rendezvous, "rendezvous.commit.error");
		}

		return result;
	}
	
	@RequestMapping(value = "/assign", method = RequestMethod.GET)
	public ModelAndView assing(int rendezvousSourceId, int rendezvousTargetId) {
		
		Rendezvous source = rendezvousService.findOneToEdit(rendezvousSourceId);
		Rendezvous target = rendezvousService.findOneToEdit(rendezvousTargetId);
		
		ModelAndView result;

		try {
			source.getLinkedRendezvouses().add(target);
			result = new ModelAndView("redirect:list-link.do?rendezvousId=" + rendezvousSourceId);
		} catch (final Throwable oops) {
			result = this.createEditModelAndView(source, "rendezvous.commit.error");
		}

		return result;
	}
	
	@RequestMapping(value = "/unassign", method = RequestMethod.GET)
	public ModelAndView unassing(int rendezvousSourceId, int rendezvousTargetId) {
		
		Rendezvous source = rendezvousService.findOneToEdit(rendezvousSourceId);
		Rendezvous target = rendezvousService.findOneToEdit(rendezvousTargetId);
		
		ModelAndView result;

		try {
			source.getLinkedRendezvouses().remove(target);
			result = new ModelAndView("redirect:list-link.do?rendezvousId=" + rendezvousSourceId);
		} catch (final Throwable oops) {
			result = this.createEditModelAndView(source, "rendezvous.commit.error");
		}

		return result;
	}

	// Ancillary methods ------------------------------------------------------

	protected ModelAndView createEditModelAndView(final Rendezvous rendezvous) {
		ModelAndView result;

		result = this.createEditModelAndView(rendezvous, null);
		return result;
	}

	protected ModelAndView createEditModelAndView(final Rendezvous rendezvous,
			final String messageCode) {

		rendezvousService.checkPrincipal(rendezvous);

		ModelAndView result;

		result = new ModelAndView("rendezvous/edit");
		result.addObject("rendezvous", rendezvous);

		return result;
	}

}
