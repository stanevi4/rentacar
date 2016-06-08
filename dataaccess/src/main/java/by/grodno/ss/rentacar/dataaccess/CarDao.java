package by.grodno.ss.rentacar.dataaccess;

import java.util.List;

import by.grodno.ss.rentacar.dataaccess.filters.CarFilter;
import by.grodno.ss.rentacar.datamodel.Car;

public interface CarDao extends AbstractDao<Car, Long> {

	Long count(CarFilter filter);

    List<Car> find(CarFilter filter);
}
