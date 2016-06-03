package by.grodno.ss.rentacar.webapp.component.panel;

import java.util.Date;

import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.SubmitLink;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.PropertyModel;

import by.grodno.ss.rentacar.dataaccess.filters.CarFilter;
import by.grodno.ss.rentacar.webapp.app.AuthorizedSession;
import by.grodno.ss.rentacar.webapp.page.car.ChooseCarPage;
import de.agilecoders.wicket.extensions.markup.html.bootstrap.form.datetime.DatetimePicker;
import de.agilecoders.wicket.extensions.markup.html.bootstrap.form.datetime.DatetimePickerConfig;

public class ReservationPanel extends Panel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private CarFilter filter;

	public ReservationPanel(String id, CarFilter filter) {
		super(id);
		this.filter = filter;
	}

	public ReservationPanel(String id, IModel<?> model, CarFilter filter) {
		super(id, model);
		this.filter = filter;
	}

	@Override
	protected void onInitialize() {
		super.onInitialize();

		Form<CarFilter> form = new Form<CarFilter>("form-search", new CompoundPropertyModel<CarFilter>(filter));
		form.add(new FeedbackPanel("feedbackpanel"));

		DatetimePickerConfig dateconfig = configureDateTimepicker();

		DatetimePicker dateFrom = new DatetimePicker("dateFrom", new PropertyModel<>(filter, "dateFrom"), dateconfig);
		form.add(dateFrom);

		DatetimePicker dateTo = new DatetimePicker("dateTo", new PropertyModel<>(filter, "dateTo"), dateconfig);
		form.add(dateTo);

		form.add(new SubmitLink("submit-search") {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void onSubmit() {
				super.onSubmit();
				setResponsePage(new ChooseCarPage());
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
		dateconfig.withMinDate(new Date());

		return dateconfig;
	}

}
