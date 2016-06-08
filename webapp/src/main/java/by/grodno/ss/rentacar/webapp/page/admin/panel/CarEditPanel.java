package by.grodno.ss.rentacar.webapp.page.admin.panel;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.markup.html.form.DropDownChoice;
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

import by.grodno.ss.rentacar.dataaccess.filters.LocationFilter;
import by.grodno.ss.rentacar.dataaccess.filters.TypeFilter;
import by.grodno.ss.rentacar.datamodel.Car;
import by.grodno.ss.rentacar.datamodel.CarStatus;
import by.grodno.ss.rentacar.datamodel.Location;
import by.grodno.ss.rentacar.datamodel.TransmissionType;
import by.grodno.ss.rentacar.datamodel.Type;
import by.grodno.ss.rentacar.service.CarService;
import by.grodno.ss.rentacar.service.LocationService;
import by.grodno.ss.rentacar.service.TypeService;
import by.grodno.ss.rentacar.webapp.common.CarStatusChoiceRenderer;
import by.grodno.ss.rentacar.webapp.common.LocationChoiceRenderer;
import by.grodno.ss.rentacar.webapp.common.TransmissionTypeChoiceRenderer;
import by.grodno.ss.rentacar.webapp.common.TypeChoiceRenderer;
import de.agilecoders.wicket.core.markup.html.bootstrap.components.TooltipBehavior;

public class CarEditPanel extends Panel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Inject
	private CarService carService;
	@Inject
	private TypeService typeService;
	@Inject
	private LocationService locationService;
	private Car car;
	private IModel<String> descName = Model.of("Enter your vehicle model/name");
	private IModel<String> descDesc = Model.of("Description");
	private IModel<String> descImage = Model.of("Select image");
	private IModel<String> descType = Model.of("Select type of car");
	private IModel<String> descReg = Model.of("National register car number");

	public CarEditPanel(String id, Car car) {
		super(id);
		this.car = car;
	}

	public CarEditPanel(String id, IModel<?> model, Car car) {
		super(id, model);
		this.car = car;
	}
	
	@Override
	public void onInitialize() {
		super.onInitialize();
		CarEditPanel.this.setOutputMarkupId(true);

		Form<Car> form = new Form<Car>("form", new CompoundPropertyModel<Car>(car));

		TextField<String> name = new TextField<String>("name");
		name.setRequired(true);
		name.add(StringValidator.maximumLength(100));
		name.add(StringValidator.minimumLength(2));
		name.add(new PatternValidator("[A-Za-z0-9 /-]+"));
		name.add(new TooltipBehavior(descName));
		form.add(name);
		
		TextField<String> description = new TextField<String>("description");
		description.setRequired(true);
		description.add(StringValidator.maximumLength(200));
		description.add(StringValidator.minimumLength(2));
		description.add(new PatternValidator("[A-Za-z0-9 /-]+"));
		description.add(new TooltipBehavior(descDesc));
		form.add(description);
		
		TextField<String> image = new TextField<String>("image");
		image.setRequired(true);
		image.add(StringValidator.maximumLength(200));
		image.add(StringValidator.minimumLength(5));
		image.add(new PatternValidator("[A-Za-z0-9 /_-]+"));
		image.add(new TooltipBehavior(descImage));
		form.add(image);
		
		List<Type> allTypes = typeService.find(new TypeFilter());
  		DropDownChoice<Type> typeDropDownType = new DropDownChoice<>("type", allTypes, TypeChoiceRenderer.INSTANCE);
  		typeDropDownType.setRequired(true);
  		typeDropDownType.add(new TooltipBehavior(descType));
        form.add(typeDropDownType);
        
		List<Location> allLocations = locationService.find(new LocationFilter());
  		DropDownChoice<Location> typeDropDownLoc = new DropDownChoice<>("location", allLocations, LocationChoiceRenderer.INSTANCE);
  		typeDropDownLoc.setRequired(true);
  		typeDropDownLoc.add(new TooltipBehavior(descType));
        form.add(typeDropDownLoc);
        
        DropDownChoice<CarStatus> statusDropDown = new DropDownChoice<>("carStatus", Arrays.asList(CarStatus.values()), CarStatusChoiceRenderer.INSTANCE);
        statusDropDown.setRequired(true);
        form.add(statusDropDown);        
        
        TextField<String> regNumber = new TextField<String>("regNumber");
        regNumber.setRequired(true);
        regNumber.add(StringValidator.maximumLength(20));
        regNumber.add(StringValidator.minimumLength(5));
        regNumber.add(new PatternValidator("[A-Za-z0-9 /-]+"));
        regNumber.add(new TooltipBehavior(descReg));
		form.add(regNumber);
		
		NumberTextField<Integer> yearProdaction = new NumberTextField<Integer>("yearProdaction");
		yearProdaction.setRequired(true);
		yearProdaction.setMinimum(1900);
		yearProdaction.setMaximum(Calendar.getInstance().get(Calendar.YEAR));
		form.add(yearProdaction);

		form.add(new SubmitLink("save") {
			private static final long serialVersionUID = 1L;

			@Override
			public void onSubmit() {
				carService.saveOrUpdate(car);
				info("Car was saved");
			}
		});

		add(form);

		add(new AjaxLink<Void>("back") {
			private static final long serialVersionUID = 1L;

			public void onClick(AjaxRequestTarget target) {
				Component newPanel = new CarListPanel(CarEditPanel.this.getId());
				CarEditPanel.this.replaceWith(newPanel);
				if (target != null) {
					target.add(newPanel);
				}
			}
		});
	}

}
