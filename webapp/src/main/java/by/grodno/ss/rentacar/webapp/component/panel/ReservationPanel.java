package by.grodno.ss.rentacar.webapp.component.panel;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormComponentUpdatingBehavior;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.SubmitLink;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import by.grodno.ss.rentacar.dataaccess.filters.CarFilter;
import by.grodno.ss.rentacar.dataaccess.filters.LocationFilter;
import by.grodno.ss.rentacar.datamodel.Location;
import by.grodno.ss.rentacar.service.LocationService;
import by.grodno.ss.rentacar.service.SettingService;
import by.grodno.ss.rentacar.webapp.app.AuthorizedSession;
import by.grodno.ss.rentacar.webapp.common.LocationChoiceRenderer;
import by.grodno.ss.rentacar.webapp.page.car.ChooseCarPage;
import de.agilecoders.wicket.core.markup.html.bootstrap.common.NotificationPanel;
import de.agilecoders.wicket.extensions.markup.html.bootstrap.form.checkbox.bootstrapcheckbox.BootstrapCheckBoxPicker;
import de.agilecoders.wicket.extensions.markup.html.bootstrap.form.datetime.DatetimePicker;
import de.agilecoders.wicket.extensions.markup.html.bootstrap.form.datetime.DatetimePickerConfig;

public class ReservationPanel extends Panel {
	private static final long serialVersionUID = 1L;
	private CarFilter filter;
	@Inject
	private LocationService locationService;
	@Inject
	private SettingService settingService;
	
	private boolean sameAs;

	public ReservationPanel(String id, CarFilter filter) {
		super(id);
		this.filter = filter;
		this.sameAs = true;
	}

	public ReservationPanel(String id, IModel<?> model, CarFilter filter) {
		super(id, model);
		this.filter = filter;
		this.sameAs = true;
	}

	@Override
	protected void onInitialize() {
		super.onInitialize();
		final NotificationPanel feedback = new NotificationPanel("feedbackpanel");
		feedback.setOutputMarkupId(true);
		
		Form<CarFilter> form = new Form<CarFilter>("form-search", new CompoundPropertyModel<CarFilter>(filter));

		form.add(feedback);
		DatetimePickerConfig dateconfig = configureDateTimepicker();
		
		DatetimePicker dateFrom = new DatetimePicker("dateFrom", dateconfig);
		dateFrom.setRequired(true);
		form.add(dateFrom);
		
		DatetimePicker dateTo = new DatetimePicker("dateTo", dateconfig);
		dateTo.setRequired(true);
		form.add(dateTo);
		
		List<Location> allLocations = locationService.find(new LocationFilter());
		DropDownChoice<Location> typeDropDownLocFrom = new DropDownChoice<>("locationFrom", allLocations,
				LocationChoiceRenderer.INSTANCE);
		typeDropDownLocFrom.setRequired(true);
		typeDropDownLocFrom.setOutputMarkupId(true);
		
		DropDownChoice<Location> typeDropDownLocTo = new DropDownChoice<>("locationTo", allLocations,
				LocationChoiceRenderer.INSTANCE);
		typeDropDownLocTo.setRequired(true);
		typeDropDownLocTo.setOutputMarkupId(true);
		typeDropDownLocTo.setEnabled(!sameAs);
		
		BootstrapCheckBoxPicker chk0 = new BootstrapCheckBoxPicker("sameAs", Model.of(sameAs));
		
		typeDropDownLocFrom.add(new AjaxFormComponentUpdatingBehavior("change") {
			private static final long serialVersionUID = 1L;
			@Override
            protected void onUpdate(AjaxRequestTarget target) {
				Location l = typeDropDownLocFrom.getModelObject();
				filter.setLocationFrom(l);
				if(sameAs){
					filter.setLocationTo(l);
					target.add(typeDropDownLocTo);
				}
            }
        });	
		
		chk0.add(new AjaxFormComponentUpdatingBehavior("change") {
			private static final long serialVersionUID = 1L;
			@Override
            protected void onUpdate(AjaxRequestTarget target) {
				Boolean value = chk0.getModelObject();
				sameAs = value;
				typeDropDownLocTo.setEnabled(!sameAs);
				if(sameAs && filter.getLocationFrom()!=null){
					filter.setLocationTo(filter.getLocationFrom());
				}
				target.add(typeDropDownLocTo);
            }
        });
		
		form.add(typeDropDownLocFrom);
		form.add(typeDropDownLocTo);
		form.add(chk0);
		
		form.add(new SubmitLink("submit-search") {
			private static final long serialVersionUID = 1L;

			@Override
			public void onSubmit() {
				super.onSubmit();
				
				//мин время заказа
				int minLength = settingService.get().getMinBookingLength();
				long minBookingLength = TimeUnit.HOURS.toMillis(minLength);
				long from = filter.getDateFrom().getTime();
				long to   = filter.getDateTo().getTime();
				long dur  = to - from;
				
				if(filter.getDateFrom().compareTo(filter.getDateTo()) >= 0){
					error("Return date is lesser/equals pickup date. Please select correct dates");
				}else if(filter.getDateFrom().compareTo(new Date()) < 0){
					error("Pick-up date is lesser than current date. Please select correct dates");
				}else if(dur < minBookingLength){
					error("Minimum booking length is "+minLength+" hours");					
				}else{
					setResponsePage(new ChooseCarPage(filter));
				}
			}
		});

		add(form);
	}

	private DatetimePickerConfig configureDateTimepicker() {
		DatetimePickerConfig dateconfig = new DatetimePickerConfig();
		dateconfig.withFormat("dd.MM.yyyy HH:mm");
		dateconfig.useSideBySide(true);
		dateconfig.useLocale(AuthorizedSession.get().getLocale().getLanguage());
		dateconfig.withMinuteStepping(10);
		//dateconfig.withMinDate(new Date());

		return dateconfig;
	}

}
