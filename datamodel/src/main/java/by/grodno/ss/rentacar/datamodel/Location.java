package by.grodno.ss.rentacar.datamodel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "location")
public class Location extends AbstractModel {
	private static final long serialVersionUID = 1L;

	@Column
	private String name;
	
	@Column
	private Double lat;
	
	@Column 
	private Double lng;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getLat() {
		return lat;
	}

	public void setLat(Double lat) {
		this.lat = lat;
	}

	public Double getLng() {
		return lng;
	}

	public void setLng(Double lng) {
		this.lng = lng;
	}
}
