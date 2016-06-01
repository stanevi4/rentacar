package by.grodno.ss.rentacar.dataaccess.impl;

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

import by.grodno.ss.rentacar.dataaccess.UserProfileDao;
import by.grodno.ss.rentacar.dataaccess.filters.UserFilter;
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

		// указывает что селектать SELECT * , from - это таблица,
		// from.get(...) это колонка
		cq.select(from);

		handleFilterParameters(filter, cb, cq, from);

		// set fetching
		if (filter.isFetchCredentials()) {
			from.fetch(UserProfile_.userCredentials, JoinType.LEFT);
		}

		// set sort params
		if (filter.getSortProperty() != null) {
			Path<Object> expression;
			if (UserCredentials_.email.equals(filter.getSortProperty())) {
				expression = from.get(UserProfile_.userCredentials).get(filter.getSortProperty());
			} else {
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

		boolean firstName = (filter.getFirstName() != null);
		boolean lastName = (filter.getLastName() != null);
		boolean email = (filter.getEmail() != null);
		boolean startDateCreated = (filter.getStartDateCreated() != null);
		boolean endDateCreated = (filter.getEndDateCreated() != null);
		boolean filt = (firstName || lastName || email || startDateCreated || endDateCreated);

		if (filt) {
			Predicate emailEqualCondition = cb.equal(from.get(UserProfile_.userCredentials).get(UserCredentials_.email),
					filter.getEmail());
			Predicate fNameEqualCondition = cb.equal(from.get(UserProfile_.firstName), filter.getFirstName());
			Predicate lNameEqualCondition = cb.equal(from.get(UserProfile_.lastName), filter.getLastName());
			Predicate startCreatedEqualCondition = cb.greaterThanOrEqualTo(from.get(UserProfile_.created),
					filter.getStartDateCreated());
			Predicate endCreatedEqualCondition = cb.lessThanOrEqualTo(from.get(UserProfile_.created),
					filter.getEndDateCreated());
			
			cq.where(cb.or(fNameEqualCondition, lNameEqualCondition, emailEqualCondition, startCreatedEqualCondition,
					endCreatedEqualCondition));
		}
	}

}
