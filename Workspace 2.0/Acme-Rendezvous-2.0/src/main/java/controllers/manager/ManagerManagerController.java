package controllers.manager;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.ManagerService;
import controllers.AbstractController;
import domain.Manager;
import forms.ManagerForm;

@Controller
@RequestMapping("/manager/manager")
public class ManagerManagerController extends AbstractController {

	// Services ---------------

	@Autowired
	private ManagerService managerService;

	// Constructors -----------
	
	public ManagerManagerController() {
		super();
	}

	// Edition ----------------
	
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(){
		
		Assert.isTrue(managerService.checkAuthority());
		
		Manager manager = managerService.findByPrincipal();
		ManagerForm managerForm = managerService.construct(manager);
		
		ModelAndView result = new ModelAndView("manager/edit");
		result.addObject("managerForm", managerForm);
		
		return result;
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid ManagerForm managerForm, BindingResult binding){
		
		Assert.isTrue(managerService.checkAuthority());
		
		ModelAndView result;
		Manager manager = managerService.reconstruct(managerForm, binding);
		
		if(binding.hasErrors()){
			result = createEditModelAndView(managerForm, "manager.params.error");
		}
		
	}
	
	// Ancillary methods ----------------------
	
	protected ModelAndView createEditModelAndView(ManagerForm managerForm){
		
		ModelAndView res;
		
		res = this.createEditModelAndView(managerForm, null);
		
		return res;
	}

}
