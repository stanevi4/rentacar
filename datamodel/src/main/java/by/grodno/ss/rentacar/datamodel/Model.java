package by.grodno.ss.rentacar.datamodel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

@Entity
public class Model extends AbstractModel {

	@Column
	private String modelName;

	@ManyToOne(targetEntity = Brand.class, fetch = FetchType.LAZY)
	private Brand brand;

	public String getModelName() {
		return modelName;
	}

	public void setModelName(String modelName) {
		this.modelName = modelName;
	}

	public Brand getBrand() {
		return brand;
	}

	public void setBrand(Brand brand) {
		this.brand = brand;
	}

}
