package by.grodno.ss.rentacar.datamodel;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "brand")
public class Brand extends AbstractModel {

	@Column
	private String brandName;
	
	@OneToMany(mappedBy = "brand", fetch = FetchType.LAZY)
	private Set<Model> models= new HashSet<>(0);

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public Set<Model> getModels() {
		return models;
	}

	public void setModels(Set<Model> models) {
		this.models = models;
	}

}
