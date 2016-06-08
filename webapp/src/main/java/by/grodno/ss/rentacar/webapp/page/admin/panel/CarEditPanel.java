package by.grodno.ss.rentacar.webapp.page.admin.panel;

import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;

import by.grodno.ss.rentacar.datamodel.Car;

public class CarEditPanel extends Panel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Car car;

	public CarEditPanel(String id, Car car) {
		super(id);
		this.car = car;
	}

	public CarEditPanel(String id, IModel<?> model, Car car) {
		super(id, model);
		this.car = car;
	}

}
