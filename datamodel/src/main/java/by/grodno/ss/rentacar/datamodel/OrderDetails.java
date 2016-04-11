package by.grodno.ss.rentacar.datamodel;

public class OrderDetails extends AbstractModel {

	private Order order;
	private Reason reason;
	private Damage damage;

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public Reason getReason() {
		return reason;
	}

	public void setReason(Reason reason) {
		this.reason = reason;
	}

	public Damage getDamage() {
		return damage;
	}

	public void setDamage(Damage damage) {
		this.damage = damage;
	}

}
