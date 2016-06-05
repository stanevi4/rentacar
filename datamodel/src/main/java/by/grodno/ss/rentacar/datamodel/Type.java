package by.grodno.ss.rentacar.datamodel;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

@Entity
@Table(name = "type")
public class Type extends AbstractModel {

	@Column
	private String name;
	
	@Column
	private Integer numPassengers;
	
	@Column
	private Integer numBags;
	
	@Column
	private Integer numDoors;
	
	@Column
	@Enumerated(value = EnumType.ORDINAL)
	private TransmissionType transmissionType;
	
	@Column
	private BigDecimal pricePerHour;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getNumPassengers() {
		return numPassengers;
	}

	public void setNumPassengers(Integer numPassengers) {
		this.numPassengers = numPassengers;
	}

	public Integer getNumBags() {
		return numBags;
	}

	public void setNumBags(Integer numBags) {
		this.numBags = numBags;
	}

	public Integer getNumDoors() {
		return numDoors;
	}

	public void setNumDoors(Integer numDoors) {
		this.numDoors = numDoors;
	}

	public TransmissionType getTransmissionType() {
		return transmissionType;
	}

	public void setTransmissionType(TransmissionType transmissionType) {
		this.transmissionType = transmissionType;
	}

	public BigDecimal getPricePerHour() {
		return pricePerHour;
	}

	public void setPricePerHour(BigDecimal pricePerHour) {
		this.pricePerHour = pricePerHour;
	}
	
}
