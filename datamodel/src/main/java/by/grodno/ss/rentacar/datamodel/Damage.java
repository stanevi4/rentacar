package by.grodno.ss.rentacar.datamodel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

@Entity
public class Damage extends AbstractModel {

	@Column
	private String damage;
	
	@ManyToOne(targetEntity = Order.class, fetch = FetchType.LAZY)
	private Order order;
	
	public String getDamage() {
		return damage;
	}

	public void setDamage(String damage) {
		this.damage = damage;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

}
