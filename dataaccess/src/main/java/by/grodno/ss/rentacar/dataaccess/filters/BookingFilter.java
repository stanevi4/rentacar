package by.grodno.ss.rentacar.dataaccess.filters;

import java.util.Date;

public class BookingFilter extends AbstractFilter {
	private static final long serialVersionUID = 1L;

	private Date created;

	public BookingFilter() {
		super();
	}
	
	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}
}
