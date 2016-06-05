package by.grodno.ss.rentacar.dataaccess.impl;

import org.springframework.stereotype.Repository;

import by.grodno.ss.rentacar.dataaccess.LocationDao;
import by.grodno.ss.rentacar.datamodel.Location;

@Repository
public class LocationDaoImpl extends AbstractDaoImpl<Location, Long> implements LocationDao {

	protected LocationDaoImpl() {
		super(Location.class);
	}
	

}
