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
	private Collection<Rendezvous> organisedRendezvous;
	private Collection<Rendezvous> rsvpdRendezvous;
	
	@Valid
	@NotNull
	@OneToMany(mappedBy = "user")
	public Collection<Answer> getAnswer() {
		return answer;
	}
	
	public void setAnswer(Collection<Answer> answer) {
		this.answer = answer;
	}
	
	
	@Valid
	@NotNull
	@OneToMany(mappedBy = "organiser")
	public Collection<Rendezvous> getOrganisedRendezvous() {
		return organisedRendezvous;
	}
	
	public void setOrganisedRendezvous(Collection<Rendezvous> organisedRendezvous) {
		this.organisedRendezvous = organisedRendezvous;
	}
	
	@Valid
	@NotNull
	@ManyToMany(mappedBy = "attendants")
	public Collection<Rendezvous> getRsvpdRendezvous() {
		return rsvpdRendezvous;
	}
	
	public void setRsvpdRendezvous(Collection<Rendezvous> rsvpdRendezvous) {
		this.rsvpdRendezvous = rsvpdRendezvous;
	}
	
	
}
