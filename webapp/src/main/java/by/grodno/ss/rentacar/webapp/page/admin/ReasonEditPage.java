package by.grodno.ss.rentacar.webapp.page.admin;

import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import by.grodno.ss.rentacar.webapp.page.admin.panel.ReasonListPanel;

public class ReasonEditPage extends AdminPage {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ReasonEditPage() {
	}

	public ReasonEditPage(PageParameters parameters) {
		super(parameters);
	}
	
	@Override
	protected void onInitialize() {
		super.onInitialize();
		
		add(new FeedbackPanel("feedback"));
		add(new ReasonListPanel("reason-panel"));
	}

}
