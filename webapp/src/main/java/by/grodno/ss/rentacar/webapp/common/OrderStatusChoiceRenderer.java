package by.grodno.ss.rentacar.webapp.common;

import org.apache.wicket.markup.html.form.ChoiceRenderer;

import by.grodno.ss.rentacar.datamodel.OrderStatus;

public class OrderStatusChoiceRenderer extends ChoiceRenderer<OrderStatus> {
	private static final long serialVersionUID = 1L;
	public static final OrderStatusChoiceRenderer INSTANCE = new OrderStatusChoiceRenderer();

	public OrderStatusChoiceRenderer() {
		super();
	}

	@Override
	public Object getDisplayValue(OrderStatus object) {
		return object.name();
	}

	@Override
	public String getIdValue(OrderStatus object, int index) {
		return String.valueOf(object.ordinal());
	}
}
