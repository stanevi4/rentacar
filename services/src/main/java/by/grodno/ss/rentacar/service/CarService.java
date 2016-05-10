package by.grodno.ss.rentacar.service;

import javax.transaction.Transactional;

import by.grodno.ss.rentacar.datamodel.Car;

public interface CarService {

	Car get(Long id);

	@Transactional
	void update(Car car);
	
	@Transactional
	void register(Car car);
	
	@Transactional
	void delete(Long id);
}
