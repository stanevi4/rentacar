package by.grodno.ss.rentacar.dataaccess.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
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
		
		handleFilterParameters(filter, cb, cq, from);

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
	
	private void handleFilterParameters(BookingFilter filter, CriteriaBuilder cb, CriteriaQuery<?> cq, Root<Booking> from) {

		Predicate locationFromEqualCondition=null;
		Predicate locationToEqualCondition=null;
		Predicate clientEqualCondition=null;
		Predicate statusEqualCondition=null;
		
		Predicate cFromEqualCondition=null;
		Predicate cToEqualCondition=null;
		
		List<Predicate> predicates = new ArrayList<>();
		
		if (filter.getLocationFrom() != null){
			locationFromEqualCondition = cb.equal(from.get(Booking_.locationFrom), filter.getLocationFrom());
			predicates.add(locationFromEqualCondition);
		}
		if (filter.getLocationTo() != null){
			locationToEqualCondition = cb.equal(from.get(Booking_.locationTo), filter.getLocationTo());
			predicates.add(locationToEqualCondition);
		}
		if (filter.getClient() != null){
			clientEqualCondition = cb.equal(from.get(Booking_.client), filter.getClient());
			predicates.add(clientEqualCondition);
		}
		if (filter.getOrderStatus() != null){
			statusEqualCondition = cb.equal(from.get(Booking_.orderStatus), filter.getOrderStatus());
			predicates.add(statusEqualCondition);
		}
		if (filter.getCreatedFrom() != null){
			cFromEqualCondition = cb.greaterThanOrEqualTo(from.get(Booking_.created), filter.getCreatedFrom());
			predicates.add(cFromEqualCondition);
		}
		if (filter.getCreatedTo() != null){
			cToEqualCondition = cb.lessThanOrEqualTo(from.get(Booking_.created), filter.getCreatedTo());
			predicates.add(cToEqualCondition);
		}
		Predicate[] p = predicates.toArray(new Predicate[predicates.size()]);
		cq.where(cb.and(p));
	}

}
