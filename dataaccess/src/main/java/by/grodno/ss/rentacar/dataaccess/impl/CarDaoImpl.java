package by.grodno.ss.rentacar.dataaccess.impl;

import org.springframework.stereotype.Repository;

import by.grodno.ss.rentacar.dataaccess.CarDao;
import by.grodno.ss.rentacar.datamodel.Car;

@Repository
public class CarDaoImpl extends AbstractDaoImpl<Car, Long> implements CarDao {

	protected CarDaoImpl() {
		super(Car.class);
	}

}
