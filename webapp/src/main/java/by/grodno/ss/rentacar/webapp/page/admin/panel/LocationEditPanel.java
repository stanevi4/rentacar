package by.grodno.ss.rentacar.webapp.page.admin.panel;

import javax.inject.Inject;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.NumberTextField;
import org.apache.wicket.markup.html.form.SubmitLink;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.validation.validator.PatternValidator;
import org.apache.wicket.validation.validator.StringValidator;

import by.grodno.ss.rentacar.datamodel.Location;
import by.grodno.ss.rentacar.service.LocationService;
import de.agilecoders.wicket.core.markup.html.bootstrap.components.TooltipBehavior;

public class LocationEditPanel extends Panel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Inject
	private LocationService locationService;
	private Location location;
	private IModel<String> descName = Model.of("Enter location name");
	private IModel<String> descLat = Model.of("Enter location latitude coordinate");
	private IModel<String> descLng = Model.of("Enter location Longitude coordinate");

	public LocationEditPanel(String id, Location location) {
		super(id);
		this.location = location;

	}

	public LocationEditPanel(String id, IModel<?> model, Location location) {
		super(id, model);
		this.location = location;
	}

	@Override
	public void onInitialize() {
		super.onInitialize();
		LocationEditPanel.this.setOutputMarkupId(true);

		Form<Location> form = new Form<Location>("form", new CompoundPropertyModel<Location>(location));

		TextField<String> name = new TextField<String>("name");
		name.setRequired(true);
		name.add(StringValidator.maximumLength(100));
		name.add(StringValidator.minimumLength(2));
		name.add(new PatternValidator("[A-Za-z0-9 /-]+"));
		name.add(new TooltipBehavior(descName));
		form.add(name);

		NumberTextField<Double> lat = new NumberTextField<Double>("lat");
		lat.setRequired(true);
		lat.setMinimum(Model.of(new Double("0.00")));
		lat.setMaximum(new Double("1000000.00"));
		lat.add(new TooltipBehavior(descLat));
		form.add(lat);

		NumberTextField<Double> lng = new NumberTextField<Double>("lng");
		lng.setRequired(true);
		lng.setMinimum(Model.of(new Double("0.00")));
		lng.setMaximum(new Double("1000000.00"));
		lng.add(new TooltipBehavior(descLng));
		form.add(lng);


		
		form.add(new SubmitLink("save") {
			private static final long serialVersionUID = 1L;

			@Override
			public void onSubmit() {
				locationService.saveOrUpdate(location);
				info("Location was saved");
			}
		});
		
		add(form);

		add(new AjaxLink<Void>("back") {
			private static final long serialVersionUID = 1L;
			public void onClick(AjaxRequestTarget target) {
				Component newPanel = new LocationListPanel(LocationEditPanel.this.getId());
				LocationEditPanel.this.replaceWith(newPanel);
				if (target != null) {
					target.add(newPanel);
				}
			}
		});
	}

}
