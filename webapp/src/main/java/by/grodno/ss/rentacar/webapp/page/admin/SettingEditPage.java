package by.grodno.ss.rentacar.webapp.page.admin;

import org.apache.wicket.request.mapper.parameter.PageParameters;

import by.grodno.ss.rentacar.webapp.page.admin.panel.SettingPanel;

public class SettingEditPage extends AdminPage {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SettingEditPage() {

	}

	public SettingEditPage(PageParameters parameters) {
		super(parameters);

	}

	@Override
	protected void onInitialize() {
		super.onInitialize();
		
		add(new SettingPanel("setting-panel"));
	}

}
