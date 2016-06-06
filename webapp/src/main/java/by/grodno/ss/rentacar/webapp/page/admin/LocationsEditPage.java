package by.grodno.ss.rentacar.webapp.page.admin;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.util.string.StringValue;

import by.grodno.ss.rentacar.webapp.page.admin.panel.LocationListPanel;

public class LocationsEditPage extends AdminPage {
	private static final long serialVersionUID = 1L;
	private StringValue msg;	
	
	public LocationsEditPage() {
		super();
	}

	public LocationsEditPage(PageParameters parameters) {
		super(parameters);
	}
	
	@Override
	protected void onInitialize() {
		super.onInitialize();
		
		Model<String> userMessageModel = new Model<String>();
        Label label = new Label("user-message", userMessageModel);
        label.setVisible(false);
        add(label);
		if (msg != null ) {
	        userMessageModel.setObject(msg.toString(""));
	        label.setVisible(true);
		}
		
		add(new FeedbackPanel("feedback"));
		add(new LocationListPanel("location-panel"));
	}


}
