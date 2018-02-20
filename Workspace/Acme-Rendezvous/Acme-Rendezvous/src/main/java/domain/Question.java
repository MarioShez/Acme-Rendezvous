
package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.Valid;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Access(AccessType.PROPERTY)
public class Question extends DomainEntity {

	private String	content;


	@NotBlank
	public String getContent() {
		return this.content;
	}

	public void setContent(final String title) {
		this.content = title;
	}


	//Relationships

	private Collection<Answer>	answers;
	private Rendezvous			rendezvous;


	@Valid
	@OneToMany
	public Collection<Answer> getAnswers() {
		return this.answers;
	}

	public void setAnswers(final Collection<Answer> answers) {
		this.answers = answers;
	}

	@ManyToOne
	public Rendezvous getRendezvous() {
		return this.rendezvous;
	}

	public void setRendezvous(final Rendezvous rendezvous) {
		this.rendezvous = rendezvous;
	}

}
