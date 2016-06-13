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

import by.grodno.ss.rentacar.dataaccess.filters.LocationFilter;
import by.grodno.ss.rentacar.datamodel.Location;
import by.grodno.ss.rentacar.datamodel.Location_;
import by.grodno.ss.rentacar.service.LocationService;
import by.grodno.ss.rentacar.webapp.page.admin.LocationsEditPage;
import de.agilecoders.wicket.core.markup.html.bootstrap.components.TooltipBehavior;
import de.agilecoders.wicket.core.markup.html.bootstrap.navigation.BootstrapPagingNavigator;

public class LocationListPanel extends Panel {

	private static final long serialVersionUID = 1L;
	@Inject
	private LocationService locationService;
	private IModel<String> descDeleteButton = Model.of("Delete item");
	private IModel<String> descEditButton = Model.of("View / edit item");

	public LocationListPanel(String id) {
		super(id);
	}

	@Override
	public void onInitialize() {
		super.onInitialize();
		setOutputMarkupId(true);
		ProductsDataProvider locationsDataProvider = new ProductsDataProvider();
		DataView<Location> dataView = new DataView<Location>("rows", locationsDataProvider, 10) {
			private static final long serialVersionUID = 1L;

			@Override
			protected void populateItem(Item<Location> item) {

				Location location = item.getModelObject();
				item.add(new Label("id", location.getId()));
				item.add(new Label("name", location.getName()));
				item.add(new Label("lat", location.getLat()));
				item.add(new Label("lng", location.getLng()));
				addEditButton(LocationListPanel.this.getId(), item, location);
				addDeleteButton(item, location);
			}
		};
		add(dataView);
		BootstrapPagingNavigator pager = new BootstrapPagingNavigator("paging", dataView);
		add(pager);

		add(new OrderByBorder("sort-id", Location_.id, locationsDataProvider));
		add(new OrderByBorder("sort-name", Location_.name, locationsDataProvider));

		addButtonNew(this.getId());
	}

	private class ProductsDataProvider extends SortableDataProvider<Location, Serializable> {

		private LocationFilter locationFilter;

		public ProductsDataProvider() {
			super();
			locationFilter = new LocationFilter();
			setSort((Serializable) Location_.name, SortOrder.ASCENDING);
		}

		@Override
		public Iterator<Location> iterator(long first, long count) {
			Serializable property = getSort().getProperty();
			SortOrder propertySortOrder = getSortState().getPropertySortOrder(property);

			locationFilter.setSortProperty((SingularAttribute<?, ?>) property);
			locationFilter.setSortOrder(propertySortOrder.equals(SortOrder.ASCENDING) ? true : false);

			locationFilter.setLimit((int) count);
			locationFilter.setOffset((int) first);
			return locationService.find(locationFilter).iterator();
		}

		@Override
		public long size() {
			return locationService.count(locationFilter);
		}

		@Override
		public IModel<Location> model(Location object) {
			return new Model(object);
		}

	}
	
	private void addEditButton(String id, Item<Location> item, Location location) {
		AjaxLink<Void> buttonEdit = new AjaxLink<Void>("edit-link") {
			private static final long serialVersionUID = 1L;
			@Override
			public void onClick(AjaxRequestTarget target) {
				Component newPanel = new LocationEditPanel(id, location);
				LocationListPanel.this.replaceWith(newPanel);
				if (target != null) {
					target.add(newPanel);
				}
			}
		};
		buttonEdit.add(new TooltipBehavior(descEditButton));
		item.add(buttonEdit);
	}
	
	private void addDeleteButton(Item<Location> item, Location location) {
		item.add(new Link<Void>("delete-link") {
			@Override
			public void onClick() {
				try {
					locationService.delete(location);
				} catch (PersistenceException e) {
					System.out.println("caughth persistance exception");
				}
				setResponsePage(new LocationsEditPage());
			}
		}.add(new TooltipBehavior(descDeleteButton)));
	}

	private void addButtonNew(String id) {
		AjaxLink<Void> buttonNewItem = new AjaxLink<Void>("add-new-item") {
			private static final long serialVersionUID = 1L;
			public void onClick(AjaxRequestTarget target) {
				Component newPanel = new LocationEditPanel(id, new Location());
				LocationListPanel.this.replaceWith(newPanel);
				if (target != null) {
					target.add(newPanel);
				}
			}
		};
		add(buttonNewItem);
	}
}
