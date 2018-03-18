package domain;

import java.util.Collection;

import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;
import org.hibernate.validator.constraints.URL;

public class ServiceForm {
	
	public ServiceForm(){
		super();
	}

	// Attributes

	private int id;
	private String name;
	private String description;
	private String picture;
	private boolean cancelled;
	
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
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
	@SafeHtml
	public String getPicture() {
		return picture;
	}
	
	public void setPicture(String picture) {
		this.picture = picture;
	}
	
	public boolean isCancelled() {
		return this.cancelled;
	}

	public void setCancelled(final boolean cancelled) {
		this.cancelled = cancelled;
	}
	
	// Relationships
	private Manager					manager;
	private Collection<Request>		requests;
	private Collection<Category>	categories;

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
	@OneToMany(mappedBy = "service")
	public Collection<Request> getRequests() {
		return requests;
	}

	public void setRequests(Collection<Request> requests) {
		this.requests = requests;
	}

	@Valid
	@NotNull
	@ManyToMany()
	public Collection<Category> getCategories() {
		return categories;
	}

	public void setCategories(Collection<Category> categories) {
		this.categories = categories;
	}
	
	
	
	
}
