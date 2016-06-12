package by.grodno.ss.rentacar.service.impl;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import by.grodno.ss.rentacar.dataaccess.OrderDao;
import by.grodno.ss.rentacar.dataaccess.filters.OrderFilter;
import by.grodno.ss.rentacar.datamodel.Order;
import by.grodno.ss.rentacar.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {
	
	private static Logger LOGGER = LoggerFactory.getLogger(OrderServiceImpl.class);
	
	@Inject
	private OrderDao orderDao;

	@Override
	public Long count(OrderFilter filter) {
		return orderDao.count(filter);
	}

	@Override
	public List<Order> find(OrderFilter filter) {
		return orderDao.find(filter);
	}

	@Override
	public void saveOrUpdate(Order order) {
		if (order.getId() == null) {
			orderDao.insert(order);
			//LOGGER.info("Order added: {}", order.getId());
		} else {
			orderDao.update(order);
			//LOGGER.info("Order updated: {}", order.getId());
		}			
	}

	@Override
	public void delete(Order order) {
		LOGGER.info("Order deleted: {}", order.getId());
		orderDao.delete(order.getId());		
	}
}
