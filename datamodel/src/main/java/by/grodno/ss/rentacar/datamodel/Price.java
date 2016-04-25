package by.grodno.ss.rentacar.datamodel;

import java.util.Date;

public class Price extends AbstractModel {
	
	private Date created;
	private Date dateFrom;
	private Double value;
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	public Date getDateFrom() {
		return dateFrom;
	}
	public void setDateFrom(Date dateFrom) {
		this.dateFrom = dateFrom;
	}
	public Double getValue() {
		return value;
	}
	public void setValue(Double value) {
		this.value = value;
	}

}
