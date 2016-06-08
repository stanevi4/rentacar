package by.grodno.ss.rentacar.webapp.page.admin.panel;

import java.math.BigDecimal;
import java.util.Arrays;

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

import by.grodno.ss.rentacar.datamodel.TransmissionType;
import by.grodno.ss.rentacar.datamodel.Type;
import by.grodno.ss.rentacar.service.TypeService;
import by.grodno.ss.rentacar.webapp.common.TransmissionTypeChoiceRenderer;
import de.agilecoders.wicket.core.markup.html.bootstrap.components.TooltipBehavior;

public class TypeEditPanel extends Panel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Inject
	private TypeService typeService;
	private Type type;
	private IModel<String> descName = Model.of("Enter type name");
	private IModel<String> descPass = Model.of("Number of passengers");
	private IModel<String> descBags = Model.of("Number of bags");
	private IModel<String> descDoors = Model.of("Number of doors");
	private IModel<String> descTrans = Model.of("Transmission type");
	private IModel<String> descPrice = Model.of("Price per hour");

	public TypeEditPanel(String id, Type type) {
		super(id);
		this.type = type;
	}

	public TypeEditPanel(String id, IModel<?> model, Type type) {
		super(id, model);
		this.type = type;
	}

	@Override
	public void onInitialize() {
		super.onInitialize();
		TypeEditPanel.this.setOutputMarkupId(true);

		Form<Type> form = new Form<Type>("form", new CompoundPropertyModel<Type>(type));

		TextField<String> name = new TextField<String>("name");
		name.setRequired(true);
		name.add(StringValidator.maximumLength(100));
		name.add(StringValidator.minimumLength(2));
		name.add(new PatternValidator("[A-Za-z0-9 /-]+"));
		name.add(new TooltipBehavior(descName));
		form.add(name);

		NumberTextField<Integer> pass = new NumberTextField<Integer>("numPassengers");
		pass.setRequired(true);
		pass.setMinimum(1);
		pass.setMaximum(99);
		pass.add(new TooltipBehavior(descPass));
		form.add(pass);

		NumberTextField<Integer> bags = new NumberTextField<Integer>("numBags");
		bags.setRequired(true);
		bags.setMinimum(1);
		bags.setMaximum(99);
		bags.add(new TooltipBehavior(descBags));
		form.add(bags);
		
		NumberTextField<Integer> doors = new NumberTextField<Integer>("numDoors");
		doors.setRequired(true);
		doors.setMinimum(1);
		doors.setMaximum(99);
		doors.add(new TooltipBehavior(descDoors));
		form.add(doors);
		
		DropDownChoice<TransmissionType> transmissionDropDown = new DropDownChoice<>("transmissionType", Arrays.asList(TransmissionType.values()), TransmissionTypeChoiceRenderer.INSTANCE);
		transmissionDropDown.setRequired(true);
		transmissionDropDown.add(new TooltipBehavior(descTrans));
        form.add(transmissionDropDown);
        
        NumberTextField<BigDecimal> price = new NumberTextField<BigDecimal>("pricePerHour");
        price.setRequired(true);
        price.setMinimum(Model.of(new BigDecimal("0.01")));
        price.setMaximum(new BigDecimal("100000.00"));
        price.add(new TooltipBehavior(descPrice));
		form.add(price);

		form.add(new SubmitLink("save") {
			private static final long serialVersionUID = 1L;

			@Override
			public void onSubmit() {
				typeService.saveOrUpdate(type);
				info("Type was saved");
			}
		});

		add(form);

		add(new AjaxLink<Void>("back") {
			private static final long serialVersionUID = 1L;

			public void onClick(AjaxRequestTarget target) {
				Component newPanel = new TypeListPanel(TypeEditPanel.this.getId());
				TypeEditPanel.this.replaceWith(newPanel);
				if (target != null) {
					target.add(newPanel);
				}
			}
		});
	}

}
