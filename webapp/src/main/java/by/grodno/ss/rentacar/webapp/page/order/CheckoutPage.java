package by.grodno.ss.rentacar.webapp.page.order;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;

import javax.inject.Inject;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.SubmitLink;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.image.Image;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.request.resource.ContextRelativeResource;
import org.apache.wicket.validation.validator.PatternValidator;
import org.apache.wicket.validation.validator.StringValidator;

import by.grodno.ss.rentacar.dataaccess.filters.CarFilter;
import by.grodno.ss.rentacar.datamodel.Booking;
import by.grodno.ss.rentacar.datamodel.Car;
import by.grodno.ss.rentacar.datamodel.Currency;
import by.grodno.ss.rentacar.datamodel.OrderStatus;
import by.grodno.ss.rentacar.datamodel.UserCredentials;
import by.grodno.ss.rentacar.datamodel.UserProfile;
import by.grodno.ss.rentacar.datamodel.UserRole;
import by.grodno.ss.rentacar.service.BookingService;
import by.grodno.ss.rentacar.service.SettingService;
import by.grodno.ss.rentacar.service.UserService;
import by.grodno.ss.rentacar.webapp.app.AuthorizedSession;
import by.grodno.ss.rentacar.webapp.page.AbstractPage;
import by.grodno.ss.rentacar.webapp.page.car.ChooseCarPage;
import by.grodno.ss.rentacar.webapp.page.confirm.ConfirmPage;
import by.grodno.ss.rentacar.webapp.page.reservation.ReservationPage;
import de.agilecoders.wicket.core.markup.html.bootstrap.common.NotificationPanel;
import de.agilecoders.wicket.extensions.markup.html.bootstrap.form.DateTextField;
import de.agilecoders.wicket.extensions.markup.html.bootstrap.form.DateTextFieldConfig;

public class CheckoutPage extends AbstractPage {
	private static final long serialVersionUID = 1L;
	private Car car;
	private CarFilter filter;
	private UserProfile userProfile;
	private UserCredentials userCredential;
	private Booking booking;
	private BigDecimal totalPrice;
	@Inject
	private BookingService bookingService;
	@Inject
	private SettingService settingService;
	@Inject
	private UserService userService;
	private String IMAGE_FOLDER = "/images/cars/";

	public CheckoutPage(Car car, CarFilter filter) {
		super();
		this.car = car;
		this.filter = filter;
		if (AuthorizedSession.get().isSignedIn()) {
			this.userProfile = AuthorizedSession.get().getLoggedUser().getUserProfile();
			this.userCredential = userService.getCredentials(AuthorizedSession.get().getLoggedUser().getId());
		} else {
			this.userProfile = new UserProfile();
			this.userCredential = new UserCredentials();
		}
		this.booking = new Booking();
		booking.setCar(car);
		booking.setDateFrom(filter.getDateFrom());
		booking.setDateTo(filter.getDateTo());
		booking.setLocationFrom(filter.getLocationFrom());
		booking.setLocationTo(filter.getLocationTo());
		// liBookings.setVisible(AuthorizedSession.get().isSignedIn() &&
		// AuthorizedSession.get().getLoggedUser().getRole().equals(UserRole.CLIENT));
	}

	public CheckoutPage(PageParameters parameters, Car car, CarFilter filter) {
		super(parameters);
		this.car = car;
		this.filter = filter;
	}

