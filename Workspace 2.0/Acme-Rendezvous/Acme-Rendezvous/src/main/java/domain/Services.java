package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;
import org.hibernate.validator.constraints.URL;

@Entity
@Access(AccessType.PROPERTY)
public class Services extends DomainEntity {

	// Constructors

	public Services() {
		super();
	}

	// Attributes

	private String name;
	private String description;
	private String picture;

	@NotBlank
	@SafeHtml
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@NotBlank
	@SafeHtml
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@URL
	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	// Relationships

	private Manager manager;
	private Collection<Category> category;

	@Valid
	@NotNull
	@ManyToOne(optional = false)
	public Manager getManager() {
		return manager;
	}

	public void setManager(Manager manager) {
		this.manager = manager;
	}

	@Valid
	@NotNull
	@ManyToMany()
	public Collection<Category> getCategory() {
		return category;
	}

	public void setCategory(Collection<Category> category) {
		this.category = category;
	}

}
