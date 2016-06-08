package by.grodno.ss.rentacar.service;

import java.util.List;

import javax.transaction.Transactional;

import by.grodno.ss.rentacar.dataaccess.filters.TypeFilter;
import by.grodno.ss.rentacar.datamodel.Type;

public interface TypeService {

	Long count(TypeFilter filter);

    List<Type> find(TypeFilter filter);

    @Transactional
    void saveOrUpdate(Type type);

    @Transactional
    void delete(Type type);
}
