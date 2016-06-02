package by.grodno.ss.rentacar.webapp.page.register;

import java.util.Date;
import org.apache.wicket.datetime.PatternDateConverter;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.PropertyModel;

import de.agilecoders.wicket.extensions.markup.html.bootstrap.form.DateTextField;
import de.agilecoders.wicket.extensions.markup.html.bootstrap.form.datetime.DatetimePickerConfig;

public class RegisterPanelTest extends Panel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Date date;
	private Form<Void> form;
	private Button submit;
	private DateTextField dateField;
	private PropertyModel<Date> dateModel;
	
	public RegisterPanelTest(String id) {
		super(id);
		createModels();
        createComponents();
        addComponents();
	}

	private void addComponents() {
		form.add(dateField);
		form.add(submit);
		add(form);
	}

	private void createModels() {
		dateModel = new PropertyModel<>(this, "date");
	}

	private void createComponents() {
		form = new Form<Void>("form") {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			@Override
			protected void onSubmit() {
				printOutDate();
			}
		};

		//("dd/MM/yyyy");
		dateField = new DateTextField("dateField", "dd.MM.yyyy");
		//dateField.add(new DateTimePicker());

		submit = new Button("submit");
		form.setDefaultButton(submit);
	}

	private void printOutDate() {
		System.out.println("Selected date: " + date);
	}
	
	// GETTER & SETTER
	
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
}
