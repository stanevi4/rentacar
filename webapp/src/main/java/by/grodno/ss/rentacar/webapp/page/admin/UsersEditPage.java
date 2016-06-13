package by.grodno.ss.rentacar.webapp.page.admin;

import org.apache.wicket.request.mapper.parameter.PageParameters;

import by.grodno.ss.rentacar.dataaccess.filters.UserFilter;
import by.grodno.ss.rentacar.webapp.page.admin.panel.UserListPanel;
import de.agilecoders.wicket.core.markup.html.bootstrap.common.NotificationPanel;

public class UsersEditPage extends AdminPage {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UsersEditPage() {
		super();
	}

	public UsersEditPage(PageParameters parameters) {
		super(parameters);
	}
	
	@Override
	protected void onInitialize() {
		super.onInitialize();

		final NotificationPanel feedback = new NotificationPanel("feedbackpanel");
		add(feedback);
		add(new UserListPanel("user-panel", new UserFilter()));
	}

}
