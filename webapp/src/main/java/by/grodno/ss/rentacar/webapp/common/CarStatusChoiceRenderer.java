package by.grodno.ss.rentacar.webapp.common;

import org.apache.wicket.markup.html.form.ChoiceRenderer;

import by.grodno.ss.rentacar.datamodel.CarStatus;


public class CarStatusChoiceRenderer extends ChoiceRenderer<CarStatus> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final CarStatusChoiceRenderer INSTANCE = new CarStatusChoiceRenderer();

	public CarStatusChoiceRenderer() {
		super();
	}

	@Override
	public Object getDisplayValue(CarStatus object) {
		return object.name();
	}

	@Override
	public String getIdValue(CarStatus object, int index) {
		return String.valueOf(object.ordinal());
	}
}
