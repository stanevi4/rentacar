package by.grodno.ss.rentacar.webapp.page.admin.panel;

import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;

import by.grodno.ss.rentacar.datamodel.Booking;
import by.grodno.ss.rentacar.datamodel.Order;

public class ReservationEditPanel extends Panel {
	private static final long serialVersionUID = 1L;
	private Booking booking;
	
	public ReservationEditPanel(String id, Booking booking) {
		super(id);
		this.booking = booking;
	}

	public ReservationEditPanel(String id, IModel<?> model, Booking booking) {
		super(id, model);
		this.booking = booking;
	}

}
