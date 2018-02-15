package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
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
	
//	private Collection<Answer> answer;
//	
//	public Collection<Answer> getAnswer() {
//		return answer;
//	}
//	
//	@Valid
//	@NotNull
//	@OneToMany(mappedBy = "user")
//	public setAnswer(Collection<Answer> answer) {
//		this.answer = answer;
//	}
	
	
	
	
	
	
}
