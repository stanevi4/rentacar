package by.grodno.ss.rentacar.dataaccess;

import java.util.List;

import by.grodno.ss.rentacar.dataaccess.filters.OrderFilter;
import by.grodno.ss.rentacar.datamodel.Order;

public interface OrderDao extends AbstractDao<Order, Long> {
	
	Long count(OrderFilter filter);

    List<Order> find(OrderFilter filter);
}
