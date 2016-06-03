package by.grodno.ss.rentacar.dataaccess.filters;

import java.util.Date;

import by.grodno.ss.rentacar.datamodel.Location;

public class CarFilter extends AbstractFilter {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Date dateFrom;
	private Date dateTo;
	private Location locationFrom;
	private Location locationTo;

	public CarFilter() {
		super();
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

	public Location getLocationFrom() {
		return locationFrom;
	}

	public void setLocationFrom(Location locationFrom) {
		this.locationFrom = locationFrom;
	}

	public Location getLocationTo() {
		return locationTo;
	}

	public void setLocationTo(Location locationTo) {
		this.locationTo = locationTo;
	}
	
	
}
