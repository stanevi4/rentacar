package by.grodno.ss.rentacar.webapp.page.login;

import org.apache.wicket.Application;
import org.apache.wicket.authroles.authentication.AuthenticatedWebSession;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.markup.html.form.SubmitLink;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.util.string.Strings;

import by.grodno.ss.rentacar.webapp.page.AbstractPage;
import by.grodno.ss.rentacar.webapp.page.register.RegisterPage;

public class LoginPage extends AbstractPage {
	/**
	 * 
	 */
	private static final long serialVersionUID = 9114854893665783937L;

	public static final String ID_FORM = "form-login";

	private String username;
	private String password;

	public LoginPage() {
	}

	@Override
	protected void onInitialize() {
		super.onInitialize();

		// if already logged then should not see login page at all
		if (AuthenticatedWebSession.get().isSignedIn()) {
			setResponsePage(Application.get().getHomePage());
		}

		final Form<Void> form = new Form<Void>(ID_FORM);
		form.setDefaultModel(new CompoundPropertyModel<LoginPage>(this));
		form.add(new RequiredTextField<String>("username"));
		form.add(new PasswordTextField("password"));

		form.add(new SubmitLink("login-button") {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void onSubmit() {
				super.onSubmit();
				if (Strings.isEmpty(username) || Strings.isEmpty(password)) {
					return;
				}
				final boolean authResult = AuthenticatedWebSession.get().signIn(username, password);
				if (authResult) {
					setResponsePage(Application.get().getHomePage());
				} else {
					error("authorization error");
				}
			}
		});

		form.add(new Link("link-register") {
			@Override
			public void onClick() {
				setResponsePage(new RegisterPage());
			}

		});

		form.add(new FeedbackPanel("feedbackpanel"));
		add(form);
	}
}
