package by.grodno.ss.rentacar.webapp.page.reservation;

import org.apache.wicket.model.IModel;
import by.grodno.ss.rentacar.dataaccess.filters.CarFilter;
import by.grodno.ss.rentacar.webapp.component.panel.ReservationPanel;


public class ReservationPanelReservation extends ReservationPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ReservationPanelReservation(String id, CarFilter filter) {
		super(id, filter);
	}

	public ReservationPanelReservation(String id, IModel<?> model, CarFilter filter) {
		super(id, model, filter);
	}
	
}
