package by.grodno.ss.rentacar.service;

import java.util.List;

import javax.transaction.Transactional;

import by.grodno.ss.rentacar.dataaccess.filters.LocationFilter;
import by.grodno.ss.rentacar.datamodel.Location;

public interface LocationService {

	Long count(LocationFilter filter);

    List<Location> find(LocationFilter filter);

    @Transactional
    void saveOrUpdate(Location location);

    @Transactional
    void delete(Location location);
}
