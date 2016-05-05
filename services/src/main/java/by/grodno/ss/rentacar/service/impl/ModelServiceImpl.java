package by.grodno.ss.rentacar.service.impl;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import by.grodno.ss.rentacar.dataaccess.BrandDao;
import by.grodno.ss.rentacar.dataaccess.ModelDao;
import by.grodno.ss.rentacar.datamodel.Brand;
import by.grodno.ss.rentacar.datamodel.Model;
import by.grodno.ss.rentacar.service.ModelService;

@Service
public class ModelServiceImpl implements ModelService {
	
	private static Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);
	
	@Inject
	private ModelDao modelDao;
	
	@Override
	public Model get(Long id) {
		return modelDao.get(id);
	}

	@Override
	public void update(Model model) {
		modelDao.update(model);
		
	}

	@Override
	public void register(Model model) {
		modelDao.insert(model);
		
	}

	@Override
	public void delete(Long id) {
		modelDao.delete(id);
		
	}

}
