package controllers.user;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.RendezvousService;
import services.UserService;
import controllers.AbstractController;
import domain.Rendezvous;
import domain.User;
import forms.RendezvousForm;

@Controller
@RequestMapping("/rendezvous/user")
public class RendezvousUserController extends AbstractController {

	// Services ------------------------------------------------------

	@Autowired
	private RendezvousService rendezvousService;
	
	@Autowired
	private UserService userService;


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
		RendezvousForm rendezvousForm = rendezvousService.construct(rendezvous);
		
		ModelAndView result = createEditModelAndView(rendezvousForm);

		return result;
	}

	// Edition ----------------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam int rendezvousId) {

		Rendezvous rendezvous = rendezvousService.findOneToEdit(rendezvousId);
		RendezvousForm rendezvousForm = rendezvousService.construct(rendezvous);

		ModelAndView result = createEditModelAndView(rendezvousForm);

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid RendezvousForm rendezvousForm, BindingResult binding) {

		ModelAndView result;
		if (binding.hasErrors())
			result = this.createEditModelAndView(rendezvousForm);
		else
			try {
				Rendezvous rendezvous = rendezvousService.reconstruct(rendezvousForm, binding);
				this.rendezvousService.save(rendezvous);
				result = new ModelAndView("redirect:list-organised.do");
			} catch (final Throwable oops) {
				result = this.createEditModelAndView(rendezvousForm, "rendezvous.commit.error");
			}
		return result;
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(RendezvousForm rendezvousForm, BindingResult binding) {

		ModelAndView result;

		try {
			this.rendezvousService.changeToDeleted(rendezvousForm.getId());
			result = new ModelAndView("redirect:list-organised.do");
		} catch (final Throwable oops) {
			result = this.createEditModelAndView(rendezvousForm, "rendezvous.commit.error");
		}

		return result;
	}
	
	@RequestMapping(value = "/assign", method = RequestMethod.GET)
	public ModelAndView assign(int rendezvousSourceId, int rendezvousTargetId) {
		
		Rendezvous source = rendezvousService.findOneToEdit(rendezvousSourceId);
		Rendezvous target = rendezvousService.findOneToEdit(rendezvousTargetId);
		
		ModelAndView result;

		try {
			source.getLinkedRendezvouses().add(target);
			rendezvousService.save(source);
			result = new ModelAndView("redirect:list-link.do?rendezvousId=" + rendezvousSourceId);
		} catch (final Throwable oops) {
			RendezvousForm sourceForm = rendezvousService.construct(source);
			result = this.createEditModelAndView(sourceForm, "rendezvous.commit.error");
		}

		return result;
	}
	
	@RequestMapping(value = "/unassign", method = RequestMethod.GET)
	public ModelAndView unassign(int rendezvousSourceId, int rendezvousTargetId) {
		
		Rendezvous source = rendezvousService.findOneToEdit(rendezvousSourceId);
		Rendezvous target = rendezvousService.findOneToEdit(rendezvousTargetId);
		
		ModelAndView result;

		try {
			source.getLinkedRendezvouses().remove(target);
			rendezvousService.save(source);
			result = new ModelAndView("redirect:list-link.do?rendezvousId=" + rendezvousSourceId);
		} catch (final Throwable oops) {
			RendezvousForm sourceForm = rendezvousService.construct(source);
			result = this.createEditModelAndView(sourceForm, "rendezvous.commit.error");
		}

		return result;
	}
	
	@RequestMapping(value = "/rspv", method = RequestMethod.GET)
	public ModelAndView rspv(@RequestParam int rendezvousId){
		
		Rendezvous rendezvous = rendezvousService.findOne(rendezvousId);
		User principal = userService.findByPrincipal();
		
		Assert.notNull(principal);
		Assert.isTrue(!principal.getRsvpdRendezvouses().contains(rendezvous));
		
		principal.getRsvpdRendezvouses().add(rendezvous);
		rendezvous.getAttendants().add(principal);
		
		userService.save(principal);
		
		return new ModelAndView("redirect:list-rspv.do");
	}
	
	@RequestMapping(value = "/unrspv", method = RequestMethod.GET)
	public ModelAndView unrspv(@RequestParam int rendezvousId){
		
		Rendezvous rendezvous = rendezvousService.findOne(rendezvousId);
		User principal = userService.findByPrincipal();
		
		Assert.notNull(principal);
		Assert.isTrue(principal.getRsvpdRendezvouses().contains(rendezvous));
		
		principal.getRsvpdRendezvouses().remove(rendezvous);
		rendezvous.getAttendants().remove(principal);
		
		userService.save(principal);
		
		return new ModelAndView("redirect:list-rspv.do");
	}

	// Ancillary methods ------------------------------------------------------

	protected ModelAndView createEditModelAndView(final RendezvousForm rendezvousForm) {
		ModelAndView result;

		result = this.createEditModelAndView(rendezvousForm, null);
		return result;
	}

	protected ModelAndView createEditModelAndView(final RendezvousForm rendezvousForm,
			final String messageCode) {

		rendezvousService.checkPrincipalForm(rendezvousForm);

		ModelAndView result;

		result = new ModelAndView("rendezvous/edit");
		result.addObject("rendezvousForm", rendezvousForm);
		result.addObject("message", messageCode);

		return result;
	}

}
