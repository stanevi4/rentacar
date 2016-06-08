package by.grodno.ss.rentacar.service.impl;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import by.grodno.ss.rentacar.dataaccess.CarDao;
import by.grodno.ss.rentacar.dataaccess.filters.CarFilter;
import by.grodno.ss.rentacar.datamodel.Car;
import by.grodno.ss.rentacar.service.CarService;

@Service
public class CarServiceImpl implements CarService {

	private static Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);
	
	@Inject
	private CarDao carDao;

	@Override
	public Long count(CarFilter filter) {
		return carDao.count(filter);
	}

	@Override
	public List<Car> find(CarFilter filter) {
		return carDao.find(filter);
	}

	@Override
	public void saveOrUpdate(Car car) {
		if (car.getId() == null) {
			carDao.insert(car);
			LOGGER.info("Car added: {}", car.getName());
		} else {
			carDao.update(car);
			LOGGER.info("Car updated: {}", car.getName());
		}		
	}

	@Override
	public void delete(Car car) {
		LOGGER.info("Car deleted: {}", car.getName());
		carDao.delete(car.getId());
	}
}
