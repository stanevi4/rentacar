package by.grodno.ss.rentacar.webapp.page.car;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.metamodel.SingularAttribute;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormComponentUpdatingBehavior;
import org.apache.wicket.extensions.markup.html.repeater.data.sort.SortOrder;
import org.apache.wicket.extensions.markup.html.repeater.util.SortableDataProvider;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.ChoiceRenderer;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.SubmitLink;
import org.apache.wicket.markup.html.image.Image;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.request.resource.ContextRelativeResource;

import by.grodno.ss.rentacar.dataaccess.filters.CarFilter;
import by.grodno.ss.rentacar.dataaccess.filters.SelectOption;
import by.grodno.ss.rentacar.dataaccess.filters.TypeFilter;
import by.grodno.ss.rentacar.datamodel.Car;
import by.grodno.ss.rentacar.datamodel.Currency;
import by.grodno.ss.rentacar.datamodel.Type;
import by.grodno.ss.rentacar.datamodel.Type_;
import by.grodno.ss.rentacar.service.BookingService;
import by.grodno.ss.rentacar.service.CarService;
import by.grodno.ss.rentacar.service.SettingService;
import by.grodno.ss.rentacar.service.TypeService;
import by.grodno.ss.rentacar.webapp.common.TypeChoiceRenderer;
import by.grodno.ss.rentacar.webapp.page.AbstractPage;
import by.grodno.ss.rentacar.webapp.page.order.CheckoutPage;
import by.grodno.ss.rentacar.webapp.page.reservation.ReservationPage;
import de.agilecoders.wicket.core.markup.html.bootstrap.components.TooltipBehavior;
import de.agilecoders.wicket.core.markup.html.bootstrap.navigation.BootstrapPagingNavigator;

public class ChooseCarPage extends AbstractPage {
	private static final long serialVersionUID = 1L;
	private CarFilter filter;
	@Inject
	private CarService carService;
	@Inject
	private SettingService settingService;
	@Inject
	private BookingService bookingService;
	@Inject
	private TypeService typeService;

	private String IMAGE_FOLDER = "/images/cars/";
	private IModel<String> descPass = Model.of("Number of passengers");
	private IModel<String> descBags = Model.of("Number of bags");
	private IModel<String> descDoors = Model.of("Number of doors");
	private IModel<String> descTrans = Model.of("Transmission type");

	public ChooseCarPage(CarFilter filter) {
		super();
		this.filter = filter;
	}

	public ChooseCarPage(PageParameters parameters, CarFilter filter) {
		super(parameters);
		this.filter = filter;
	}

