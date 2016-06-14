package by.grodno.ss.rentacar.datamodel;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "car")
public class Car extends AbstractModel {

	private static final long serialVersionUID = 1L;

	@Column
	private String name;
	
	@ManyToOne(targetEntity = Location.class, fetch = FetchType.LAZY)
	//@JoinColumn(name = "location_id", nullable = false)
	private Location location;
	
	@ManyToOne(targetEntity = Type.class, fetch = FetchType.LAZY)
	//@JoinColumn(name = "type_id", nullable = false)
	private Type type;
	
    @OneToMany(mappedBy = "car", fetch = FetchType.LAZY)
    private List<Booking> bookings;
	
	@Column
	@Enumerated(value = EnumType.ORDINAL)
	private CarStatus carStatus;
	
	@Column
	private String regNumber;
	
	@Column
	private Integer yearProdaction;
	
	@Column
	private String description;
	
	@Column
	private String image;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public String getRegNumber() {
		return regNumber;
	}

	public void setRegNumber(String regNumber) {
		this.regNumber = regNumber;
	}

	public Integer getYearProdaction() {
		return yearProdaction;
	}

	public void setYearProdaction(Integer yearProdaction) {
		this.yearProdaction = yearProdaction;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}
	
}
