
package domain.domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.Valid;

import org.hibernate.validator.constraints.NotBlank;

import domain.DomainEntity;

@Entity
@Access(AccessType.PROPERTY)
public class Answer extends DomainEntity {

	private String	content;


	@NotBlank
	public String getContent() {
		return this.content;
	}

	public void setContent(final String title) {
		this.content = title;
	}


	//Relationships

	private Question	question;


	@Valid
	@ManyToOne
	public Question getQuestion() {
		return this.question;
	}

	public void setTrips(final Question question) {
		this.question = question;
	}

}
