package by.grodno.ss.rentacar.service;

import java.util.List;

import javax.transaction.Transactional;

import by.grodno.ss.rentacar.dataaccess.filters.OrderFilter;
import by.grodno.ss.rentacar.datamodel.Order;

public interface OrderService {

	Long count(OrderFilter filter);

    List<Order> find(OrderFilter filter);

    @Transactional
    void saveOrUpdate(Order order);

    @Transactional
    void delete(Order order);
}
