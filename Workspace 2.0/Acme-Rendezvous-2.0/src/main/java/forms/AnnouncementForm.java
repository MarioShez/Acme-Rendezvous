package forms;


import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;

public class AnnouncementForm {

	// Constructor 
	
	public AnnouncementForm(){
		super();
	}

	// Attributes
	
	private int id;
	private String title;
	private String description;
	private int rendezvousId;
	
	
	public int getId(){
		return id;
	}
	
	public void setId(int id){
		this.id=id;
	}
	
	@NotBlank
	@SafeHtml
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	@NotBlank
	@SafeHtml
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}

	@Valid
	@NotNull
	@ManyToOne(optional=false)
	public int getRendezvousId() {
		return rendezvousId;
	}

	public void setRendezvous(int rendezvousId) {
		this.rendezvousId = rendezvousId;
	}

	
}
