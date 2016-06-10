package by.grodno.ss.rentacar.webapp.page.admin.dataprovider;

import java.io.Serializable;
import java.util.Iterator;

import javax.inject.Inject;
import javax.persistence.metamodel.SingularAttribute;

import org.apache.wicket.extensions.markup.html.repeater.data.sort.SortOrder;
import org.apache.wicket.extensions.markup.html.repeater.util.SortableDataProvider;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import by.grodno.ss.rentacar.dataaccess.filters.CarFilter;
import by.grodno.ss.rentacar.datamodel.Car;
import by.grodno.ss.rentacar.datamodel.Car_;
import by.grodno.ss.rentacar.service.CarService;

public class CarsDataProvider2 extends SortableDataProvider<Car, Serializable> {
	private static final long serialVersionUID = 1L;
	@Inject
	private CarService carService;
	private CarFilter carFilter;

	public CarsDataProvider2() {
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
