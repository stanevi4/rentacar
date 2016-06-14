package by.grodno.ss.rentacar.service;

import java.util.List;

import javax.transaction.Transactional;

import by.grodno.ss.rentacar.dataaccess.filters.CarFilter;
import by.grodno.ss.rentacar.datamodel.Car;

public interface CarService {

	Long count(CarFilter filter);

    List<Car> find(CarFilter filter);

    @Transactional
    void saveOrUpdate(Car car);

    @Transactional
    void delete(Car car);
    
    List<Car> reserved(CarFilter filter, int timeBetweenBookings);
}
