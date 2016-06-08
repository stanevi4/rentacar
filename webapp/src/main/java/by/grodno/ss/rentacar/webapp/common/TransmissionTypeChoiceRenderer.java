package by.grodno.ss.rentacar.webapp.common;

import org.apache.wicket.markup.html.form.ChoiceRenderer;

import by.grodno.ss.rentacar.datamodel.TransmissionType;

public class TransmissionTypeChoiceRenderer extends ChoiceRenderer<TransmissionType> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final TransmissionTypeChoiceRenderer INSTANCE = new TransmissionTypeChoiceRenderer();

	public TransmissionTypeChoiceRenderer() {
		super();
	}

	@Override
	public Object getDisplayValue(TransmissionType object) {
		return object.name();
	}

	@Override
	public String getIdValue(TransmissionType object, int index) {
		return String.valueOf(object.ordinal());
	}

}
