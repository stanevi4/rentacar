package by.grodno.ss.rentacar.service.impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import by.grodno.ss.rentacar.dataaccess.LocationDao;
import by.grodno.ss.rentacar.dataaccess.filters.LocationFilter;
import by.grodno.ss.rentacar.datamodel.Location;
import by.grodno.ss.rentacar.service.LocationService;

@Service
public class LocationServiceImpl implements LocationService {

	@Inject
	LocationDao locationDao;
	
	@Override
	public Long count(LocationFilter filter) {
		return locationDao.count(filter);
	}

	@Override
	public List<Location> find(LocationFilter filter) {
		return locationDao.find(filter);
	}

	@Override
	public void saveOrUpdate(Location location) {
		 if (location.getId() == null) {
	            locationDao.insert(location);
	        } else {
	            locationDao.update(location);
	        }		
	}

	@Override
	public void delete(Location location) {
		locationDao.delete(location.getId());	
	}

}
