package by.grodno.ss.rentacar.webapp.page.admin.panel;

import javax.inject.Inject;

import org.apache.wicket.markup.html.panel.Panel;

import by.grodno.ss.rentacar.service.LocationService;

public class LocationListPanel extends Panel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Inject
	private LocationService locationService;
	
	public LocationListPanel(String id) {
		super(id);
		
	}
}
