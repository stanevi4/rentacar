package by.grodno.ss.rentacar.webapp.common;

import org.apache.wicket.markup.html.form.ChoiceRenderer;

import by.grodno.ss.rentacar.datamodel.Currency;

public class CurrencyChoiceRenderer extends ChoiceRenderer<Currency>{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final CurrencyChoiceRenderer INSTANCE = new CurrencyChoiceRenderer();

	public CurrencyChoiceRenderer() {
		super();
	}
	
	@Override
    public Object getDisplayValue(Currency object) {
        return object.name();
    }

    @Override
    public String getIdValue(Currency object, int index) {
        return String.valueOf(object.ordinal());
    }

}
