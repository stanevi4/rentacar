package by.grodno.ss.rentacar.webapp.page.admin;

import org.apache.wicket.request.mapper.parameter.PageParameters;

import by.grodno.ss.rentacar.webapp.component.menu.AdminMenu;
import by.grodno.ss.rentacar.webapp.page.AbstractPage;

public class AdminPage extends AbstractPage {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//public Panel replacedPanel;

	public AdminPage() {
		super();
	}

	public AdminPage(PageParameters parameters) {
		super(parameters);
	}

	@Override
	protected void onInitialize() {
		super.onInitialize();

		add(new AdminMenu("admin-menu"));
		
//		add(new AjaxLink<Void>("settings-link") {
//			private static final long serialVersionUID = 1L;
//			@Override
//			public void onClick(AjaxRequestTarget target) {
//				Panel settingsPanel = new SettingsEditPanel(replacedPanel.getId());
//				settingsPanel.setOutputMarkupId(true);
//				replacedPanel.replaceWith(settingsPanel);
//				target.add(settingsPanel);
//				replacedPanel = settingsPanel;
//			}
//		});
//	
//		add(new AjaxLink<Void>("reservations-link") {
//			private static final long serialVersionUID = 1L;
//			@Override
//			public void onClick(AjaxRequestTarget target) {
//				Panel reservationsPanel = new ReservationsEditPanel(replacedPanel.getId());
//				reservationsPanel.setOutputMarkupId(true);
//				replacedPanel.replaceWith(reservationsPanel);
//				target.add(reservationsPanel);
//				replacedPanel = reservationsPanel;
//			}
//		});
//		
//		add(new AjaxLink<Void>("models-link") {
//			private static final long serialVersionUID = 1L;
//			@Override
//			public void onClick(AjaxRequestTarget target) {
//				Panel modelsPanel = new CarsEditPanel(replacedPanel.getId());
//				modelsPanel.setOutputMarkupId(true);
//				replacedPanel.replaceWith(modelsPanel);
//				target.add(modelsPanel);
//				replacedPanel = modelsPanel;
//			}
//		});
//		
//		add(new AjaxLink<Void>("cars-link") {
//			private static final long serialVersionUID = 1L;
//			@Override
//			public void onClick(AjaxRequestTarget target) {
//				Panel carsPanel = new CarsEditPanel(replacedPanel.getId());
//				carsPanel.setOutputMarkupId(true);
//				replacedPanel.replaceWith(carsPanel);
//				target.add(carsPanel);
//				replacedPanel = carsPanel;
//			}
//		});
//		
//		add(new AjaxLink<Void>("locations-link") {
//			private static final long serialVersionUID = 1L;
//			@Override
//			public void onClick(AjaxRequestTarget target) {
//				Panel locPanel = new LocationsEditPanel(replacedPanel.getId());
//				locPanel.setOutputMarkupId(true);
//				replacedPanel.replaceWith(locPanel);
//				target.add(locPanel);
//				replacedPanel = locPanel;
//			}
//		});
//		
//		add(new AjaxLink<Void>("users-link") {
//			private static final long serialVersionUID = 1L;
//			@Override
//			public void onClick(AjaxRequestTarget target) {
//				Panel usersPanel = new UsersEditPanel(replacedPanel.getId());
//				usersPanel.setOutputMarkupId(true);
//				replacedPanel.replaceWith(usersPanel);
//				target.add(usersPanel);
//				replacedPanel = usersPanel;
//			}
//		});
	}
}
