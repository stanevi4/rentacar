package by.grodno.ss.rentacar.webapp.page.reservation;

import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.SubmitLink;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import by.grodno.ss.rentacar.webapp.page.AbstractPage;
import by.grodno.ss.rentacar.webapp.page.car.ChooseCarPage;

public class ReservationPage extends AbstractPage {

	public ReservationPage() {
		super();
	}

	public ReservationPage(PageParameters parameters) {
		super(parameters);
	}
	
	@Override
	protected void onInitialize() {
		super.onInitialize();

		Form<Void> form = new Form<Void>("form-search");
		form.add(new SubmitLink("submit-search") {
			@Override
			public void onSubmit() {
				super.onSubmit();
				setResponsePage(new ChooseCarPage());
			}
		});
		add(form);
	}

}
