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

		private String name;
		private String description;
		
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
		
		// Relationships

		private Collection<Services> services;
		
		@Valid
		@NotNull
		@ManyToMany(mappedBy="category")
		public Collection<Services> getServices() {
			return services;
		}

		public void setServices(Collection<Services> services) {
			this.services = services;
		}

}
