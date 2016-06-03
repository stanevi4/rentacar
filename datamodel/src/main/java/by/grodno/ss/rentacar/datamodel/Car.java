package by.grodno.ss.rentacar.datamodel;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "car")
public class Car extends AbstractModel {

	@ManyToOne(targetEntity = Model.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "model_id", nullable = false)
	private Model model;
	
	@ManyToOne(targetEntity = Location.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "location_id", nullable = false)
	private Location location;
	
	@Column
	private String regNumber;
	
	@Column
	private Integer yearProdaction;
	
	@Column
	@Enumerated(value = EnumType.STRING)
	private TransmissionType transmissionType;
	
	@Column
	private Integer power;
	
	@Column
	private String description;
	
	@Column
	private String image;
	
	@Column
	@Enumerated(value = EnumType.STRING)
	private CarStatus carStatus;
	
	@Column
	private BigDecimal price;
	
	public Model getModel() {
		return model;
	}

	public void setModel(Model model) {
		this.model = model;
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

	public TransmissionType getTransmissionType() {
		return transmissionType;
	}

	public void setTransmissionType(TransmissionType transmissionType) {
		this.transmissionType = transmissionType;
	}

	public Integer getPower() {
		return power;
	}

	public void setPower(Integer power) {
		this.power = power;
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

	public CarStatus getCarStatus() {
		return carStatus;
	}

	public void setCarStatus(CarStatus carStatus) {
		this.carStatus = carStatus;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	
}
