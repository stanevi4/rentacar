package by.grodno.ss.rentacar.webapp.component.menu;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;

import by.grodno.ss.rentacar.webapp.page.admin.CarsEditPage;
import by.grodno.ss.rentacar.webapp.page.admin.LocationsEditPage;
import by.grodno.ss.rentacar.webapp.page.admin.ReasonEditPage;
import by.grodno.ss.rentacar.webapp.page.admin.ReservationsEditPage;
import by.grodno.ss.rentacar.webapp.page.admin.SettingEditPage;
import by.grodno.ss.rentacar.webapp.page.admin.TypeEditPage;
import by.grodno.ss.rentacar.webapp.page.admin.UsersEditPage;

public class AdminMenu extends Panel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AdminMenu(String id, IModel<?> model) {
		super(id, model);
	}

	public AdminMenu(String id) {
		super(id);
	}

	@Override
	protected void onInitialize() {
		super.onInitialize();
		
		WebMarkupContainer liSettings = new WebMarkupContainer("link-container-settings");
		if (getPage().getClass().equals(SettingEditPage.class)) {
			liSettings.add(new AttributeModifier("class", "active"));
		}
		liSettings.add(new Link<Void>("settings-link") {
			private static final long serialVersionUID = 1L;
			@Override
			public void onClick() {
				setResponsePage(new SettingEditPage());
			}
		});
		add(liSettings);
		
		WebMarkupContainer liReservations = new WebMarkupContainer("link-container-reservations");
		if (getPage().getClass().equals(ReservationsEditPage.class)) {
			liReservations.add(new AttributeModifier("class", "active"));
		}
		liReservations.add(new Link<Void>("reservations-link") {
			private static final long serialVersionUID = 1L;
			@Override
			public void onClick() {
				setResponsePage(new ReservationsEditPage());
			}
		});
		add(liReservations);
		
		WebMarkupContainer liCars = new WebMarkupContainer("link-container-cars");
		if (getPage().getClass().equals(CarsEditPage.class)) {
			liCars.add(new AttributeModifier("class", "active"));
		}
		liCars.add(new Link<Void>("cars-link") {
			private static final long serialVersionUID = 1L;
			@Override
			public void onClick() {
				setResponsePage(new CarsEditPage());
			}
		});
		add(liCars);
		
		WebMarkupContainer liLocations = new WebMarkupContainer("link-container-locations");
		if (getPage().getClass().equals(LocationsEditPage.class)) {
			liLocations.add(new AttributeModifier("class", "active"));
		}
		liLocations.add(new Link<Void>("locations-link") {
			private static final long serialVersionUID = 1L;
			@Override
			public void onClick() {
				setResponsePage(new LocationsEditPage());
			}
		});
		add(liLocations);
		
		WebMarkupContainer liUsers = new WebMarkupContainer("link-container-users");
		if (getPage().getClass().equals(UsersEditPage.class)) {
			liUsers.add(new AttributeModifier("class", "active"));
		}
		liUsers.add(new Link<Void>("users-link") {
			private static final long serialVersionUID = 1L;
			@Override
			public void onClick() {
				setResponsePage(new UsersEditPage());
			}
		});
		add(liUsers);
		
		WebMarkupContainer liReasons = new WebMarkupContainer("link-container-reasons");
		if (getPage().getClass().equals(ReasonEditPage.class)) {
			liReasons.add(new AttributeModifier("class", "active"));
		}
		liReasons.add(new Link<Void>("reasons-link") {
			private static final long serialVersionUID = 1L;
			@Override
			public void onClick() {
				setResponsePage(new ReasonEditPage());
			}
		});
		add(liReasons);
		
		WebMarkupContainer liTypes = new WebMarkupContainer("link-container-types");
		if (getPage().getClass().equals(TypeEditPage.class)) {
			liTypes.add(new AttributeModifier("class", "active"));
		}
		liTypes.add(new Link<Void>("types-link") {
			private static final long serialVersionUID = 1L;
			@Override
			public void onClick() {
				setResponsePage(new TypeEditPage());
			}
		});
		add(liTypes);

//		WebMarkupContainer liHomePage = new WebMarkupContainer("link-container-homepage");
//		if (selectedPage.equals(HomePage.class)) {
//			liHomePage.add(new AttributeModifier("class", "active"));
//		}
//		liHomePage.add(new Link("link-homepage") {
//			@Override
//			public void onClick() {
//				setResponsePage(new HomePage());
//			}
//		});
//		add(liHomePage);
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

	};

}
