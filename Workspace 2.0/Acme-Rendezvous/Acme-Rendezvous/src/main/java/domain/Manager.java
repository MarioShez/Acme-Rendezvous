package domain;


import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;

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

	
	@NotBlank
	@SafeHtml
	public String getVat() {
		return vat;
	}
	
	public void setVat(String vat) {
		this.vat = vat;
	}
	

}
