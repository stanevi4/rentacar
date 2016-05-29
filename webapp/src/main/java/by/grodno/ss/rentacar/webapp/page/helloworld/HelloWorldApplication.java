package by.grodno.ss.rentacar.webapp.page.helloworld;

import javax.inject.Inject;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.spring.injection.annot.SpringComponentInjector;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component("wicketWebApplicationBean")
public class HelloWorldApplication extends WebApplication {
	
	@Inject
    private ApplicationContext applicationContext;
    
	public HelloWorldApplication() {
    }
    /**
     * @see org.apache.wicket.Application#getHomePage()
     */
    @Override
    public void init() {
        super.init();
        getMarkupSettings().setStripWicketTags(true);
        // add your configuration here

        getComponentInstantiationListeners().add(new SpringComponentInjector(this, getApplicationContext()));

        //getSecuritySettings().setAuthorizationStrategy(new AnnotationsRoleAuthorizationStrategy(this));
        // mount
        //mountPage("/ind", HelloWorld.class);
    }
    
    public ApplicationContext getApplicationContext() {
        return applicationContext;
    }
    @Override
    public Class<? extends WebPage> getHomePage() {
        return HelloWorld.class;
    }
}
