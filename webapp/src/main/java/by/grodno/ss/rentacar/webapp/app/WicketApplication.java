package by.grodno.ss.rentacar.webapp.app;

import java.util.List;

import javax.inject.Inject;

import org.apache.wicket.authroles.authentication.AbstractAuthenticatedWebSession;
import org.apache.wicket.authroles.authentication.AuthenticatedWebApplication;
import org.apache.wicket.authroles.authorization.strategies.role.annotations.AnnotationsRoleAuthorizationStrategy;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.spring.injection.annot.SpringComponentInjector;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import by.grodno.ss.rentacar.webapp.page.home.HomePage;
import by.grodno.ss.rentacar.webapp.page.login.LoginPage;
import de.agilecoders.wicket.core.Bootstrap;
import de.agilecoders.wicket.core.settings.BootstrapSettings;
import de.agilecoders.wicket.core.settings.ITheme;

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
        
//        BootstrapSettings settings = new BootstrapSettings();
//        settings.getActiveThemeProvider().setActiveTheme("bootstrap");
//        Bootstrap.install(this, settings);
        Bootstrap.install(this);
//        BootstrapSettings settings = new BootstrapSettings();
//        List<ITheme> themes = settings.getThemeProvider().available();
//        for (ITheme theme: themes) {
//            System.out.println("available theme: " + theme.name());
//        }
        
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
        return LoginPage.class;
    }

    /**
     * @see org.apache.wicket.Application#getHomePage()
     */
    @Override
    public Class<? extends WebPage> getHomePage() {
        return HomePage.class;
    }

}