package by.grodno.ss.rentacar.webapp.component.datepicker;

import java.io.Serializable;
import java.util.Date;

public class DatePickerOptions implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final String elementId;
	private String viewMode;
	private String format;
	private Date minDate;
	private Date maxDate;

	public DatePickerOptions(String elementId) {
		this.elementId = elementId;
		this.viewMode = "days";
		this.format = "DD.MM.YYYY";
	}

	public DatePickerOptions(String elementId, Date minDate) {
		this.elementId = elementId;
		this.viewMode = "days";
		this.format = "DD.MM.YYYY";
		this.minDate = minDate;
	}

	public DatePickerOptions(String elementId, String viewMode, String format) {
		this.elementId = elementId;
		this.viewMode = viewMode;
		this.format = format;
	}

	public String getViewMode() {
		return viewMode;
	}

	public void setViewMode(String viewMode) {
		this.viewMode = viewMode;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public Date getMinDate() {
		return minDate;
	}

	public void setMinDate(Date minDate) {
		this.minDate = minDate;
	}

	public Date getMaxDate() {
		return maxDate;
	}

	public void setMaxDate(Date maxDate) {
		this.maxDate = maxDate;
	}

	public String getElementId() {
		return elementId;
	}

}
