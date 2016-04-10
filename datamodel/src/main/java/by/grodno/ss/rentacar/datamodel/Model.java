package by.grodno.ss.rentacar.datamodel;

public class Model extends AbstractModel {

	private Brand brand;
	private String modelName;

	public Brand getBrand() {
		return brand;
	}

	public void setBrand(Brand brand) {
		this.brand = brand;
	}

	public String getModelName() {
		return modelName;
	}

	public void setModelName(String modelName) {
		this.modelName = modelName;
	}

}
