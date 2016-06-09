package by.grodno.ss.rentacar.webapp.page.admin;

import org.apache.wicket.request.mapper.parameter.PageParameters;

import by.grodno.ss.rentacar.webapp.page.admin.panel.ReservationListPanel;

public class ReservationsEditPage extends  AdminPage{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ReservationsEditPage() {
		super();
	}

	public ReservationsEditPage(PageParameters parameters) {
		super(parameters);
	}
	@Override
	protected void onInitialize() {
		super.onInitialize();

		add(new ReservationListPanel("reservation-panel"));
	}
}
