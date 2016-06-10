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

import by.grodno.ss.rentacar.dataaccess.BookingDao;
import by.grodno.ss.rentacar.dataaccess.filters.BookingFilter;
import by.grodno.ss.rentacar.datamodel.Booking;
import by.grodno.ss.rentacar.datamodel.Booking_;

@Repository
public class BookingDaoImpl extends AbstractDaoImpl<Booking, Long> implements BookingDao {

	protected BookingDaoImpl() {
		super(Booking.class);
	}

	@Override
	public Long count(BookingFilter filter) {
		EntityManager em = getEntityManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Long> cq = cb.createQuery(Long.class);
		Root<Booking> from = cq.from(Booking.class);
		cq.select(cb.count(from));
		TypedQuery<Long> q = em.createQuery(cq);
		return q.getSingleResult();
	}

	@Override
	public List<Booking> find(BookingFilter filter) {
		EntityManager em = getEntityManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Booking> cq = cb.createQuery(Booking.class);
		Root<Booking> from = cq.from(Booking.class);
		cq.select(from);

		if (filter.getSortProperty() != null) {
			cq.orderBy(new OrderImpl(from.get(filter.getSortProperty()), filter.isSortOrder()));
		}

		if (filter.isFetchClient()) {
			from.fetch(Booking_.client, JoinType.LEFT);
		}

		if (filter.isFetchCar()) {
			from.fetch(Booking_.car, JoinType.LEFT);
		}

		if (filter.isFetchLocations()) {
			from.fetch(Booking_.locationFrom, JoinType.LEFT);
			from.fetch(Booking_.locationTo, JoinType.LEFT);
		}

		if (filter.isFetchReason()) {
			from.fetch(Booking_.reason, JoinType.LEFT);
		}

		TypedQuery<Booking> q = em.createQuery(cq);
		setPaging(filter, q);
		return q.getResultList();
	}

}
