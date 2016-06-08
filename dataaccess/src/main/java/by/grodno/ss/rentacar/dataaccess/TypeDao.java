package by.grodno.ss.rentacar.dataaccess;

import java.util.List;

import by.grodno.ss.rentacar.dataaccess.filters.TypeFilter;
import by.grodno.ss.rentacar.datamodel.Type;

public interface TypeDao extends AbstractDao<Type, Long> {

	Long count(TypeFilter filter);

    List<Type> find(TypeFilter filter);
}
