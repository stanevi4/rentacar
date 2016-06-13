package by.grodno.ss.rentacar.webapp.page.MyBooking;

import javax.inject.Inject;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.SubmitLink;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.form.validation.EqualPasswordInputValidator;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.validation.validator.EmailAddressValidator;
import org.apache.wicket.validation.validator.PatternValidator;
import org.apache.wicket.validation.validator.StringValidator;

import by.grodno.ss.rentacar.datamodel.UserCredentials;
import by.grodno.ss.rentacar.datamodel.UserProfile;
import by.grodno.ss.rentacar.service.UserService;
import by.grodno.ss.rentacar.webapp.app.AuthorizedSession;
import de.agilecoders.wicket.core.markup.html.bootstrap.common.NotificationPanel;
import de.agilecoders.wicket.extensions.markup.html.bootstrap.form.DateTextField;
import de.agilecoders.wicket.extensions.markup.html.bootstrap.form.DateTextFieldConfig;

public class ProfileEditPanel extends Panel {
	private static final long serialVersionUID = 1L;
	private UserCredentials userCredentials;
	private UserProfile userProfile;
	@Inject
	private UserService userService;
	
	public ProfileEditPanel(String id, UserProfile userProfile, UserCredentials userCredentials) {
		super(id);
		this.userCredentials = userCredentials;
		this.userProfile = userProfile; 
	}

	public ProfileEditPanel(String id, IModel<?> model) {
		super(id, model);
	}
	
	@Override
	public void onInitialize() {
		super.onInitialize();
		final NotificationPanel feedback = new NotificationPanel("feedbackpanel");
		add(feedback);
		
		Form<UserProfile> form = new Form<UserProfile>("form", new CompoundPropertyModel<UserProfile>(userProfile));

		TextField<String> firstName = new TextField<String>("firstName");
		firstName.setRequired(true);
		firstName.add(StringValidator.maximumLength(100));
		firstName.add(StringValidator.minimumLength(2));
		firstName.add(new PatternValidator("[A-Za-z]+"));
		form.add(firstName);

		TextField<String> lastName = new TextField<String>("lastName");
		lastName.setRequired(true);
		lastName.add(StringValidator.maximumLength(100));
		lastName.add(StringValidator.minimumLength(2));
		lastName.add(new PatternValidator("[A-Za-z]+"));
		form.add(lastName);

		TextField<String> phone = new TextField<String>("phoneNumber");
		phone.setRequired(true);
		phone.add(StringValidator.maximumLength(100));
		phone.add(StringValidator.minimumLength(2));
		phone.add(new PatternValidator("[0-9+()-]+"));
		form.add(phone);

		TextField<String> licNumber = new TextField<String>("licenseNumber");
		licNumber.add(StringValidator.maximumLength(100));
		licNumber.add(StringValidator.minimumLength(2));
		licNumber.add(new PatternValidator("[A-Za-z0-9]+"));
		form.add(licNumber);

		DateTextFieldConfig config = new DateTextFieldConfig();
		config.withLanguage(AuthorizedSession.get().getLocale().getLanguage());
		config.withFormat("dd.MM.yyyy");
		DateTextField dateBirth = new DateTextField("birthDay", config);
		form.add(dateBirth);

		TextField<String> address = new TextField<String>("address");
		address.add(StringValidator.maximumLength(100));
		address.add(StringValidator.minimumLength(2));
		address.add(new PatternValidator("[A-Za-z0-9 /-]+"));
		form.add(address);

		TextField<String> city = new TextField<String>("city");
		city.add(StringValidator.maximumLength(100));
		city.add(StringValidator.minimumLength(2));
		city.add(new PatternValidator("[A-Za-z0-9]+"));
		form.add(city);

		TextField<String> region = new TextField<String>("region");
		region.add(StringValidator.maximumLength(100));
		region.add(StringValidator.minimumLength(2));
		region.add(new PatternValidator("[A-Za-z0-9]+"));
		form.add(region);

		TextField<String> zip = new TextField<String>("zipCode");
		zip.add(StringValidator.maximumLength(20));
		zip.add(StringValidator.minimumLength(2));
		zip.add(new PatternValidator("[0-9]+"));
		form.add(zip);
		
		Label email = new Label("email", new PropertyModel<>(userCredentials, "email"));
		form.add(email);

		WebMarkupContainer passTable = new WebMarkupContainer("pass-table");
		passTable.setOutputMarkupId(true);
		
		WebMarkupContainer trPass = new WebMarkupContainer("pass");
		WebMarkupContainer trCpass = new WebMarkupContainer("cpass");
		if(userProfile.getId()==null){
			trPass.setVisible(true);
			trCpass.setVisible(true);
		}else{
			trPass.setVisible(false);
			trCpass.setVisible(false);
		}
		trPass.setOutputMarkupId(true);
		trCpass.setOutputMarkupId(true);

		PasswordTextField password = new PasswordTextField("password",
				new PropertyModel<>(userCredentials, "password"));
		trPass.add(password);
		PasswordTextField cpassword = new PasswordTextField("cpassword", Model.of(""));
		trCpass.add(cpassword);
		
		passTable.add(trPass);
		passTable.add(trCpass);
		form.add(passTable);
		form.add(new EqualPasswordInputValidator(password, cpassword));

		AjaxLink<Void> changePass = new AjaxLink<Void>("change-password") {
			private static final long serialVersionUID = 1L;
			@Override
			public void onClick(AjaxRequestTarget target) {
				if (!trPass.isVisible()) {
					trPass.setVisible(true);
					trCpass.setVisible(true);
					passTable.add(trPass);
					passTable.add(trCpass);
				}else{
					trPass.setVisible(false);
					trCpass.setVisible(false);
				}
				if (target != null) {
					target.add(passTable);
				}
			}
		};
		if(userProfile.getId()==null){
			changePass.setVisible(false);
		}
		form.add(changePass);

		form.add(new SubmitLink("save") {
			private static final long serialVersionUID = 1L;

			@Override
			public void onSubmit() {
				if (userProfile.getId() == null) {
					error("saving error");
				} else {
					userService.update(userProfile);
					userService.update(userCredentials);
				}
				info("User was saved");
			}
		});
		add(form);

		add(new Link<Void>("back") {
			private static final long serialVersionUID = 1L;
			@Override
			public void onClick() {
				setResponsePage(new MyBookingPage());			
			}
		});
	}
}
