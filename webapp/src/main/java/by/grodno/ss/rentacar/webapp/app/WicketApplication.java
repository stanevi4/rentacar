package by.grodno.ss.rentacar.webapp.app;

import javax.inject.Inject;

import org.apache.wicket.authroles.authentication.AbstractAuthenticatedWebSession;
import org.apache.wicket.authroles.authentication.AuthenticatedWebApplication;
import org.apache.wicket.authroles.authorization.strategies.role.annotations.AnnotationsRoleAuthorizationStrategy;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.spring.injection.annot.SpringComponentInjector;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import by.grodno.ss.rentacar.webapp.page.home.HomePage;

@Component("wicketWebApplicationBean")
public class WicketApplication extends AuthenticatedWebApplication {
    
	@Inject
    private ApplicationContext applicationContext;

    public WicketApplication() {
		super();
	}

	/**
     * @see org.apache.wicket.Application#init()
     */
    @Override
    public void init() {
        super.init();
        getMarkupSettings().setStripWicketTags(true);
        // add your configuration here

        getComponentInstantiationListeners().add(new SpringComponentInjector(this, getApplicationContext()));

        getSecuritySettings().setAuthorizationStrategy(new AnnotationsRoleAuthorizationStrategy(this));
        // mount
        mountPage("/home", HomePage.class);
    }

    public ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    @Override
    protected Class<? extends AbstractAuthenticatedWebSession> getWebSessionClass() {
        return AuthorizedSession.class;
    }

    @Override
    protected Class<? extends WebPage> getSignInPageClass() {
        return HomePage.class;
    }

    /**
     * @see org.apache.wicket.Application#getHomePage()
     */
    @Override
    public Class<? extends WebPage> getHomePage() {
        return HomePage.class;
    }

}