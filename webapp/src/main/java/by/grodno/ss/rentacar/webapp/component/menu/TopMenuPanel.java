package by.grodno.ss.rentacar.webapp.component.menu;

import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.Panel;

import by.grodno.ss.rentacar.webapp.page.about.AboutUsPage;
import by.grodno.ss.rentacar.webapp.page.contact.ContactUsPage;
import by.grodno.ss.rentacar.webapp.page.faq.FaqPage;
import by.grodno.ss.rentacar.webapp.page.home.HomePage;
import by.grodno.ss.rentacar.webapp.page.locations.LocationsPage;
import by.grodno.ss.rentacar.webapp.page.reservation.ReservationPage;
import by.grodno.ss.rentacar.webapp.page.services.ServicesPage;

public class TopMenuPanel extends Panel {

	public TopMenuPanel(String id) {
		super(id);
	}

	@Override
	protected void onInitialize() {
		super.onInitialize();

		add(new Link("link-homepage") {
			@Override
			public void onClick() {
				setResponsePage(new HomePage());
			}
		});

		add(new Link("link-reservations") {
			@Override
			public void onClick() {
				setResponsePage(new ReservationPage());
			}
		});

		add(new Link("link-about-us") {
			@Override
			public void onClick() {
				setResponsePage(new AboutUsPage());
			}
		});

		add(new Link("link-services") {
			@Override
			public void onClick() {
				setResponsePage(new ServicesPage());
			}
		});

		add(new Link("link-locations") {
			@Override
			public void onClick() {
				setResponsePage(new LocationsPage());
			}
		});

		add(new Link("link-faq") {
			@Override
			public void onClick() {
				setResponsePage(new FaqPage());
			}
		});

		add(new Link("link-contact-us") {
			@Override
			public void onClick() {
				setResponsePage(new ContactUsPage());
			}
		});
	}

}
