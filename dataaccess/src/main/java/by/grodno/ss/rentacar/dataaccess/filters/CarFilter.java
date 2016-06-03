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
	
	
}
