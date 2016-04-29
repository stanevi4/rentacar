package by.grodno.ss.rentacar.service;

import javax.transaction.Transactional;
import by.grodno.ss.rentacar.datamodel.Brand;


public interface BrandService {

	Brand get(Long id);

	@Transactional
	void update(Brand brand);
	
	@Transactional
	void set(Brand brand);
	
	@Transactional
	void delete(Long id);
}
