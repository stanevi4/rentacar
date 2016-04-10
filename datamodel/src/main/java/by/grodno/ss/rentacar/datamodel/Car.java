package by.grodno.ss.rentacar.datamodel;

public class Car extends AbstractModel {

	private Model model;
	private String regNumber;
	private Integer yearProdaction;
	private TransmissionType transmissionType;
	private Integer power;
	private Boolean actualCar;
	private Byte image;

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

	public Boolean getActualCar() {
		return actualCar;
	}

	public void setActualCar(Boolean actualCar) {
		this.actualCar = actualCar;
	}

	public Byte getImage() {
		return image;
	}

	public void setImage(Byte image) {
		this.image = image;
	}

}
