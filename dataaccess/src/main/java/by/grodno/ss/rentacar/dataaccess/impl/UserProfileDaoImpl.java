package by.grodno.ss.rentacar.dataaccess.impl;

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

import by.grodno.ss.rentacar.dataaccess.UserProfileDao;
import by.grodno.ss.rentacar.dataaccess.filters.UserFilter;
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

        Root<UserProfile> from = cq.from(UserProfile.class);

        // set selection
        cq.select(from);

        if (filter.getUserName() != null) {
            Predicate fNameEqualCondition = cb.equal(from.get(UserProfile_.firstName), filter.getUserName());
            Predicate lNameEqualCondition = cb.equal(from.get(UserProfile_.lastName), filter.getUserName());
            cq.where(cb.or(fNameEqualCondition, lNameEqualCondition));
        }
        // set fetching
        if (filter.isFetchCredentials()) {
            from.fetch(UserProfile_.userCredentials, JoinType.LEFT);
        }

        // set sort params
        if (filter.getSortProperty() != null) {
            cq.orderBy(new OrderImpl(from.get(filter.getSortProperty()), filter.isSortOrder()));
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

}
