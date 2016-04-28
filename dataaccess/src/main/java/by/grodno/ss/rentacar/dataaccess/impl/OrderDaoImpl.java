package by.grodno.ss.rentacar.dataaccess.impl;

import org.springframework.stereotype.Repository;

import by.grodno.ss.rentacar.dataaccess.OrderDao;
import by.grodno.ss.rentacar.datamodel.Order;

@Repository
public class OrderDaoImpl extends AbstractDaoImpl<Order, Long> implements OrderDao {

	protected OrderDaoImpl() {
		super(Order.class);
	}
}
