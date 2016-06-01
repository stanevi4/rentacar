package by.grodno.ss.rentacar.webapp.page.register;

import by.grodno.ss.rentacar.webapp.page.AbstractPage;

public class RegisterPage extends AbstractPage {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public RegisterPage() {
	}

	@Override
	protected void onInitialize() {

		super.onInitialize();
		
		add(new RegisterPanel("register-panel"));
	}

}
