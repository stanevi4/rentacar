package by.grodno.ss.rentacar.dataaccess.filters;

import java.util.Date;

public class UserFilter extends AbstractFilter{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String firstName;
	private String lastName;
	private Date startDateCreated;
	private Date endDateCreated;
	private String email;
	private boolean isFetchCredentials;
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public Date getStartDateCreated() {
		return startDateCreated;
	}
	public void setStartDateCreated(Date startDateCreated) {
		this.startDateCreated = startDateCreated;
	}
	public Date getEndDateCreated() {
		return endDateCreated;
	}
	public void setEndDateCreated(Date endDateCreated) {
		this.endDateCreated = endDateCreated;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public boolean isFetchCredentials() {
		return isFetchCredentials;
	}
	public void setFetchCredentials(boolean isFetchCredentials) {
		this.isFetchCredentials = isFetchCredentials;
	}
	
}
