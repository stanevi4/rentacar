package by.grodno.ss.rentacar.webapp.page.reservation;

import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.validation.validator.EmailAddressValidator;
import org.apache.wicket.validation.validator.StringValidator;

import by.grodno.ss.rentacar.dataaccess.filters.CarFilter;
import by.grodno.ss.rentacar.datamodel.UserCredentials;
import by.grodno.ss.rentacar.webapp.app.AuthorizedSession;
import de.agilecoders.wicket.extensions.markup.html.bootstrap.form.DateTextField;
import de.agilecoders.wicket.extensions.markup.html.bootstrap.form.DateTextFieldConfig;

public class ReservationPanel extends Panel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private CarFilter filter;
	
	public ReservationPanel(String id) {
		super(id);
	}
	
	public ReservationPanel(String id, IModel<?> model) {
		super(id, model);
	}

	@Override
	protected void onInitialize() {
		super.onInitialize();
		Form<CarFilter> form = new Form<CarFilter>("form",
				new CompoundPropertyModel<CarFilter>(filter));
		form.add(new FeedbackPanel("feedbackpanel"));

		DateTextFieldConfig config = new DateTextFieldConfig();
		config.withLanguage(AuthorizedSession.get().getLocale().getLanguage());
		config.withFormat("dd.MM.yyyy");
		DateTextField dateFrom = new DateTextField("date-birth", new PropertyModel<>(filter, "dateFrom"), config);
		form.add(dateFrom);
		
		TextField<String> email = new TextField<String>("email");
		email.setRequired(true);
		email.add(StringValidator.maximumLength(100));
		email.add(StringValidator.minimumLength(3));
		email.add(EmailAddressValidator.getInstance());
		form.add(email);
	}
	
}
