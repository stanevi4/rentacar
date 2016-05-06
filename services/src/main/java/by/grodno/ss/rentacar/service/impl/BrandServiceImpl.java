package by.grodno.ss.rentacar.service.impl;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import by.grodno.ss.rentacar.dataaccess.BrandDao;
import by.grodno.ss.rentacar.datamodel.Brand;
import by.grodno.ss.rentacar.service.BrandService;

@Service
public class BrandServiceImpl implements BrandService {
	
	private static Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);
	
	@Inject
	private BrandDao brandDao;
	
	@Override
	public Brand get(Long id) {
		return brandDao.get(id);
	}

	@Override
	public void update(Brand brand) {
		brandDao.update(brand);
		LOGGER.info("Brand updated: {}", brand.getBrandName());
	}

	@Override
	public void register(Brand brand) {
		brandDao.insert(brand);
		LOGGER.info("Brand registered: {}", brand.getBrandName());
	}

	@Override
	public void delete(Long id) {
		LOGGER.info("Brand deleted: {}", brandDao.get(id).getBrandName());
		brandDao.delete(id);
	}

}
