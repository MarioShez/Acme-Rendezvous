package domain;

import java.sql.Date;
import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Access(AccessType.PROPERTY)
public class Rendezvous extends DomainEntity {

	// Constructors -----------------------------------------------------------

	public Rendezvous() {
		super();
	}

	// Attributes -------------------------------------------------------------

	private String name;
	private String description;
	private String picture;
	private Date moment;
	private GpsCoordinate gpsCoordinate;
	private boolean adult;
	private boolean finalVersion;
	private boolean deleted;

	@NotBlank
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@NotBlank
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@URL
	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
	public Date getMoment() {
		return moment;
	}

	public void setMoment(Date moment) {
		this.moment = moment;
	}

	@Valid
	public GpsCoordinate getGpsCoordinate() {
		return gpsCoordinate;
	}

	public void setGpsCoordinate(GpsCoordinate gpsCoordinate) {
		this.gpsCoordinate = gpsCoordinate;
	}

	public boolean getFinalVersion() {
		return finalVersion;
	}

	public void setFinalVersion(boolean finalVersion) {
		this.finalVersion = finalVersion;
	}

	public boolean getAdult() {
		return adult;
	}

	public void setAdult(boolean adult) {
		this.adult = adult;
	}

	public boolean getDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	// Relationships ----------------------------------------------------------

	private Collection<Announcement> announcements;
	private Collection<Comment> comments;
	private Collection<Question> questions;
	private User organiser;
	private Collection<User> attendants;
	private Collection<Rendezvous> linkedRendezvouses;

	@Valid
	@NotNull
	@OneToMany(mappedBy = "rendezvous")
	public Collection<Announcement> getAnnouncements() {
		return announcements;
	}

	public void setAnnouncements(Collection<Announcement> announcements) {
		this.announcements = announcements;
	}

	@Valid
	@NotNull
	@OneToMany(mappedBy = "rendezvous")
	public Collection<Comment> getComments() {
		return comments;
	}

	public void setComments(Collection<Comment> comments) {
		this.comments = comments;
	}

	@Valid
	@NotNull
	@OneToMany(mappedBy = "rendezvous")
	public Collection<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(Collection<Question> questions) {
		this.questions = questions;
	}

	@Valid
	@NotNull
	@ManyToOne(optional = false)
	public User getOrganiser() {
		return organiser;
	}

	public void setOrganiser(User organiser) {
		this.organiser = organiser;
	}

	@Valid
	@NotNull
	@ManyToMany
	public Collection<User> getAttendants() {
		return attendants;
	}

	public void setAttendants(Collection<User> attendants) {
		this.attendants = attendants;
	}

	@Valid
	@NotNull
	@OneToMany(mappedBy = "rendezvous0")
	public Collection<Rendezvous> getLinkedRendezvouses() {
		return linkedRendezvouses;
	}

	public void setLinkedRendezvouses(Collection<Rendezvous> linkedRendezvouses) {
		this.linkedRendezvouses = linkedRendezvouses;
	}

}
