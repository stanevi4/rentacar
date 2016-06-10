package by.grodno.ss.rentacar.webapp.page.admin.panel;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.NumberTextField;
import org.apache.wicket.markup.html.form.SubmitLink;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.form.upload.FileUpload;
import org.apache.wicket.markup.html.form.upload.FileUploadField;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.util.file.File;
import org.apache.wicket.util.lang.Bytes;
import org.apache.wicket.validation.validator.PatternValidator;
import org.apache.wicket.validation.validator.StringValidator;

import by.grodno.ss.rentacar.dataaccess.filters.LocationFilter;
import by.grodno.ss.rentacar.dataaccess.filters.ReasonFilter;
import by.grodno.ss.rentacar.datamodel.Booking;
import by.grodno.ss.rentacar.datamodel.Location;
import by.grodno.ss.rentacar.datamodel.OrderStatus;
import by.grodno.ss.rentacar.datamodel.Reason;
import by.grodno.ss.rentacar.datamodel.UserRole;
import by.grodno.ss.rentacar.service.BookingService;
import by.grodno.ss.rentacar.service.LocationService;
import by.grodno.ss.rentacar.service.ReasonService;
import by.grodno.ss.rentacar.webapp.app.AuthorizedSession;
import by.grodno.ss.rentacar.webapp.common.LocationChoiceRenderer;
import by.grodno.ss.rentacar.webapp.common.OrderStatusChoiceRenderer;
import by.grodno.ss.rentacar.webapp.common.ReasonChoiceRenderer;
import de.agilecoders.wicket.core.markup.html.bootstrap.components.TooltipBehavior;
import de.agilecoders.wicket.extensions.markup.html.bootstrap.form.DateTextField;
import de.agilecoders.wicket.extensions.markup.html.bootstrap.form.datetime.DatetimePicker;
import de.agilecoders.wicket.extensions.markup.html.bootstrap.form.datetime.DatetimePickerConfig;

public class ReservationEditPanel extends Panel {
	private static final long serialVersionUID = 1L;
	@Inject
	private BookingService bookingService;
	@Inject
	private LocationService locationService;
	@Inject
	private ReasonService reasonService;
	private Booking booking;
	private IModel<String> noteDesc = Model.of("Комментарий к заказу");
	private IModel<String> damageDesc = Model.of("Опишите повреждения если автомобиль был поврежден в процессе эксплуатации клиентом");

	public ReservationEditPanel(String id, Booking booking) {
		super(id);
		this.booking = booking;
	}

	public ReservationEditPanel(String id, IModel<?> model, Booking booking) {
		super(id, model);
		this.booking = booking;
	}

	@Override
	public void onInitialize() {
		super.onInitialize();
		ReservationEditPanel.this.setOutputMarkupId(true);
		add(new FeedbackPanel("feedback"));

		Form<Booking> form = new Form<Booking>("form", new CompoundPropertyModel<Booking>(booking));

		TextField<String> created = new TextField<String>("created");
		created.setEnabled(false);
		form.add(created);

		
		IModel<String> clientNameModel = new Model<String>(
				booking.getClient().getFirstName() + " " + booking.getClient().getLastName());
		TextField<String> clientName = new TextField<String>("client-name", clientNameModel);
		clientName.setEnabled(false);
		form.add(clientName);

		IModel<String> carNameModel = new Model<String>(booking.getCar().getName());
		TextField<String> carName = new TextField<String>("vehicle-name", carNameModel);
		carName.setEnabled(false);
		form.add(carName);
		
		AjaxLink<Void> changeCar = new AjaxLink<Void>("change-car"){
			private static final long serialVersionUID = 1L;
			@Override
			public void onClick(AjaxRequestTarget target) {
				
			}
		};
		form.add(changeCar);

		DatetimePickerConfig dateconfig = configureDateTimepicker();
		DatetimePicker dateFrom = new DatetimePicker("dateFrom", dateconfig);
		form.add(dateFrom);
		
		DatetimePicker dateTo = new DatetimePicker("dateTo", dateconfig);
		form.add(dateTo);
		
		List<Location> allLocations = locationService.find(new LocationFilter());
		DropDownChoice<Location> typeDropDownLocFrom = new DropDownChoice<>("locationFrom", allLocations,
				LocationChoiceRenderer.INSTANCE);
		typeDropDownLocFrom.setRequired(true);
		form.add(typeDropDownLocFrom);

		DropDownChoice<Location> typeDropDownLocTo = new DropDownChoice<>("locationTo", allLocations,
				LocationChoiceRenderer.INSTANCE);
		typeDropDownLocTo.setRequired(true);
		form.add(typeDropDownLocTo);

		NumberTextField<BigDecimal> summ = new NumberTextField<BigDecimal>("summ");
		summ.setRequired(true);
		summ.setMinimum(Model.of(new BigDecimal("0.01")));
		summ.setMaximum(new BigDecimal("100000.00"));
		form.add(summ);
		
		DropDownChoice<OrderStatus> statusDropDown = new DropDownChoice<>("orderStatus", Arrays.asList(OrderStatus.values()),
				OrderStatusChoiceRenderer.INSTANCE);
		statusDropDown.setRequired(true);
		form.add(statusDropDown);
		
		List<Reason> allReasons = reasonService.find(new ReasonFilter());
		DropDownChoice<Reason> reasonDropDown = new DropDownChoice<>("reason", allReasons,
				ReasonChoiceRenderer.INSTANCE);
		form.add(reasonDropDown);

		TextField<String> damage = new TextField<String>("damage");
		damage.add(new TooltipBehavior(damageDesc));
		form.add(damage);
		
		TextField<String> note = new TextField<String>("note");
		note.add(new TooltipBehavior(noteDesc));
		form.add(note);

		form.add(new SubmitLink("save") {
			private static final long serialVersionUID = 1L;

			@Override
			public void onSubmit() {

				if (booking.getOrderStatus().equals(OrderStatus.denied) && booking.getReason()==null) {
					info("Please select reason of refusing");
				} 
				else if(booking.getDateFrom().compareTo(new Date()) < 0){
					info("Pickup date is lesser than current date. Please select correct dates");
				}
				else if(booking.getDateFrom().compareTo(booking.getDateTo()) >= 0){
					info("Return date is lesser/equals pickup date. Please select correct dates");
				}
				else {
					bookingService.saveOrUpdate(booking);
					info("Booking was updated");
				}
			}
		});

		add(form);

		add(new AjaxLink<Void>("back") {
			private static final long serialVersionUID = 1L;

			public void onClick(AjaxRequestTarget target) {
				Component newPanel = new ReservationListPanel(ReservationEditPanel.this.getId());
				ReservationEditPanel.this.replaceWith(newPanel);
				if (target != null) {
					target.add(newPanel);
				}
			}
		});
	}
	
	private DatetimePickerConfig configureDateTimepicker() {
		DatetimePickerConfig dateconfig = new DatetimePickerConfig();
		dateconfig.withFormat("dd.MM.yyyy HH:mm");
		dateconfig.useSideBySide(true);
		dateconfig.useLocale(AuthorizedSession.get().getLocale().getLanguage());
		dateconfig.withMinuteStepping(10);
		return dateconfig;
	}

}
