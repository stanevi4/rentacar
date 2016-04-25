package by.grodno.ss.rentacar.datamodel;

import java.util.List;

public class Car extends AbstractModel {

	private Model model;
	private String regNumber;
	private Integer yearProdaction;
	private TransmissionType transmissionType;
	private Integer power;
	private String description;
	private String image;
	private CarStatus carStatus;
	private List<Price> price;

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

	public List<Price> getPrice() {
		return price;
	}

	public void setPrice(List<Price> price) {
		this.price = price;
	}

}
