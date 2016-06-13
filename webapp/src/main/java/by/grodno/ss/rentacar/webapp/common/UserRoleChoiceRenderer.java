package by.grodno.ss.rentacar.webapp.common;

import org.apache.wicket.markup.html.form.ChoiceRenderer;

import by.grodno.ss.rentacar.datamodel.UserRole;

public class UserRoleChoiceRenderer extends ChoiceRenderer<UserRole> {
	private static final long serialVersionUID = 1L;
	public static final UserRoleChoiceRenderer INSTANCE = new UserRoleChoiceRenderer();

	public UserRoleChoiceRenderer() {
		super();
	}

	@Override
	public Object getDisplayValue(UserRole object) {
		return object.name();
	}

	@Override
	public String getIdValue(UserRole object, int index) {
		return String.valueOf(object.ordinal());
	}
	

}
