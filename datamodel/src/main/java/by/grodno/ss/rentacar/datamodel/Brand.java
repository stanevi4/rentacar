package by.grodno.ss.rentacar.datamodel;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

@Entity
public class Brand extends AbstractModel {

	@Column
	private String brandName;
	
	@OneToMany(mappedBy = "brand", fetch = FetchType.LAZY)
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
