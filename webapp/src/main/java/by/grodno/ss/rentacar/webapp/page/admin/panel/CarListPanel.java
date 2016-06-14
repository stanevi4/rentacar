package by.grodno.ss.rentacar.webapp.page.admin.panel;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.PersistenceException;
import javax.persistence.metamodel.SingularAttribute;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormComponentUpdatingBehavior;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.extensions.markup.html.repeater.data.sort.OrderByBorder;
import org.apache.wicket.extensions.markup.html.repeater.data.sort.SortOrder;
import org.apache.wicket.extensions.markup.html.repeater.util.SortableDataProvider;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.image.ContextImage;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import by.grodno.ss.rentacar.dataaccess.filters.CarFilter;
import by.grodno.ss.rentacar.dataaccess.filters.LocationFilter;
import by.grodno.ss.rentacar.dataaccess.filters.TypeFilter;
import by.grodno.ss.rentacar.datamodel.Car;
import by.grodno.ss.rentacar.datamodel.Car_;
import by.grodno.ss.rentacar.datamodel.Location;
import by.grodno.ss.rentacar.datamodel.Type;
import by.grodno.ss.rentacar.service.CarService;
import by.grodno.ss.rentacar.service.LocationService;
import by.grodno.ss.rentacar.service.TypeService;
import by.grodno.ss.rentacar.webapp.common.LocationChoiceRenderer;
import by.grodno.ss.rentacar.webapp.common.TypeChoiceRenderer;
import by.grodno.ss.rentacar.webapp.page.admin.CarsEditPage;
import de.agilecoders.wicket.core.markup.html.bootstrap.components.TooltipBehavior;
import de.agilecoders.wicket.core.markup.html.bootstrap.navigation.BootstrapPagingNavigator;

public class CarListPanel extends Panel {
	private static final long serialVersionUID = 1L;
	@Inject
	private CarService carService;
	@Inject
	private TypeService typeService;
	@Inject
	private LocationService locationService;
	private CarFilter filter;
	private String IMAGE_FOLDER = "/images/cars/";
	private IModel<String> descDeleteButton = Model.of("Delete item");
	private IModel<String> descEditButton = Model.of("View / edit item");

	public CarListPanel(String id, CarFilter filter) {
		super(id);
		this.filter = filter;
	}

	public CarListPanel(String id, IModel<?> model) {
		super(id, model);
	}

	@Override
	public void onInitialize() {
		super.onInitialize();
		setOutputMarkupId(true);

		WebMarkupContainer wmc = new WebMarkupContainer("table");
		wmc.setOutputMarkupId(true);

		Form<CarFilter> form = new Form<CarFilter>("selections", new CompoundPropertyModel<CarFilter>(filter));

		List<Type> allTypes = typeService.find(new TypeFilter());
		DropDownChoice<Type> typeDropDown = new DropDownChoice<>("type", allTypes, TypeChoiceRenderer.INSTANCE);
		typeDropDown.setRequired(false);
		typeDropDown.setNullValid(true);
		typeDropDown.add(new AjaxFormComponentUpdatingBehavior("change") {
			private static final long serialVersionUID = 1L;

			@Override
			protected void onUpdate(AjaxRequestTarget target) {
				if (target != null) {
					target.add(wmc);
				}
			}
		});
		form.add(typeDropDown);

		List<Location> allLocations = locationService.find(new LocationFilter());
		DropDownChoice<Location> locDropDown = new DropDownChoice<>("location", allLocations,
				LocationChoiceRenderer.INSTANCE);
		locDropDown.setRequired(false);
		locDropDown.setNullValid(true);
		locDropDown.add(new AjaxFormComponentUpdatingBehavior("change") {
			private static final long serialVersionUID = 1L;

			@Override
			protected void onUpdate(AjaxRequestTarget target) {
				if (target != null) {
					target.add(wmc);
				}
			}
		});
		form.add(locDropDown);
		
		add(form);

		CarsDataProvider carsDataProvider = new CarsDataProvider(filter);
		DataView<Car> dataView = new DataView<Car>("rows", carsDataProvider, 10) {
			private static final long serialVersionUID = 1L;

			@Override
			protected void populateItem(Item<Car> item) {

				Car car = item.getModelObject();
				addImage(item, car);
				item.add(new Label("name", car.getName()));
				item.add(new Label("type", car.getType().getName()));
				item.add(new Label("location", car.getLocation().getName()));
				item.add(new Label("status", car.getCarStatus()));
				addEditButton(CarListPanel.this.getId(), item, car);
				addDeleteButton(item, car);
			}
		};
		wmc.add(dataView);
		BootstrapPagingNavigator pager = new BootstrapPagingNavigator("paging", dataView);
		add(pager);

		wmc.add(new OrderByBorder("sort-name", Car_.name, carsDataProvider));
		wmc.add(new OrderByBorder("sort-type", Car_.type, carsDataProvider));
		wmc.add(new OrderByBorder("sort-location", Car_.location, carsDataProvider));
		wmc.add(new OrderByBorder("sort-status", Car_.carStatus, carsDataProvider));

		addButtonNew(this.getId());
		
		add(wmc);
	}

	private class CarsDataProvider extends SortableDataProvider<Car, Serializable> {
		private static final long serialVersionUID = 1L;
		private CarFilter carFilter;

		public CarsDataProvider(CarFilter carFilter) {
			super();
			this.carFilter = filter;
			setSort((Serializable) Car_.name, SortOrder.ASCENDING);
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

	private void addEditButton(String id, Item<Car> item, Car car) {
		AjaxLink<Void> buttonEdit = new AjaxLink<Void>("edit-link") {
			private static final long serialVersionUID = 1L;

			@Override
			public void onClick(AjaxRequestTarget target) {
				Component newPanel = new CarEditPanel(id, car, filter);
				CarListPanel.this.replaceWith(newPanel);
				if (target != null) {
					target.add(newPanel);
				}
			}
		};
		buttonEdit.add(new TooltipBehavior(descEditButton));
		item.add(buttonEdit);
	}

	private void addDeleteButton(Item<Car> item, Car car) {
		item.add(new Link<Void>("delete-link") {
			@Override
			public void onClick() {
				try {
					carService.delete(car);
				} catch (PersistenceException e) {
					System.out.println("caughth persistance exception");
				}
				setResponsePage(new CarsEditPage());
			}
		}.add(new TooltipBehavior(descDeleteButton)));
	}

	private void addButtonNew(String id) {
		AjaxLink<Void> buttonNewItem = new AjaxLink<Void>("add-new-item") {
			private static final long serialVersionUID = 1L;

			@Override
			public void onClick(AjaxRequestTarget target) {
				Component newPanel = new CarEditPanel(id, new Car(), filter);
				CarListPanel.this.replaceWith(newPanel);
				if (target != null) {
					target.add(newPanel);
				}
			}
		};
		add(buttonNewItem);
	}

	private void addImage(Item<Car> item, Car car) {
		ContextImage carImage = new ContextImage("image", (IMAGE_FOLDER + car.getImage()));
		// Image carImage = new Image("image", new
		// ContextRelativeResource(IMAGE_FOLDER + car.getImage()));
		carImage.add(new AttributeModifier("height", "100"));
		carImage.add(new AttributeModifier("width", "160"));
		String alt = car.getName();
		carImage.add(new AttributeModifier("alt", alt));
		item.add(carImage);
	}
}
