package by.grodno.ss.rentacar.webapp.page.admin.panel;

import java.io.Serializable;
import java.util.Iterator;

import javax.inject.Inject;
import javax.persistence.PersistenceException;
import javax.persistence.metamodel.SingularAttribute;

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

public class LocationListPanel extends Panel {

	private static final long serialVersionUID = 1L;
	@Inject
	private LocationService locationService;

	public LocationListPanel(String id) {
		super(id);
	}
	
	@Override
	public void onInitialize(){
		super.onInitialize();
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
                
                item.add(new Link<Void>("edit-link") {
                    @Override
                    public void onClick() {
                        //setResponsePage(new ProductEditPage(product));
                    }
                });
                
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
                });
            }
        };
        add(dataView);
        add(new PagingNavigator("paging", dataView));
        
        add(new OrderByBorder("sort-id", Location_.id, locationsDataProvider));
        add(new OrderByBorder("sort-name", Location_.name, locationsDataProvider));
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
}
