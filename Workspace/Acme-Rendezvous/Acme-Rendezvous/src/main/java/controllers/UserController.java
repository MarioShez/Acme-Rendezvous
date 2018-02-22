
package controllers;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.UserService;
import domain.User;
import forms.UserForm;

@Controller
@RequestMapping("/user")
public class UserController extends AbstractController {

	// Services -------------------------------------------------------------

	@Autowired
	private UserService	userService;


	// Constructors ---------------------------------------------------------

	public UserController() {
		super();
	}

	// Listing --------------------------------------------------------------

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		Collection<User> users;

		users = userService.findAll();

		result = new ModelAndView("user/list");
		result.addObject("user", users);
		result.addObject("requestURI", "user/list.do");

		return result;
	}

	// Registering ----------------------------------------------------------

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView res;
		UserForm userForm;

		userForm = new UserForm();
		res = this.createEditModelAndView(userForm);

		return res;
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final UserForm userForm, final BindingResult binding) {
		ModelAndView res;
		User user;

		user = userService.reconstruct(userForm, binding);
		if (binding.hasErrors())
			res = this.createEditModelAndView(userForm, "user.params.error");
		else if (!userForm.getRepeatPassword().equals(userForm.getUserAccount().getPassword()))
			res = this.createEditModelAndView(userForm, "user.params.errorPassword");
		else if (userForm.getTermsAndConditions() == false) {
			res = this.createEditModelAndView(userForm, "user.params.errorTerms");
		} else
			try {
				this.userService.save(user);
				res = new ModelAndView("redirect:../");
			} catch (final Throwable oops) {
				res = this.createEditModelAndView(userForm, "user.commit.error");
			}

		return res;
	}

	@RequestMapping(value = "/rendezvousCreator/list", method = RequestMethod.GET)
	public ModelAndView listRendezvousCreator(@RequestParam int rendezvousId) {
		ModelAndView result;
		User organizer;

		organizer = userService.findOrganiserByRendezvousId(rendezvousId);

		result = new ModelAndView("user/list");
		result.addObject("user", organizer);
		result.addObject("requestURI", "user/rendezvousCreator/list.do");

		return result;
	}

	@RequestMapping(value = "/rendezvousAttends/list", method = RequestMethod.GET)
	public ModelAndView listRendezvousAttendant(@RequestParam int rendezvousId) {
		ModelAndView result;
		Collection<User> attends;

		attends = userService.findAttendsByRendezvousId(rendezvousId);

		result = new ModelAndView("user/list");
		result.addObject("user", attends);
		result.addObject("requestURI", "user/rendezvousAttends/list.do");

		return result;
	}

	protected ModelAndView createEditModelAndView(final UserForm userForm) {
		ModelAndView result;

		result = this.createEditModelAndView(userForm, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(final UserForm userForm, final String message) {
		ModelAndView result;

		result = new ModelAndView("user/register_User");
		result.addObject("userForm", userForm);
		result.addObject("message", message);

		return result;
	}
}
