package controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import domain.Category;

import services.CategoryService;

@Controller
@RequestMapping("/category")
public class CategoryController extends AbstractController{

	// Services --------------------
	@Autowired
	private CategoryService categoryService;
	
	// Constructors ----------------
	public CategoryController(){
		super();
	}
	
	// Listing ---------------------
	@RequestMapping(value="/list", method=RequestMethod.GET)
	public ModelAndView list(){
		ModelAndView res;
		Collection<Category> categories;
		
		categories = this.categoryService.subCategories();
		
		res = new ModelAndView("category/list");
		res.addObject("category", categories);
		res.addObject("requestURI", "category/list.do");
		
		return res;
	}
	
	@RequestMapping(value="/list", method=RequestMethod.GET)
	public ModelAndView list(@RequestParam final int categoryId){
		ModelAndView res;
		Category categoryParent;
		Collection<Category> categories;
		
		categoryParent = this.categoryService.findOne(categoryId);
		Assert.notNull(categoryParent);
		categories = categoryParent.getCategories();
		
		res = new ModelAndView("category/list");
		res.addObject("categories",categories);
		res.addObject("categoryParent",categoryParent);
		
		return res;
	}
	
}
