package by.grodno.ss.rentacar.webapp.app;

import javax.inject.Inject;

import org.apache.wicket.Session;
import org.apache.wicket.authroles.authentication.AuthenticatedWebSession;
import org.apache.wicket.authroles.authorization.strategies.role.Roles;
import org.apache.wicket.injection.Injector;
import org.apache.wicket.request.Request;

import by.grodno.ss.rentacar.datamodel.UserCredentials;
import by.grodno.ss.rentacar.service.UserService;

public class AuthorizedSession extends AuthenticatedWebSession {
    /**
	 * 
	 */
	private static final long serialVersionUID = -933259137901929401L;

	@Inject
    private UserService userService;

    private UserCredentials loggedUser;

    private Roles roles;

    public AuthorizedSession(Request request) {
        super(request);
        Injector.get().inject(this);

    }

    public static AuthorizedSession get() {
        return (AuthorizedSession) Session.get();
    }

    @Override
    public boolean authenticate(final String userName, final String password) {
        //loggedUser = userService.getByNameAndPassword(userName, password);
        return loggedUser != null;
    }

    @Override
    public Roles getRoles() {
        if (isSignedIn() && (roles == null)) {
            roles = new Roles();
           // roles.addAll(userService.resolveRoles(loggedUser.getId()));
        }
        return roles;
    }

    @Override
    public void signOut() {
        super.signOut();
        loggedUser = null;
        roles = null;
    }

    public UserCredentials getLoggedUser() {
        return loggedUser;
    }

}
