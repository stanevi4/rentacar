package by.grodno.ss.rentacar.datamodel;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "booking")
public class Booking extends AbstractModel {
	private static final long serialVersionUID = 1L;
	
	@Column
	private Date created;
	
	@ManyToOne(targetEntity = UserProfile.class, fetch = FetchType.LAZY)
	private UserProfile client;
	
	@ManyToOne(targetEntity = Location.class, fetch = FetchType.LAZY)
	private Location locationFrom;
	
	@ManyToOne(targetEntity = Location.class, fetch = FetchType.LAZY)
	private Location locationTo;	
	

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public UserProfile getClient() {
		return client;
	}

	public void setClient(UserProfile client) {
		this.client = client;
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
