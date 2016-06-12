package by.grodno.ss.rentacar.webapp.page.MyBooking;

import by.grodno.ss.rentacar.webapp.page.AbstractPage;

public class MyBookingPage extends AbstractPage {
	private static final long serialVersionUID = 1L;

	public MyBookingPage() {
		super();
	}
	
	@Override
	protected void onInitialize() {
		super.onInitialize();

		add(new MyBookingListPanel("list-bookings"));
		}
}
