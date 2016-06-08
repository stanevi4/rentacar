package by.grodno.ss.rentacar.webapp.common;

import org.apache.wicket.markup.html.form.ChoiceRenderer;

import by.grodno.ss.rentacar.datamodel.Location;

public class LocationChoiceRenderer extends ChoiceRenderer<Location> {

	private static final long serialVersionUID = 1L;
	
	public static final LocationChoiceRenderer INSTANCE = new LocationChoiceRenderer();

    private LocationChoiceRenderer() {
        super();
    }

    @Override
    public Object getDisplayValue(Location object) {
        return object.getName();
    }

    @Override
    public String getIdValue(Location object, int index) {
        return String.valueOf(object.getId());
    }

}
