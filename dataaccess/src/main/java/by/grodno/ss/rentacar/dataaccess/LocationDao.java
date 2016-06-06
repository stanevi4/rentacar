package by.grodno.ss.rentacar.dataaccess;

import java.util.List;

import by.grodno.ss.rentacar.dataaccess.filters.LocationFilter;
import by.grodno.ss.rentacar.datamodel.Location;

public interface LocationDao extends AbstractDao<Location, Long> {

	Long count(LocationFilter filter);

    List<Location> find(LocationFilter filter);
}
