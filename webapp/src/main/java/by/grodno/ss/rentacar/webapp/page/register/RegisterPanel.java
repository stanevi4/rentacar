package by.grodno.ss.rentacar.webapp.page.register;

import javax.inject.Inject;

import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.SubmitLink;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.validation.validator.EmailAddressValidator;
import org.apache.wicket.validation.validator.PatternValidator;
import org.apache.wicket.validation.validator.StringValidator;

import by.grodno.ss.rentacar.datamodel.UserCredentials;
import by.grodno.ss.rentacar.datamodel.UserProfile;
import by.grodno.ss.rentacar.datamodel.UserRole;
import by.grodno.ss.rentacar.service.UserService;
import by.grodno.ss.rentacar.webapp.app.AuthorizedSession;
import by.grodno.ss.rentacar.webapp.page.login.LoginPage;
import de.agilecoders.wicket.extensions.markup.html.bootstrap.form.DateTextField;
import de.agilecoders.wicket.extensions.markup.html.bootstrap.form.DateTextFieldConfig;
import de.agilecoders.wicket.extensions.markup.html.bootstrap.form.checkbox.bootstrapcheckbox.BootstrapCheckBoxPicker;

public class RegisterPanel extends Panel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	private UserService userService;
	private UserProfile userProfile;
	private UserCredentials userCredentials;

	public RegisterPanel(String id, UserCredentials userCredentials) {
		super(id);
		this.userCredentials = userCredentials;
		this.userProfile = userCredentials.getUserProfile();
	}

	public RegisterPanel(String id) {
		super(id);
		userProfile = new UserProfile();
		userCredentials = new UserCredentials();
	}

	@Override
	protected void onInitialize() {
		super.onInitialize();
		Form<UserCredentials> form = new Form<UserCredentials>("form-register",
				new CompoundPropertyModel<UserCredentials>(userCredentials));
		form.add(new FeedbackPanel("feedbackpanel"));

		TextField<String> email = new TextField<String>("email");
		email.setRequired(true);
		email.add(StringValidator.maximumLength(100));
		email.add(StringValidator.minimumLength(3));
		email.add(EmailAddressValidator.getInstance());
		form.add(email);

		PasswordTextField password = new PasswordTextField("password");
		password.setRequired(true);
		password.add(StringValidator.maximumLength(100));
		password.add(StringValidator.minimumLength(6));
		password.add(new PatternValidator("[A-Za-z0-9_-]+"));
		form.add(password);

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

		BootstrapCheckBoxPicker chk0 = new BootstrapCheckBoxPicker("check-confirm", Model.of(Boolean.FALSE));
		form.add(chk0);

		form.add(new SubmitLink("button-register") {
			private static final long serialVersionUID = 1L;

			@Override
			public void onSubmit() {
				if (chk0.getModelObject().equals(false)) {
					info("You must to agree rental terms");
				} else {
					userCredentials.setRole(UserRole.CLIENT);
					if (userProfile.getId() == null) {
						userService.register(userProfile, userCredentials);
					} else {
						userService.update(userProfile);
						userService.update(userCredentials);
					}
					setResponsePage(new LoginPage());
				}
			}
		});
		add(form);

	}
}
