package by.grodno.ss.rentacar.dataaccess.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;

import org.hibernate.jpa.criteria.OrderImpl;
import org.springframework.stereotype.Repository;

import by.grodno.ss.rentacar.dataaccess.OrderDao;
import by.grodno.ss.rentacar.dataaccess.filters.OrderFilter;
import by.grodno.ss.rentacar.datamodel.Order;
import by.grodno.ss.rentacar.datamodel.Order_;

@Repository
public class OrderDaoImpl extends AbstractDaoImpl<Order, Long> implements OrderDao {

	protected OrderDaoImpl() {
		super(Order.class);
	}

	@Override
	public Long count(OrderFilter filter) {
		EntityManager em = getEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Order> from = cq.from(Order.class);
        cq.select(cb.count(from));
        TypedQuery<Long> q = em.createQuery(cq);
        return q.getSingleResult();
	}

	@Override
	public List<Order> find(OrderFilter filter) {
		EntityManager em = getEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Order> cq = cb.createQuery(Order.class);
        Root<Order> from = cq.from(Order.class);
        cq.select(from);

        if (filter.getSortProperty() != null) {
            cq.orderBy(new OrderImpl(from.get(filter.getSortProperty()), filter.isSortOrder()));
        }
        
        if (filter.isFetchClient()) {
		//	from.fetch(Order_.client, JoinType.LEFT);
		}
        
        if (filter.isFetchCar()) {
		//	from.fetch(Order_.car, JoinType.LEFT);
		}
        
        if (filter.isFetchLocations()) {
		//	from.fetch(Order_.locationFrom, JoinType.LEFT);
		//	from.fetch(Order_.locationTo, JoinType.LEFT);
		}
        
        if (filter.isFetchReason()) {
		//	from.fetch(Order_.reason, JoinType.LEFT);
		}        

        TypedQuery<Order> q = em.createQuery(cq);
        setPaging(filter, q);
        return q.getResultList();
	}
}
