package by.grodno.ss.rentacar.webapp.page.home;
import by.grodno.ss.rentacar.dataaccess.filters.CarFilter;
import by.grodno.ss.rentacar.webapp.page.AbstractPage;

public class HomePage extends AbstractPage {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public HomePage() {
		super();
	}

	@Override
	protected void onInitialize() {
		super.onInitialize();

		add(new ReservationPanelHome("reservation-panel", new CarFilter()));

	}

}
