
package services;

import java.util.ArrayList;
import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.CategoryRepository;
import domain.Category;

@Service
@Transactional
public class CategoryService {

	// Managed repository

	@Autowired
	private CategoryRepository	categoryRepository;

	//Other services

	@Autowired
	private ServiceService		serviceService;


	// Constructors

	public CategoryService() {
		super();
	}

	// Simple CRUD methods

	public Category create(final Integer parentId) {

		final Category result = new Category();
		if (parentId != null) {
			final Category parent = this.categoryRepository.findOne(parentId);
			result.setCategoryParent(parent);
		}

		return result;

	}

	public Collection<Category> findAll() {

		final Collection<Category> result = this.categoryRepository.findAll();
		return result;
	}

	public Category findOne(final int categoryId) {

		Assert.isTrue(categoryId != 0);

		final Category result = this.categoryRepository.findOne(categoryId);
		return result;
	}

	public Category save(final Category category) {

		Assert.notNull(category);

		final Category result = this.categoryRepository.save(category);
		if (category.getId() == 0)
			for (final domain.Service s : category.getServices()) {
				s.getCategories().add(category);
				this.serviceService.save(s);
			}

		if (result.getCategoryParent() != null)
			this.actualizarParent(result.getCategoryParent(), result);

		return result;
	}

	public void delete(final Category category) {

		for (final domain.Service s : category.getServices()) {
			s.getCategories().remove(category);
			this.serviceService.save(s);
		}
		for (final Category c : category.getCategories())
			this.delete(c);

		this.categoryRepository.delete(category);
	}
	// Other business methods

	private void actualizarParent(final Category categoryParent, final Category commentChild) {
		final Collection<Category> children = new ArrayList<Category>();
		if (categoryParent.getCategories() != null)
			children.addAll(categoryParent.getCategories());
		children.add(commentChild);
		categoryParent.setCategories(children);
		this.categoryRepository.save(categoryParent);
	}
}
