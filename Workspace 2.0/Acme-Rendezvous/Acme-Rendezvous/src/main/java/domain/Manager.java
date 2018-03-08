package domain;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;

@Entity
@Access(AccessType.PROPERTY)
public class Manager extends Actor {

	// Constructors

	public Manager() {
		super();
	}

	// Attributes

	private String vat;

	// Relationships

//	private Collection<Service> service;
	
	
	@NotBlank
	@SafeHtml
	public String getVat() {
		return vat;
	}
	
	public void setVat(String vat) {
		this.vat = vat;
	}
	
	
//	@Valid
//	@NotNull
//	@OneToMany(mappedBy = "manager")
//	public Collection<Service> getService() {
//		return service;
//	}
//
//	public void setService(Collection<Service> service) {
//		this.service = service;
//	}

}
