package by.grodno.ss.rentacar.webapp.page.home;

import org.apache.wicket.model.IModel;

import by.grodno.ss.rentacar.dataaccess.filters.CarFilter;
import by.grodno.ss.rentacar.webapp.page.reservation.ReservationPanelReservation;

public class ReservationPanelHome extends ReservationPanelReservation {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public ReservationPanelHome(String id, CarFilter filter) {
		super(id, filter);
	}

	public ReservationPanelHome(String id, IModel<?> model, CarFilter filter) {
		super(id, model, filter);
	}
	
}
