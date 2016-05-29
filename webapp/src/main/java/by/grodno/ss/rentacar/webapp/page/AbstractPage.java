package by.grodno.ss.rentacar.webapp.page;

import org.apache.wicket.markup.html.WebPage;

import by.grodno.ss.rentacar.webapp.component.menu.TopMenuPanel;

public abstract class AbstractPage extends WebPage{

    public AbstractPage() {
    	super();
    }

    //public AbstractPage(PageParameters parameters) {
    //    super(parameters);
    //}

    @Override
    protected void onInitialize() {
        super.onInitialize();

        add(new TopMenuPanel("top-menu-panel"));

        //AbstractReadOnlyModel<Integer> yearModel = new AbstractReadOnlyModel<Integer>() {
        //    @Override
        //    public Integer getObject() {
        //        return Calendar.getInstance().get(Calendar.YEAR);
        //    }
        };

       // WebMarkupContainer footer = new WebMarkupContainer("footer");
       // add(footer);
       // footer.add(new Label("current-year", yearModel));
       // footer.add(AttributeModifier.append("onclick", "alert('Im clicked')"));
    //}
}
