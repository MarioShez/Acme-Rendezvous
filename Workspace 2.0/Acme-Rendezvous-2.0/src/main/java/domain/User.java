package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Entity
@Access(AccessType.PROPERTY)
public class User extends Actor {
	
	// Constructors
	
	public User() {
		super();
	}
	
	// Relationships
	
	private Collection<Answer> answer;
	private Collection<Rendezvous> organisedRendezvouses;
	private Collection<Rendezvous> rsvpdRendezvouses;
	
	@Valid
	@OneToMany(mappedBy = "user")
	public Collection<Answer> getAnswer() {
		return answer;
	}
	
	public void setAnswer(Collection<Answer> answer) {
		this.answer = answer;
	}
	
	
	@Valid
	@OneToMany(mappedBy = "organiser")
	public Collection<Rendezvous> getOrganisedRendezvouses() {
		return organisedRendezvouses;
	}
	
	public void setOrganisedRendezvouses(Collection<Rendezvous> organisedRendezvouses) {
		this.organisedRendezvouses = organisedRendezvouses;
	}
	
	@Valid
	@ManyToMany(mappedBy = "attendants")
	public Collection<Rendezvous> getRsvpdRendezvouses() {
		return rsvpdRendezvouses;
	}
	
	public void setRsvpdRendezvouses(Collection<Rendezvous> rsvpdRendezvouses) {
		this.rsvpdRendezvouses = rsvpdRendezvouses;
	}
	
	
}
