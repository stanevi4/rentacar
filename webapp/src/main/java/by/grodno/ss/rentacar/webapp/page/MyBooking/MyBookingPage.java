package by.grodno.ss.rentacar.webapp.page.MyBooking;

import org.apache.wicket.markup.html.link.Link;

import by.grodno.ss.rentacar.webapp.page.AbstractPage;

public class MyBookingPage extends AbstractPage {
	private static final long serialVersionUID = 1L;

	public MyBookingPage() {
		super();
	}
	
	@Override
	protected void onInitialize() {
		super.onInitialize();
		
		add(new Link<Void>("change-profile"){
			private static final long serialVersionUID = 1L;

			@Override
			public void onClick() {
				setResponsePage(new MyProfilePage());
			}
			
		});

		add(new MyBookingListPanel("list-bookings"));
		}
}
