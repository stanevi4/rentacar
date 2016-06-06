package by.grodno.ss.rentacar.webapp.page.admin.dataprovider;

import java.io.Serializable;
import java.util.Iterator;

import javax.inject.Inject;
import javax.persistence.metamodel.SingularAttribute;

import org.apache.wicket.extensions.markup.html.repeater.data.sort.SortOrder;
import org.apache.wicket.extensions.markup.html.repeater.util.SortableDataProvider;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import by.grodno.ss.rentacar.dataaccess.filters.LocationFilter;
import by.grodno.ss.rentacar.datamodel.Location;
import by.grodno.ss.rentacar.datamodel.Location_;
import by.grodno.ss.rentacar.service.LocationService;

public class LocationsDataProvider extends SortableDataProvider<Location, Serializable> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Inject
	private LocationService locationService;
	private LocationFilter locationFilter;
	
	public LocationsDataProvider() {
		super();
		locationFilter = new LocationFilter();
        setSort((Serializable) Location_.name, SortOrder.ASCENDING);
	}

	@Override
	public Iterator<? extends Location> iterator(long first, long count) {
		Serializable property = getSort().getProperty();
        SortOrder propertySortOrder = getSortState().getPropertySortOrder(property);

        locationFilter.setSortProperty((SingularAttribute) property);
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
