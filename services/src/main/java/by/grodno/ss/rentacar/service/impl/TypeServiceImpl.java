package by.grodno.ss.rentacar.service.impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import by.grodno.ss.rentacar.dataaccess.TypeDao;
import by.grodno.ss.rentacar.dataaccess.filters.TypeFilter;
import by.grodno.ss.rentacar.datamodel.Type;
import by.grodno.ss.rentacar.service.TypeService;

@Service
public class TypeServiceImpl implements TypeService {

	@Inject
	private TypeDao typeDao;

	@Override
	public Long count(TypeFilter filter) {
		return typeDao.count(filter);
	}

	@Override
	public List<Type> find(TypeFilter filter) {
		return typeDao.find(filter);
	}

	@Override
	public void saveOrUpdate(Type type) {
		if (type.getId() == null) {
			typeDao.insert(type);
		} else {
			typeDao.update(type);
		}

	}

	@Override
	public void delete(Type type) {
		typeDao.delete(type.getId());
	}

}
