package by.grodno.ss.rentacar.datamodel;

import java.util.Date;

public class OrderHistory extends AbstractModel {

	private Order order;
	private Date created;
	private OrderStatus orderStatus;

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public OrderStatus getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(OrderStatus orderStatus) {
		this.orderStatus = orderStatus;
	}

}