	@Override
	protected void onInitialize() {
		super.onInitialize();
		
		SimpleDateFormat dt = new SimpleDateFormat("dd.MM.yyy HH:mm");
		add(new Label("duration", bookingService.convertDurationToString(filter.getDateFrom(), filter.getDateTo())));
		add(new Label("dateFrom", dt.format(filter.getDateFrom())));
		add(new Label("locationFrom", filter.getLocationFrom().getName()));
		add(new Label("dateTo", dt.format(filter.getDateTo())));
		add(new Label("locationTo", filter.getLocationTo().getName()));
		add(new Link<Void>("link-change") {
			private static final long serialVersionUID = 1L;

			@Override
			public void onClick() {
				setResponsePage(new ReservationPage(filter));
			}
		});

		CarsDataProvider carsDataProvider = new CarsDataProvider(filter);
		DataView<Car> dataView = new DataView<Car>("rows", carsDataProvider, 5) {
			private static final long serialVersionUID = 1L;

			@Override
			protected void populateItem(Item<Car> item) {

				Car car = item.getModelObject();
				addImage(item, car);
				item.add(new Label("name", car.getName()));
				item.add(new Label("type", car.getType().getName()));
				item.add(new Label("total-price", car.getType().getPricePerHour().toString()));
				addPriceIcon(item);
				item.add(new Label("pass", car.getType().getNumPassengers()));
				item.add(new Label("bags", car.getType().getNumBags()));
				item.add(new Label("doors", car.getType().getNumDoors()));
				item.add(new Label("trans", car.getType().getTransmissionType()));
				item.add(new Label("description", car.getDescription()));

				Form<Void> form = new Form<Void>("form-submit-continue");
				form.add(new SubmitLink("submit-continue") {
					private static final long serialVersionUID = 1L;

					@Override
					public void onSubmit() {
						super.onSubmit();
						setResponsePage(new CheckoutPage(car, filter));
					}
				});
				item.add(form);

				item.add(new WebMarkupContainer("descPass").add(new TooltipBehavior(descPass)));
				item.add(new WebMarkupContainer("descBags").add(new TooltipBehavior(descBags)));
				item.add(new WebMarkupContainer("descDoors").add(new TooltipBehavior(descDoors)));
				item.add(new WebMarkupContainer("descTrans").add(new TooltipBehavior(descTrans)));
			}
		};
		add(dataView);
		BootstrapPagingNavigator pager = new BootstrapPagingNavigator("pager", dataView);
		add(pager);

		List<Type> allTypes = typeService.find(new TypeFilter());
		PropertyModel<Type> typeModel = new PropertyModel<Type>(filter, "type");
		DropDownChoice<Type> typeDropDownType = new DropDownChoice<Type>("type", typeModel, allTypes,
				TypeChoiceRenderer.INSTANCE);
		typeDropDownType.setRequired(false); // allow choise null value ("All")
		typeDropDownType.add(new AjaxFormComponentUpdatingBehavior("onchange") {
			private static final long serialVersionUID = 1L;

			@Override
			protected void onUpdate(AjaxRequestTarget target) {
				ChooseCarPage.this.filter.setType(typeDropDownType.getModelObject());
				setResponsePage(new ChooseCarPage(ChooseCarPage.this.filter));
			}
		});
		typeDropDownType.setNullValid(true);
		typeDropDownType.setMarkupId("type");
		add(typeDropDownType);

		SelectOption priceAsc = new SelectOption("priceAsc", "Price (Low to High)");
		SelectOption priceDesc = new SelectOption("priceDesc", "Price (High to Low)");
		SelectOption[] options = new SelectOption[] { priceAsc, priceDesc };
		ChoiceRenderer choiceRenderer = new ChoiceRenderer("value", "key");
		DropDownChoice<SelectOption> typeDropDownSort = (new DropDownChoice("sort",
			new PropertyModel<>(this.filter, "selectOption"), Arrays.asList(options), choiceRenderer) {
			@Override
			protected CharSequence getDefaultChoice(String selectedValue) {
				return " ";
			}
		});
		typeDropDownSort.add(new AjaxFormComponentUpdatingBehavior("change") {
			private static final long serialVersionUID = 1L;
			@Override
			protected void onUpdate(AjaxRequestTarget target) {
				ChooseCarPage.this.filter.setSortProperty(Type_.pricePerHour);
				if (typeDropDownSort.getModelObject().getKey().equals("priceAsc")) {
					ChooseCarPage.this.filter.setSortOrder(true);
				} else if (typeDropDownSort.getModelObject().getKey().equals("priceDesc")) {
					ChooseCarPage.this.filter.setSortOrder(false);
				}
				setResponsePage(new ChooseCarPage(ChooseCarPage.this.filter));
			}
		});
		add(typeDropDownSort);
	}

	private class CarsDataProvider extends SortableDataProvider<Car, Serializable> {
		private static final long serialVersionUID = 1L;
		private CarFilter carFilter;

		public CarsDataProvider(CarFilter filter) {
			super();
			this.carFilter = filter;
			if (filter.getSortProperty() == null) {
				setSort((Serializable) Type_.pricePerHour, SortOrder.ASCENDING);
			} else {
				setSort((Serializable) carFilter.getSortProperty(),
						carFilter.isSortOrder() == true ? SortOrder.ASCENDING : SortOrder.DESCENDING);
			}
		}

		@Override
		public Iterator<Car> iterator(long first, long count) {
			Serializable property = getSort().getProperty();
			SortOrder propertySortOrder = getSortState().getPropertySortOrder(property);

			carFilter.setSortProperty((SingularAttribute<?, ?>) property);
			carFilter.setSortOrder(propertySortOrder.equals(SortOrder.ASCENDING) ? true : false);
			carFilter.setLimit((int) count);
			carFilter.setOffset((int) first);
			carFilter.setFetchLocations(true);
			carFilter.setFetchTypes(true);

			return carService.find(carFilter).iterator();
		}

		@Override
		public long size() {
			return carService.count(carFilter);
		}

		@Override
		public IModel<Car> model(Car object) {
			return new Model(object);
		}
	}

	private void addImage(Item<Car> item, Car car) {
		Image carImage = new Image("image", new ContextRelativeResource(IMAGE_FOLDER + car.getImage()));
		//carImage.add(new AttributeModifier("height", "220"));
		//carImage.add(new AttributeModifier("width", "140"));
		//String alt = car.getName();
		item.add(carImage);		
	}

	private void addPriceIcon(Item<Car> item) {
		Currency currency = settingService.get().getCurrency();
		WebMarkupContainer i = new WebMarkupContainer("iconPrice");
		if (currency.equals(Currency.USD)) {
			i.add(new AttributeModifier("class", "fa fa-usd"));
		} else if (currency.equals(Currency.EUR)) {
			i.add(new AttributeModifier("class", "fa fa-eur"));
		} else if (currency.equals(Currency.GBR)) {
			i.add(new AttributeModifier("class", "fa fa-gbp"));
		} else {
			i.add(new AttributeModifier("class", "fa fa-rub"));
		}
		item.add(i);
	}
}
