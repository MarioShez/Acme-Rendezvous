package forms;

import java.sql.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;
import org.hibernate.validator.constraints.URL;
import org.springframework.format.annotation.DateTimeFormat;

import domain.GpsCoordinate;

public class RendezvousForm {

	// Constructors -----------------------------------------------------------

	public RendezvousForm() {
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

	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
	@SafeHtml
	public Date getMoment() {
		return moment;
	}

	public void setMoment(Date moment) {
		this.moment = moment;
	}

	@Valid
	@SafeHtml
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

}
