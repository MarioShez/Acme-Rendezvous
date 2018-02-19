package controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.UserService;
import domain.User;
import forms.UserForm;

@Controller
@RequestMapping("/actor")
public class RegisterUserController extends AbstractController {

	// Services -------------------------------------------------------------
	
	@Autowired
	private UserService userService;
	
	// Constructors ---------------------------------------------------------

	public RegisterUserController(){
		super();
	}
	
	// Registering ----------------------------------------------------------

	@RequestMapping(value = "/register_User", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView res;
		UserForm userForm;

		userForm = new UserForm();
		res = this.createEditModelAndView(userForm);

		return res;
	}

	@RequestMapping(value = "/register_User", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final UserForm userForm,
			final BindingResult binding) {
		ModelAndView res;
		User user;
		
		user = userService.reconstruct(userForm, binding);
		if (binding.hasErrors())
			res = this.createEditModelAndView(userForm, "actor.params.error");
		else if (!userForm.getRepeatPassword().equals(userForm.getUserAccount().getPassword()))
			res = this.createEditModelAndView(userForm, "actor.params.errorPassword");
		else
			try {
				this.userService.save(user);
				res = new ModelAndView("redirect:../");
			} catch (final Throwable oops) {
				res = this.createEditModelAndView(userForm,
						"actor.commit.error");
			}

		return res;
	}
	
	
	// Ancillary methods --------------------------------------------------

	
	protected ModelAndView createEditModelAndView(final UserForm userForm) {
		ModelAndView result;

		result = this.createEditModelAndView(userForm, null);

		return result;
	}
	
	protected ModelAndView createEditModelAndView(final UserForm userForm,
		final String message) {
	ModelAndView result;

	result = new ModelAndView("actor/register_User");
	result.addObject("userForm", userForm);
	result.addObject("message", message);

	return result;
}
	
	
	
	
	
	
}
