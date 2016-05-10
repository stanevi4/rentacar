package by.grodno.ss.rentacar.service.impl;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import by.grodno.ss.rentacar.dataaccess.CarDao;
import by.grodno.ss.rentacar.datamodel.Car;
import by.grodno.ss.rentacar.service.CarService;

@Service
public class CarServiceImpl implements CarService {

	private static Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);
	
	@Inject
	private CarDao carDao;

	@Override
	public Car get(Long id) {
		return carDao.get(id);
	}

	@Override
	public void update(Car car) {
		carDao.update(car);
		LOGGER.info("Car updated: {}", car.getModel().getModelName());
	}

	@Override
	public void register(Car car) {
		carDao.insert(car);
		LOGGER.info("Model registered: {}", car.getModel().getModelName());
	}

	@Override
	public void delete(Long id) {
		LOGGER.info("Model deleted: {}", carDao.get(id).getModel().getModelName());
		carDao.delete(id);
	}

}
