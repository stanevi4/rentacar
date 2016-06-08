package by.grodno.ss.rentacar.dataaccess.filters;

import java.math.BigDecimal;

import by.grodno.ss.rentacar.datamodel.TransmissionType;

public class TypeFilter extends AbstractFilter {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	private Integer numPassengers;
	private Integer numBags;
	private Integer numDoors;
	private TransmissionType transmissionType;
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
