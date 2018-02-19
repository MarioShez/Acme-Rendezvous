package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Embeddable;
import javax.validation.constraints.Digits;

import org.hibernate.validator.constraints.Range;

@Embeddable
@Access(AccessType.PROPERTY)
public class GpsCoordinates {

	// Attributes
	
	private Double latitude;
	private Double longitude;
	
	@Range(min = -90, max = 90)
	@Digits(integer = 3, fraction = 5)
	public Double getLatitude() {
		return latitude;
	}
	
	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}
	
	@Range(min = -180, max = 180)
	@Digits(integer = 3, fraction = 5)
	public Double getLongitude() {
		return longitude;
	}
	
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}
	
	
}
