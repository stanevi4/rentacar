package by.grodno.ss.rentacar.webapp.common;

import org.apache.wicket.markup.html.form.ChoiceRenderer;
import by.grodno.ss.rentacar.datamodel.Reason;

public class ReasonChoiceRenderer extends ChoiceRenderer<Reason> {
	private static final long serialVersionUID = 1L;
	public static final ReasonChoiceRenderer INSTANCE = new ReasonChoiceRenderer();

	private ReasonChoiceRenderer() {
        super();
    }

	@Override
	public Object getDisplayValue(Reason object) {
		return object.getName();
	}

	@Override
	public String getIdValue(Reason object, int index) {
		return String.valueOf(object.getId());
	}
}
