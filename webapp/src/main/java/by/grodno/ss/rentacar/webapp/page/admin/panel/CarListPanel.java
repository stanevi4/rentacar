package by.grodno.ss.rentacar.webapp.page.admin.panel;

import java.io.Serializable;
import java.util.Iterator;

import javax.inject.Inject;
import javax.persistence.PersistenceException;
import javax.persistence.metamodel.SingularAttribute;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.extensions.markup.html.repeater.data.sort.OrderByBorder;
import org.apache.wicket.extensions.markup.html.repeater.data.sort.SortOrder;
import org.apache.wicket.extensions.markup.html.repeater.util.SortableDataProvider;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.navigation.paging.PagingNavigator;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import by.grodno.ss.rentacar.dataaccess.filters.CarFilter;
import by.grodno.ss.rentacar.datamodel.Car;
import by.grodno.ss.rentacar.datamodel.Car_;
import by.grodno.ss.rentacar.service.CarService;
import by.grodno.ss.rentacar.webapp.page.admin.CarsEditPage;
import de.agilecoders.wicket.core.markup.html.bootstrap.components.TooltipBehavior;

public class CarListPanel extends Panel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Inject
	private CarService carService;
	
	private IModel<String> descDeleteButton = Model.of("Delete item");
	private IModel<String> descEditButton = Model.of("View / edit item");

	public CarListPanel(String id) {
		super(id);
	}

	public CarListPanel(String id, IModel<?> model) {
		super(id, model);
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
				item.add(new Label("image", car.getImage()));
				item.add(new Label("name", car.getName()));
				item.add(new Label("type", car.getType().getName()));
				item.add(new Label("location", car.getLocation().getName()));
				item.add(new Label("status", car.getCarStatus()));
				addEditButton(CarListPanel.this.getId(), item, car);
				addDeleteButton(item, car);
			}
		};
		add(dataView);
		add(new PagingNavigator("paging", dataView));

		add(new OrderByBorder("sort-name", Car_.name, carsDataProvider));
		add(new OrderByBorder("sort-type", Car_.type, carsDataProvider));
		add(new OrderByBorder("sort-location", Car_.location, carsDataProvider));
		add(new OrderByBorder("sort-status", Car_.carStatus, carsDataProvider));

		addButtonNew(this.getId());
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

	private void addEditButton(String id, Item<Car> item, Car car) {
		AjaxLink<Void> buttonEdit = new AjaxLink<Void>("edit-link") {
			private static final long serialVersionUID = 1L;

			@Override
			public void onClick(AjaxRequestTarget target) {
				Component newPanel = new CarEditPanel(id, car);
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

			public void onClick(AjaxRequestTarget target) {
				Component newPanel = new CarEditPanel(id, new Car());
				CarListPanel.this.replaceWith(newPanel);
				if (target != null) {
					target.add(newPanel);
				}
			}
		};
		add(buttonNewItem);
	}

}
