package controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("/termsAndConditions")
public class TermsAndConditions {

	// Services -------------------------------------------------------------
		
	// Constructors ---------------------------------------------------------

		public TermsAndConditions(){
			super();
		}
		
		
	// Listing --------------------------------------------------------------
		@RequestMapping(value = "/list", method = RequestMethod.GET)
		public ModelAndView list() {
			ModelAndView result;
			
			result = new ModelAndView("termsAndConditions/list");
			result.addObject("requestURI", "termsAndConditions/list.do");
			
			return result;
		}
}
