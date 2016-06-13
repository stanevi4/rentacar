package by.grodno.ss.rentacar.webapp.page.MyBooking;

import org.apache.wicket.request.mapper.parameter.PageParameters;

import by.grodno.ss.rentacar.datamodel.UserCredentials;
import by.grodno.ss.rentacar.datamodel.UserProfile;
import by.grodno.ss.rentacar.webapp.app.AuthorizedSession;
import by.grodno.ss.rentacar.webapp.page.AbstractPage;
import by.grodno.ss.rentacar.webapp.page.admin.panel.UserEditPanel;

public class MyProfilePage extends AbstractPage {
	private static final long serialVersionUID = 1L;
	private UserProfile userProfile;
	private UserCredentials userCredentials;
	
	public MyProfilePage() {
		this.userProfile = new UserProfile();
		this.userCredentials = new UserCredentials();
	}

	public MyProfilePage(PageParameters parameters) {
		super(parameters);
	}
	
	@Override
	protected void onInitialize() {
		super.onInitialize();
		// && AuthorizedSession.get().getLoggedUser().getRole().equals(UserRole.ADMIN));
		boolean a = (AuthorizedSession.get().isSignedIn());
		if (a){
			userCredentials = AuthorizedSession.get().getLoggedUser();
			userProfile = userCredentials.getUserProfile();
		}
		
		add(new ProfileEditPanel("profile", userProfile, userCredentials));
		}

}
