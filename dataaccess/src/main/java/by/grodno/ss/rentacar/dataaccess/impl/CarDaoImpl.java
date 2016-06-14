package by.grodno.ss.rentacar.dataaccess.impl;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.hibernate.jpa.criteria.OrderImpl;
import org.springframework.stereotype.Repository;

import by.grodno.ss.rentacar.dataaccess.CarDao;
import by.grodno.ss.rentacar.dataaccess.filters.CarFilter;
import by.grodno.ss.rentacar.datamodel.Car;
import by.grodno.ss.rentacar.datamodel.Car_;
import by.grodno.ss.rentacar.datamodel.Type_;

@Repository
public class CarDaoImpl extends AbstractDaoImpl<Car, Long> implements CarDao {

	protected CarDaoImpl() {
		super(Car.class);
	}

	@Override
	public Long count(CarFilter filter) {
		EntityManager em = getEntityManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Long> cq = cb.createQuery(Long.class);
		Root<Car> from = cq.from(Car.class);
		cq.select(cb.count(from));
		TypedQuery<Long> q = em.createQuery(cq);
		return q.getSingleResult();
	}

	@Override
	public List<Car> find(CarFilter filter) {
		EntityManager em = getEntityManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Car> cq = cb.createQuery(Car.class);
		Root<Car> from = cq.from(Car.class);
		cq.select(from);

		handleFilterParameters(filter, cb, cq, from);

		if (filter.getSortProperty() != null) {
			Path<Object> expression;
			if (Type_.pricePerHour.equals(filter.getSortProperty())) {
				expression = from.get(Car_.type).get(filter.getSortProperty());
			} else {
				expression = from.get(filter.getSortProperty());
			}
			cq.orderBy(new OrderImpl(expression, filter.isSortOrder()));
		}

		if (filter.isFetchLocations()) {
			from.fetch(Car_.location, JoinType.LEFT);
		}

		if (filter.isFetchTypes()) {
			from.fetch(Car_.type, JoinType.LEFT);
		}

		TypedQuery<Car> q = em.createQuery(cq);
		setPaging(filter, q);
		return q.getResultList();
	}

	private void handleFilterParameters(CarFilter filter, CriteriaBuilder cb, CriteriaQuery<?> cq, Root<Car> from) {

		Predicate locationFromEqualCondition = null;
		Predicate typeEqualCondition = null;
		Predicate locationEqualCondition = null;
		Predicate statusEqualCondition = null;

		List<Predicate> predicates = new ArrayList<>();

		if (filter.getLocation() != null) {
			locationEqualCondition = cb.equal(from.get(Car_.location), filter.getLocation());
			predicates.add(locationEqualCondition);
		}
		if (filter.getLocationFrom() != null) {
			locationFromEqualCondition = cb.equal(from.get(Car_.location), filter.getLocationFrom());
			predicates.add(locationFromEqualCondition);
		}
		if (filter.getType() != null) {
			typeEqualCondition = cb.equal(from.get(Car_.type), filter.getType());
			predicates.add(typeEqualCondition);
		}
		if (filter.getCarStatus() != null) {
			statusEqualCondition = cb.equal(from.get(Car_.carStatus), filter.getCarStatus());
			predicates.add(statusEqualCondition);
		}

		Predicate[] p = predicates.toArray(new Predicate[predicates.size()]);
		cq.where(cb.and(p));
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Car> reserved(CarFilter filter, int timeBetweenBookings) {
		
		long timeBetweenBookingsMills = TimeUnit.HOURS.toMillis(timeBetweenBookings);
		Long timeFrom = filter.getDateFrom().getTime();
		Long timeTo   = filter.getDateTo().getTime();
		Date dateFrom = new Date(timeFrom - timeBetweenBookings);
		Date dateTo   = new Date(timeTo + timeBetweenBookingsMills);
		
		EntityManager em = getEntityManager();
		List<Car> list = em
				.createQuery( "SELECT DISTINCT c FROM Booking b LEFT JOIN b.car c " +
							  "WHERE (b.dateFrom >= :startDate AND b.dateTo <= :endDate)")
				//.setParameter("startDate", filter.getDateFrom())
				//.setParameter("endDate", filter.getDateTo()).getResultList();
				.setParameter("startDate", dateFrom)
				.setParameter("endDate", dateTo).getResultList();

		return list;
	}
}
