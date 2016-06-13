package by.grodno.ss.rentacar.dataaccess.filters;

import java.util.Date;

import by.grodno.ss.rentacar.datamodel.UserRole;

public class UserFilter extends AbstractFilter{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String firstName;
	private String lastName;
	private Date createdFrom;
	private Date createdTo;
	private String email;
	private boolean isFetchCredentials;
	private UserRole role;
	
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
	public Date getCreatedFrom() {
		return createdFrom;
	}
	public void setCreatedFrom(Date createdFrom) {
		this.createdFrom = createdFrom;
	}
	public Date getCreatedTo() {
		return createdTo;
	}
	public void setCreatedTo(Date createdTo) {
		this.createdTo = createdTo;
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
	public UserRole getRole() {
		return role;
	}
	public void setRole(UserRole role) {
		this.role = role;
	}
	
}
