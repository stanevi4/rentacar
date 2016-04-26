package by.grodno.ss.rentacar.datamodel;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Damage extends AbstractModel {

	@Column
	private String damage;

	public String getDamage() {
		return damage;
	}

	public void setDamage(String damage) {
		this.damage = damage;
	}

}
