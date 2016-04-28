package by.grodno.ss.rentacar.dataaccess.impl;

import org.springframework.stereotype.Repository;

import by.grodno.ss.rentacar.dataaccess.BrandDao;
import by.grodno.ss.rentacar.datamodel.Brand;

@Repository
public class BrandDaoImpl extends AbstractDaoImpl<Brand, Long> implements BrandDao {

	protected BrandDaoImpl() {
		super(Brand.class);
	}

}
