package by.grodno.ss.rentacar.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import by.grodno.ss.rentacar.dataaccess.BookingDao;
import by.grodno.ss.rentacar.dataaccess.CarDao;
import by.grodno.ss.rentacar.dataaccess.filters.BookingFilter;
import by.grodno.ss.rentacar.dataaccess.filters.CarFilter;
import by.grodno.ss.rentacar.datamodel.Booking;
import by.grodno.ss.rentacar.datamodel.Booking_;
import by.grodno.ss.rentacar.datamodel.Car;
import by.grodno.ss.rentacar.datamodel.CarStatus;
import by.grodno.ss.rentacar.datamodel.OrderStatus;
import by.grodno.ss.rentacar.service.CarService;

@Service
public class CarServiceImpl implements CarService {

	private static Logger LOGGER = LoggerFactory.getLogger(CarServiceImpl.class);
	
	@Inject
	private CarDao carDao;
	@Inject
	private BookingDao bookingDao;
	

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

	@Override
	public List<Car> choose(CarFilter filter) {
		
		filter.setCarStatus(CarStatus.availible);
		List<Car> carList = carDao.find(filter);
		
		BookingFilter bookingFilter= new BookingFilter();
		//bookingFilter.setDateFrom(filter.getDateFrom());
		//bookingFilter.setDateTo(filter.getDateTo());
		//bookingFilter.setOrderStatus(OrderStatus.confirmed);
		
		//BookingFilter bookFilter= new BookingFilter();
		bookingFilter.setFetchCar(true);
		
		List<Car> reservedCars = bookingDao.choose(bookingFilter);
		
		return reservedCars;
	}
}
