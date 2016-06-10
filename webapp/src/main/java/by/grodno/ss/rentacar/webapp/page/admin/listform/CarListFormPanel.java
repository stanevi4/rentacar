package by.grodno.ss.rentacar.webapp.page.admin.listform;

import java.io.Serializable;
import java.util.Iterator;

import javax.inject.Inject;
import javax.persistence.metamodel.SingularAttribute;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.extensions.markup.html.repeater.data.sort.OrderByBorder;
import org.apache.wicket.extensions.markup.html.repeater.data.sort.SortOrder;
import org.apache.wicket.extensions.markup.html.repeater.util.SortableDataProvider;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.image.Image;
import org.apache.wicket.markup.html.navigation.paging.PagingNavigator;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.resource.ContextRelativeResource;

import by.grodno.ss.rentacar.dataaccess.filters.CarFilter;
import by.grodno.ss.rentacar.datamodel.Booking;
import by.grodno.ss.rentacar.datamodel.Car;
import by.grodno.ss.rentacar.datamodel.Car_;
import by.grodno.ss.rentacar.service.CarService;
import by.grodno.ss.rentacar.webapp.page.admin.panel.ReservationEditPanel;
import by.grodno.ss.rentacar.webapp.page.admin.panel.ReservationListPanel;
import de.agilecoders.wicket.core.markup.html.bootstrap.components.TooltipBehavior;

public class CarListFormPanel extends Panel {
	private static final long serialVersionUID = 1L;
	private Booking booking;
	@Inject
	private CarService carService;
	private String IMAGE_FOLDER = "/images/cars/";
	private IModel<String> descSelect = Model.of("Select this vehicle");

	public CarListFormPanel(String id, Booking booking) {
		super(id);
		this.booking = booking;
	}

	public CarListFormPanel(String id, IModel<?> model, Booking booking) {
		super(id, model);
		this.booking = booking;
	}
	
	@Override
	public void onInitialize() {
		super.onInitialize();
		setOutputMarkupId(true);
		CarsDataProvider carsDataProvider = new CarsDataProvider();
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
				addSelectButton(CarListFormPanel.this.getId(), item, car);
			}
		};
		add(dataView);
		add(new PagingNavigator("paging", dataView));

		add(new OrderByBorder("sort-name", Car_.name, carsDataProvider));
		add(new OrderByBorder("sort-type", Car_.type, carsDataProvider));
		add(new OrderByBorder("sort-location", Car_.location, carsDataProvider));
		add(new OrderByBorder("sort-status", Car_.carStatus, carsDataProvider));
		
		add(new AjaxLink<Void>("back") {
			private static final long serialVersionUID = 1L;

			public void onClick(AjaxRequestTarget target) {
				Component newPanel = new ReservationEditPanel(CarListFormPanel.this.getId(), booking);
				CarListFormPanel.this.replaceWith(newPanel);
				if (target != null) {
					target.add(newPanel);
				}
			}
		});
	}

	private class CarsDataProvider extends SortableDataProvider<Car, Serializable> {
		private static final long serialVersionUID = 1L;
		private CarFilter carFilter;

		public CarsDataProvider() {
			super();
			carFilter = new CarFilter();
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

	private void addSelectButton(String id, Item<Car> item, Car car) {
		AjaxLink<Void> buttonSelect = new AjaxLink<Void>("select-link") {
			private static final long serialVersionUID = 1L;

			@Override
			public void onClick(AjaxRequestTarget target) {
				booking.setCar(car);
				Component newPanel = new ReservationEditPanel(id, booking);
				CarListFormPanel.this.replaceWith(newPanel);
				if (target != null) {
					target.add(newPanel);
				}
			}
		};
		buttonSelect.add(new TooltipBehavior(descSelect));
		item.add(buttonSelect);
	}

	private void addImage(Item<Car> item, Car car) {
		Image carImage = new Image("image", new ContextRelativeResource(IMAGE_FOLDER + car.getImage()));
		carImage.add(new AttributeModifier("height","80"));
		carImage.add(new AttributeModifier("width","128"));
		String alt = car.getName();
		carImage.add(new AttributeModifier("alt",alt));
		item.add(carImage);
	}

}