	@Override
	protected void onInitialize() {
		super.onInitialize();
		final NotificationPanel feedback = new NotificationPanel("feedbackpanel");

		SimpleDateFormat dt = new SimpleDateFormat("dd.MM.yyy HH:mm");
		add(new Label("duration", bookingService.convertDurationToString(filter.getDateFrom(), filter.getDateTo())));
		add(new Label("dateFrom", dt.format(filter.getDateFrom())));
		add(new Label("locationFrom", filter.getLocationFrom().getName()));
		add(new Label("dateTo", dt.format(filter.getDateTo())));
		add(new Label("locationTo", filter.getLocationTo().getName()));
		add(new Link<Void>("link-change") {
			private static final long serialVersionUID = 1L;

			@Override
			public void onClick() {
				setResponsePage(new ReservationPage(filter));
			}
		});
		add(new Link<Void>("link-change-car") {
			private static final long serialVersionUID = 1L;

			@Override
			public void onClick() {
				setResponsePage(new ChooseCarPage(filter));
			}
		});
		addImage(car);
		add(new Label("name", car.getName()));
		add(new Label("type", car.getType().getName()));
		add(new Label("pass", car.getType().getNumPassengers()));
		add(new Label("bags", car.getType().getNumBags()));
		add(new Label("doors", car.getType().getNumDoors()));
		add(new Label("trans", car.getType().getTransmissionType()));

		add(new Label("rental-duration",
				bookingService.convertDurationToString(filter.getDateFrom(), filter.getDateTo())));
		BigDecimal pricePerHour = car.getType().getPricePerHour();
		totalPrice = bookingService.getTotalPrice(filter.getDateFrom(), filter.getDateTo(), pricePerHour);
		BigDecimal pricePerDay = bookingService.getPricePerDay(pricePerHour);
		int percent = settingService.get().getDepositPayment();
		BigDecimal requiredDeposit = bookingService.getRequiredDeposit(totalPrice, percent);
		add(new Label("pricePerHour", pricePerHour.toString()));
		add(new Label("total", totalPrice.toString()));
		add(new Label("pricePerDay", pricePerDay.toString()));
		add(new Label("requiredDeposit", requiredDeposit.toString()));
		add(new Label("total1", totalPrice.toString()));
		add(new Label("percent", percent));
		add(getPriceIcon("iconPrice1"));
		add(getPriceIcon("iconPrice2"));
		add(getPriceIcon("iconPrice3"));
		add(getPriceIcon("iconPrice4"));
		add(getPriceIcon("iconPrice5"));

		// -----checkout form------------
		Form<UserProfile> form = new Form<UserProfile>("form-checkout",
				new CompoundPropertyModel<UserProfile>(userProfile));
		// form.add(new FeedbackPanel("feedbackpanel"));

		TextField<String> firstName = new TextField<String>("firstName", new PropertyModel<>(userProfile, "firstName"));
		firstName.setRequired(true);
		firstName.add(StringValidator.maximumLength(100));
		firstName.add(StringValidator.minimumLength(2));
		firstName.add(new PatternValidator("[A-Za-z]+"));
		form.add(firstName);

		TextField<String> lastName = new TextField<String>("lastName", new PropertyModel<>(userProfile, "lastName"));
		lastName.setRequired(true);
		lastName.add(StringValidator.maximumLength(100));
		lastName.add(StringValidator.minimumLength(2));
		firstName.add(new PatternValidator("[A-Za-z]+"));
		form.add(lastName);

		TextField<String> phone = new TextField<String>("phone", new PropertyModel<>(userProfile, "phoneNumber"));
		phone.setRequired(true);
		phone.add(StringValidator.maximumLength(100));
		phone.add(StringValidator.minimumLength(2));
		phone.add(new PatternValidator("[0-9+()-]+"));
		form.add(phone);

		DateTextFieldConfig config = new DateTextFieldConfig();
		config.withLanguage(AuthorizedSession.get().getLocale().getLanguage());
		config.withFormat("dd.MM.yyyy");
		DateTextField dateBirth = new DateTextField("date-birth", new PropertyModel<>(userProfile, "birthDay"), config);
		form.add(dateBirth);

		TextField<String> licNumber = new TextField<String>("lic-number",
				new PropertyModel<>(userProfile, "licenseNumber"));
		licNumber.add(StringValidator.maximumLength(100));
		licNumber.add(StringValidator.minimumLength(2));
		licNumber.add(new PatternValidator("[A-Za-z0-9]+"));
		form.add(licNumber);

		TextField<String> note = new TextField<String>("note", new PropertyModel<>(booking, "note"));
		note.add(StringValidator.maximumLength(500));
		form.add(note);

		TextField<String> address = new TextField<String>("address", new PropertyModel<>(userProfile, "address"));
		address.add(StringValidator.maximumLength(100));
		address.add(StringValidator.minimumLength(2));
		address.add(new PatternValidator("[A-Za-z0-9 /-]+"));
		form.add(address);

		TextField<String> city = new TextField<String>("city", new PropertyModel<>(userProfile, "city"));
		city.add(StringValidator.maximumLength(100));
		city.add(StringValidator.minimumLength(2));
		city.add(new PatternValidator("[A-Za-z0-9]+"));
		form.add(city);

		TextField<String> region = new TextField<String>("region", new PropertyModel<>(userProfile, "region"));
		region.add(StringValidator.maximumLength(100));
		region.add(StringValidator.minimumLength(2));
		region.add(new PatternValidator("[A-Za-z0-9]+"));
		form.add(region);

		TextField<String> zip = new TextField<String>("zip-code", new PropertyModel<>(userProfile, "zipCode"));
		zip.add(StringValidator.maximumLength(20));
		zip.add(StringValidator.minimumLength(2));
		zip.add(new PatternValidator("[0-9]+"));
		form.add(zip);

		form.add(new SubmitLink("button-confirm") {
			private static final long serialVersionUID = 1L;

			@Override
			public void onSubmit() {
				if (userProfile.getId() == null) {
					userCredential.setEmail("unregistered user");
					userCredential.setPassword("pswd");
					userCredential.setRole(UserRole.UNREGISTERED);
					userService.register(userProfile, userCredential);
				} else {
					userService.update(userProfile);
				}
				booking.setSumm(totalPrice);
				booking.setClient(userProfile);
				booking.setOrderStatus(OrderStatus.pending);
				bookingService.saveOrUpdate(booking);
				setResponsePage(new ConfirmPage());
			}
		});

		form.add(feedback);
		add(form);

	}

	private void addImage(Car car) {
		Image carImage = new Image("image", new ContextRelativeResource(IMAGE_FOLDER + car.getImage()));
		// carImage.add(new AttributeModifier("height", "220"));
		// carImage.add(new AttributeModifier("width", "200"));
		add(carImage);
	}

	private WebMarkupContainer getPriceIcon(String id) {
		Currency currency = settingService.get().getCurrency();
		WebMarkupContainer i = new WebMarkupContainer(id);
		if (currency.equals(Currency.USD)) {
			i.add(new AttributeModifier("class", "fa fa-usd"));
		} else if (currency.equals(Currency.EUR)) {
			i.add(new AttributeModifier("class", "fa fa-eur"));
		} else if (currency.equals(Currency.GBR)) {
			i.add(new AttributeModifier("class", "fa fa-gbp"));
		} else {
			i.add(new AttributeModifier("class", "fa fa-rub"));
		}
		return i;
	}
}
