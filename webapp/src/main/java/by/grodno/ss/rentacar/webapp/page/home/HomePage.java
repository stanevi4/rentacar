package by.grodno.ss.rentacar.webapp.page.home;

import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.SubmitLink;

import by.grodno.ss.rentacar.webapp.page.AbstractPage;
import by.grodno.ss.rentacar.webapp.page.car.ChooseCarPage;

public class HomePage extends AbstractPage {

	public HomePage() {
		super();
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
