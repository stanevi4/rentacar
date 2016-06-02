package by.grodno.ss.rentacar.webapp.page;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import by.grodno.ss.rentacar.webapp.component.footer.FooterPanel;
import by.grodno.ss.rentacar.webapp.component.menu.TopMenuPanel;

public abstract class AbstractPage extends WebPage{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AbstractPage() {
    	super();
    }

    public AbstractPage(PageParameters parameters) {
        super(parameters);
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();

        add(new TopMenuPanel("top-menu-panel", this.getClass()));
        
        add(new FooterPanel("footer-panel", this.getClass()));

        };

//	@Override
//	public void renderHead(IHeaderResponse response) {
//		super.renderHead(response);
		
		//CSS
//		response.render(CssHeaderItem.forReference(new UrlResourceReference(Url.parse("css/style.css"))));
//		response.render(CssHeaderItem.forReference(new UrlResourceReference(Url.parse("css/bootstrap.min.css"))));
//		response.render(CssHeaderItem.forReference(new UrlResourceReference(Url.parse("css/theme2.css"))));
//		response.render(CssHeaderItem.forReference(new UrlResourceReference(Url.parse("css/template.css"))));
//		response.render(CssHeaderItem.forReference(new UrlResourceReference(Url.parse("css/bootstrap-datetimepicker.min.css"))));
//		response.render(JavaScriptHeaderItem.forReference(new UrlResourceReference(Url.parse("css/owl.carousel.css"))));
		
		//JS
		//response.render(JavaScriptHeaderItem.forReference(Application.get().getJavaScriptLibrarySettings().getJQueryReference()));
		//response.render(JavaScriptHeaderItem.forReference(new UrlResourceReference(Url.parse("js/jquery.min.js"))));
		//response.render(JavaScriptHeaderItem.forReference(new UrlResourceReference(Url.parse("js/bootstrap.min.js"))));
		//response.render(JavaScriptHeaderItem.forReference(new UrlResourceReference(Url.parse("js/moment-with-locales.min.js"))));
		//response.render(JavaScriptHeaderItem.forReference(new UrlResourceReference(Url.parse("js/bootstrap-datetimepicker.min.js"))));
//	}
}
