package by.grodno.ss.rentacar.datamodel;

import java.util.List;

public class Brand extends AbstractModel {

	private String brandName;
	private List<Model> model;

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public List<Model> getModels() {
		return model;
	}

	public void setModels(List<Model> models) {
		this.model = models;
	}

}
