package by.grodno.ss.rentacar.webapp.page.car;

import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.SubmitLink;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import by.grodno.ss.rentacar.webapp.page.AbstractPage;
import by.grodno.ss.rentacar.webapp.page.order.CheckoutPage;

public class ChooseCarPage extends AbstractPage {

	public ChooseCarPage() {
		super();
	}

	public ChooseCarPage(PageParameters parameters) {
		super(parameters);
	}

	@Override
	protected void onInitialize() {
		super.onInitialize();

		Form<Void> form = new Form<Void>("form-submit-continue");
		form.add(new SubmitLink("submit-continue") {
			@Override
			public void onSubmit() {
				super.onSubmit();
				setResponsePage(new CheckoutPage());
			}
		});
		add(form);
	}
}
