
package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;

@Entity
@Access(AccessType.PROPERTY)
public class Category extends DomainEntity {

	// Constructors

	public Category() {
		super();
	}


	// Attributes

	private String	name;
	private String	description;


	@NotBlank
	@SafeHtml
	public String getName() {
		return this.name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	@NotBlank
	@SafeHtml
	public String getDescription() {
		return this.description;
	}

	public void setDescription(final String description) {
		this.description = description;
	}


	// Relationships

	private Collection<Service>	services;


	@Valid
	@NotNull
	@ManyToMany(mappedBy = "categories")
	public Collection<Service> getServices() {
		return this.services;
	}

	public void setServices(final Collection<Service> services) {
		this.services = services;
	}

}
