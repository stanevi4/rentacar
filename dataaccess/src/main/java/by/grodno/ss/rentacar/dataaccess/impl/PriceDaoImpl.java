package by.grodno.ss.rentacar.dataaccess.impl;

import org.springframework.stereotype.Repository;

import by.grodno.ss.rentacar.dataaccess.PriceDao;
import by.grodno.ss.rentacar.datamodel.Price;

@Repository
public class PriceDaoImpl extends AbstractDaoImpl<Price, Long> implements PriceDao {

	protected PriceDaoImpl() {
		super(Price.class);
	}

}
