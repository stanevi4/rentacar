package by.grodno.ss.rentacar.service.impl;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import by.grodno.ss.rentacar.dataaccess.BrandDao;
import by.grodno.ss.rentacar.dataaccess.ModelDao;
import by.grodno.ss.rentacar.datamodel.Model;
import by.grodno.ss.rentacar.service.ModelService;

@Service
public class ModelServiceImpl implements ModelService {
	
	private static Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);
	
	@Inject
	private ModelDao modelDao;
	
	@Inject
	private BrandDao brandDao;
	
	@Override
	public Model get(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Model model) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void set(Model model) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		
	}

}
