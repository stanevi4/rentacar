package by.grodno.ss.rentacar.dataaccess.filters;

import java.util.Date;

import by.grodno.ss.rentacar.datamodel.CarStatus;
import by.grodno.ss.rentacar.datamodel.Location;
import by.grodno.ss.rentacar.datamodel.Type;

public class CarFilter extends AbstractFilter {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	private Date dateFrom;
	private Date dateTo;
	private Location locationFrom;
	private Location LocationTo;
	private Location location;
	private Type type;
	private CarStatus carStatus;
	private boolean isFetchTypes;
	private boolean isFetchLocations;

	public CarFilter() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getDateFrom() {
		return dateFrom;
	}

	public void setDateFrom(Date dateFrom) {
		this.dateFrom = dateFrom;
	}

	public Date getDateTo() {
		return dateTo;
	}

	public void setDateTo(Date dateTo) {
		this.dateTo = dateTo;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public CarStatus getCarStatus() {
		return carStatus;
	}

	public void setCarStatus(CarStatus carStatus) {
		this.carStatus = carStatus;
	}

	public boolean isFetchTypes() {
		return isFetchTypes;
	}

	public void setFetchTypes(boolean isFetchTypes) {
		this.isFetchTypes = isFetchTypes;
	}

	public boolean isFetchLocations() {
		return isFetchLocations;
	}

	public void setFetchLocations(boolean isFetchLocations) {
		this.isFetchLocations = isFetchLocations;
	}

	public Location getLocationFrom() {
		return locationFrom;
	}

	public void setLocationFrom(Location locationFrom) {
		this.locationFrom = locationFrom;
	}

	public Location getLocationTo() {
		return LocationTo;
	}

	public void setLocationTo(Location locationTo) {
		LocationTo = locationTo;
	}
	
	
}
