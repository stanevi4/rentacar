package by.grodno.ss.rentacar.dataaccess.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.jpa.criteria.OrderImpl;
import org.springframework.stereotype.Repository;

import by.grodno.ss.rentacar.dataaccess.UserProfileDao;
import by.grodno.ss.rentacar.dataaccess.filters.UserFilter;
import by.grodno.ss.rentacar.datamodel.Booking_;
import by.grodno.ss.rentacar.datamodel.UserCredentials_;
import by.grodno.ss.rentacar.datamodel.UserProfile;
import by.grodno.ss.rentacar.datamodel.UserProfile_;

@Repository
public class UserProfileDaoImpl extends AbstractDaoImpl<UserProfile, Long> implements UserProfileDao {

	protected UserProfileDaoImpl() {
		super(UserProfile.class);
	}

	@Override
	public List<UserProfile> find(UserFilter filter) {

		EntityManager em = getEntityManager();

		CriteriaBuilder cb = em.getCriteriaBuilder();

		CriteriaQuery<UserProfile> cq = cb.createQuery(UserProfile.class);

		Root<UserProfile> from = cq.from(UserProfile.class); // SELECT .. FROM
																// ...
		cq.select(from);

		handleFilterParameters(filter, cb, cq, from);

		if (filter.isFetchCredentials()) {
			from.fetch(UserProfile_.userCredentials, JoinType.LEFT);
		}

		// set sort params
		if (filter.getSortProperty() != null) {
			Path<Object> expression;
			if (UserCredentials_.email.equals(filter.getSortProperty())) {
				expression = from.get(UserProfile_.userCredentials).get(filter.getSortProperty());
			}else if (UserCredentials_.role.equals(filter.getSortProperty())) {
				expression = from.get(UserProfile_.userCredentials).get(filter.getSortProperty());
			}else {
				expression = from.get(filter.getSortProperty());
			}
			cq.orderBy(new OrderImpl(expression, filter.isSortOrder()));
		}

		TypedQuery<UserProfile> q = em.createQuery(cq);

		// set paging
		if (filter.getOffset() != null && filter.getLimit() != null) {
			q.setFirstResult(filter.getOffset());
			q.setMaxResults(filter.getLimit());
		}

		// set execute query
		List<UserProfile> allitems = q.getResultList();

		return allitems;
	}

	@Override
	public long count(UserFilter filter) {

		EntityManager em = getEntityManager();

		CriteriaBuilder cb = em.getCriteriaBuilder();

		CriteriaQuery<Long> cq = cb.createQuery(Long.class);

		Root<UserProfile> from = cq.from(UserProfile.class);

		// set selection
		cq.select(cb.count(from));

		handleFilterParameters(filter, cb, cq, from);

		TypedQuery<Long> q = em.createQuery(cq);

		// set execute query
		return q.getSingleResult();
	}

	private void handleFilterParameters(UserFilter filter, CriteriaBuilder cb, CriteriaQuery<?> cq,
			Root<UserProfile> from) {

		Predicate emailEqualCondition=null;
		Predicate roleEqualCondition=null;
		Predicate cFromEqualCondition=null;
		Predicate cToEqualCondition=null;
		
		List<Predicate> predicates = new ArrayList<>();
		
		if (filter.getEmail() != null){
			emailEqualCondition = cb.like(from.get(UserProfile_.userCredentials).get(UserCredentials_.email), "%"+filter.getEmail()+"%");
			predicates.add(emailEqualCondition);
		}
		if (filter.getRole() != null){
			roleEqualCondition = cb.equal(from.get(UserProfile_.userCredentials).get(UserCredentials_.role), filter.getRole());
			predicates.add(roleEqualCondition);
		}
		if (filter.getCreatedFrom() != null){
			cFromEqualCondition = cb.greaterThanOrEqualTo(from.get(UserProfile_.created), filter.getCreatedFrom());
			predicates.add(cFromEqualCondition);
		}
		if (filter.getCreatedTo() != null){
			cToEqualCondition = cb.lessThanOrEqualTo(from.get(UserProfile_.created), filter.getCreatedTo());
			predicates.add(cToEqualCondition);
		}
		Predicate[] p = predicates.toArray(new Predicate[predicates.size()]);
		cq.where(cb.and(p));
	}

}
