package by.grodno.ss.rentacar.dataaccess.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.jpa.criteria.OrderImpl;
import org.springframework.stereotype.Repository;

import by.grodno.ss.rentacar.dataaccess.ReasonDao;
import by.grodno.ss.rentacar.dataaccess.filters.ReasonFilter;
import by.grodno.ss.rentacar.datamodel.Reason;

@Repository
public class ReasonDaoImpl extends AbstractDaoImpl<Reason, Long> implements ReasonDao {

	protected ReasonDaoImpl() {
		super(Reason.class);
	}

	@Override
	public Long count(ReasonFilter filter) {
		EntityManager em = getEntityManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Long> cq = cb.createQuery(Long.class);
		Root<Reason> from = cq.from(Reason.class);
		cq.select(cb.count(from));
		TypedQuery<Long> q = em.createQuery(cq);
		return q.getSingleResult();
	}

	@Override
	public List<Reason> find(ReasonFilter filter) {
		EntityManager em = getEntityManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Reason> cq = cb.createQuery(Reason.class);
		Root<Reason> from = cq.from(Reason.class);
		cq.select(from);

		if (filter.getSortProperty() != null) {
			cq.orderBy(new OrderImpl(from.get(filter.getSortProperty()), filter.isSortOrder()));
		}

		TypedQuery<Reason> q = em.createQuery(cq);
		setPaging(filter, q);
		return q.getResultList();
	}

}
