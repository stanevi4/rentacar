package by.grodno.ss.rentacar.webapp.page.admin.panel;

import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;

import by.grodno.ss.rentacar.datamodel.Order;

public class ReservationEditPanel extends Panel {
	private static final long serialVersionUID = 1L;
	private Order order;
	
	public ReservationEditPanel(String id, Order order) {
		super(id);
		this.order = order;
	}

	public ReservationEditPanel(String id, IModel<?> model, Order order) {
		super(id, model);
		this.order = order;
	}

}
