package by.grodno.ss.rentacar.webapp.page.reservation;

import org.apache.wicket.request.mapper.parameter.PageParameters;

import by.grodno.ss.rentacar.dataaccess.filters.CarFilter;
import by.grodno.ss.rentacar.webapp.page.AbstractPage;

public class ReservationPage extends AbstractPage {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private CarFilter filter;

	public ReservationPage(CarFilter filter) {
		super();
		this.filter = filter;
	}

	public ReservationPage(PageParameters parameters, CarFilter filter) {
		super(parameters);
		this.filter = filter;
	}
	
	@Override
	protected void onInitialize() {
		super.onInitialize();
		
		add(new ReservationPanelReservation("reservation-panel", filter));
	}
}
