package by.grodno.ss.rentacar.dataaccess.impl;

import org.springframework.stereotype.Repository;

import by.grodno.ss.rentacar.dataaccess.OrderHistoryDao;
import by.grodno.ss.rentacar.datamodel.OrderHistory;

@Repository
public class OrderHistoryDaoImpl extends AbstractDaoImpl<OrderHistory, Long> implements OrderHistoryDao {

	protected OrderHistoryDaoImpl() {
		super(OrderHistory.class);
	}

}
