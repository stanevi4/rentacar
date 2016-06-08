package by.grodno.ss.rentacar.webapp.common;

import org.apache.wicket.markup.html.form.ChoiceRenderer;

import by.grodno.ss.rentacar.datamodel.Type;

public class TypeChoiceRenderer extends ChoiceRenderer<Type> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static final TypeChoiceRenderer INSTANCE = new TypeChoiceRenderer();

    private TypeChoiceRenderer() {
        super();
    }

    @Override
    public Object getDisplayValue(Type object) {
        return object.getName();
    }

    @Override
    public String getIdValue(Type object, int index) {
        return String.valueOf(object.getId());
    }

}
