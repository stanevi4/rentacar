package by.grodno.ss.rentacar.webapp.component.menu;

import javax.inject.Inject;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.Panel;

import by.grodno.ss.rentacar.dataaccess.filters.CarFilter;
import by.grodno.ss.rentacar.datamodel.UserRole;
import by.grodno.ss.rentacar.service.UserService;
import by.grodno.ss.rentacar.webapp.app.AuthorizedSession;
import by.grodno.ss.rentacar.webapp.page.AbstractPage;
import by.grodno.ss.rentacar.webapp.page.MyBooking.MyBookingPage;
import by.grodno.ss.rentacar.webapp.page.admin.AdminPage;
import by.grodno.ss.rentacar.webapp.page.admin.SettingEditPage;
import by.grodno.ss.rentacar.webapp.page.car.ChooseCarPage;
import by.grodno.ss.rentacar.webapp.page.contact.ContactUsPage;
import by.grodno.ss.rentacar.webapp.page.faq.FaqPage;
import by.grodno.ss.rentacar.webapp.page.home.HomePage;
import by.grodno.ss.rentacar.webapp.page.locations.LocationsPage;
import by.grodno.ss.rentacar.webapp.page.login.LoginPage;
import by.grodno.ss.rentacar.webapp.page.reservation.ReservationPage;
import by.grodno.ss.rentacar.webapp.page.services.ServicesPage;

public class TopMenuPanel extends Panel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Class<? extends AbstractPage> selectedPage;
	
	@Inject
	private UserService userService;
	
	public TopMenuPanel(String id) {
		super(id);
	}

	public TopMenuPanel(String id, Class<? extends AbstractPage> selectedPage) {
		super(id);
		this.selectedPage = selectedPage;
	}

	@Override
	protected void onInitialize() {
		
		super.onInitialize();
		
		WebMarkupContainer liHomePage = new WebMarkupContainer("link-container-homepage");
		if (selectedPage.equals(HomePage.class)) {
			liHomePage.add(new AttributeModifier("class", "active"));
		}
		liHomePage.add(new Link("link-homepage") {
			@Override
			public void onClick() {
				setResponsePage(new HomePage());
			}
		});
		add(liHomePage);

		WebMarkupContainer liReservations = new WebMarkupContainer("link-container-reservations");
		if (selectedPage.equals(ReservationPage.class) || selectedPage.equals(ChooseCarPage.class)) {
			liReservations.add(new AttributeModifier("class", "active"));
		}
		liReservations.add(new Link("link-reservations") {
			@Override
			public void onClick() {
				setResponsePage(new ReservationPage(new CarFilter()));
			}
		});
		add(liReservations);
		
//		WebMarkupContainer liAboutUs = new WebMarkupContainer("link-container-about-us");
//		if (selectedPage.equals(AboutUsPage.class)) {
//			liAboutUs.add(new AttributeModifier("class", "active"));
//		}
//		liAboutUs.add(new Link("link-about-us") {
//			@Override
//			public void onClick() {
//				setResponsePage(new AboutUsPage());
//			}
//		});
//		add(liAboutUs);

		WebMarkupContainer liServices = new WebMarkupContainer("link-container-services");
		if (selectedPage.equals(ServicesPage.class)) {
			liServices.add(new AttributeModifier("class", "active"));
		}
		liServices.add(new Link("link-services") {
			@Override
			public void onClick() {
				setResponsePage(new ServicesPage());
			}
		});
		add(liServices);

		WebMarkupContainer liLocations = new WebMarkupContainer("link-container-locations");
		if (selectedPage.equals(LocationsPage.class)) {
			liLocations.add(new AttributeModifier("class", "active"));
		}
		liLocations.add(new Link("link-locations") {
			@Override
			public void onClick() {
				setResponsePage(new LocationsPage());
			}
		});
		add(liLocations);

		WebMarkupContainer liFaq = new WebMarkupContainer("link-container-faq");
		if (selectedPage.equals(FaqPage.class)) {
			liFaq.add(new AttributeModifier("class", "active"));
		}
		liFaq.add(new Link("link-faq") {
			@Override
			public void onClick() {
				setResponsePage(new FaqPage());
			}
		});
		add(liFaq);

		WebMarkupContainer liContactUs = new WebMarkupContainer("link-container-contact-us");
		if (selectedPage.equals(ContactUsPage.class)) {
			liContactUs.add(new AttributeModifier("class", "active"));
		}
		liContactUs.add(new Link("link-contact-us") {
			@Override
			public void onClick() {
				setResponsePage(new ContactUsPage());
			}
		});
		add(liContactUs);
		
		WebMarkupContainer liLogin = new WebMarkupContainer("link-container-login");
		if (selectedPage.equals(LoginPage.class)) {
			liLogin.add(new AttributeModifier("class", "active"));
		}
		liLogin.add(new Link("link-login") {
			@Override
			public void onClick() {
				setResponsePage(new LoginPage());
			}
		});
		liLogin.setVisible(!AuthorizedSession.get().isSignedIn());
		add(liLogin);
		
		WebMarkupContainer liLogOut = new WebMarkupContainer("link-container-logout");
		liLogOut.add(new Link("link-logout") {
			@Override
			public void onClick() {
				String email = AuthorizedSession.get().getLoggedUser().getEmail();
				getSession().invalidate();
				userService.setLog(email, false); //add log string
				setResponsePage(new HomePage());
			}
		});
		liLogOut.setVisible(AuthorizedSession.get().isSignedIn());
		add(liLogOut);
		
		WebMarkupContainer liBookings = new WebMarkupContainer("link-container-bookings");
		if (selectedPage.equals(MyBookingPage.class)) {
			liBookings.add(new AttributeModifier("class", "active"));
		}
		liBookings.add(new Link("link-bookings") {
			@Override
			public void onClick() {
				setResponsePage(new MyBookingPage());
			}
		});
		liBookings.setVisible(AuthorizedSession.get().isSignedIn() && AuthorizedSession.get().getLoggedUser().getRole().equals(UserRole.CLIENT));
		add(liBookings);
		
		WebMarkupContainer liAdmin = new WebMarkupContainer("link-container-admin");
		if (AdminPage.class.isAssignableFrom(selectedPage)) {
			liAdmin.add(new AttributeModifier("class", "active"));
		}
		liAdmin.add(new Link("link-admin") {
			@Override
			public void onClick() {
				setResponsePage(new SettingEditPage());
			}
		});
		boolean a = (AuthorizedSession.get().isSignedIn() && AuthorizedSession.get().getLoggedUser().getRole().equals(UserRole.ADMIN));
		boolean m = (AuthorizedSession.get().isSignedIn() && AuthorizedSession.get().getLoggedUser().getRole().equals(UserRole.MANAGER));
		liAdmin.setVisibilityAllowed(a || m);
		add(liAdmin);
	}
}
