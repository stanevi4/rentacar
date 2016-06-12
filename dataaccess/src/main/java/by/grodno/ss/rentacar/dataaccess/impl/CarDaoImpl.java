package by.grodno.ss.rentacar.dataaccess.impl;

import java.util.ArrayList;
import java.util.List;

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

		boolean location = (filter.getLocation() != null);
		boolean locationFrom = (filter.getLocationFrom() != null);
		boolean type = (filter.getType() != null);
		boolean carStatus = (filter.getCarStatus() != null);
		Predicate locationFromEqualCondition=null;
		Predicate typeEqualCondition=null;
		List<Predicate> predicates = new ArrayList<>();
		
		if (filter.getLocationFrom() != null){
			locationFromEqualCondition = cb.equal(from.get(Car_.location), filter.getLocationFrom());
			predicates.add(locationFromEqualCondition);
		}
		if (filter.getType() != null){
			typeEqualCondition = cb.equal(from.get(Car_.type), filter.getType());
			predicates.add(typeEqualCondition);
		}
		
		Predicate[] p = predicates.toArray(new Predicate[predicates.size()]);
		cq.where(cb.and(p));
	}
}
