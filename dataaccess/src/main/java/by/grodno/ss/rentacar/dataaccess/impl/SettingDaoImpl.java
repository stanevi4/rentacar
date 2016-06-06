package by.grodno.ss.rentacar.dataaccess.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.springframework.stereotype.Repository;

import by.grodno.ss.rentacar.dataaccess.SettingDao;
import by.grodno.ss.rentacar.datamodel.Setting;

@Repository
public class SettingDaoImpl extends AbstractDaoImpl<Setting, Long> implements SettingDao {

	protected SettingDaoImpl() {
		super(Setting.class);
	}

	@Override
	public Setting get() {
		EntityManager em = getEntityManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Setting> cq = cb.createQuery(Setting.class);
		Root<Setting> from = cq.from(Setting.class);
		cq.select(from);
		TypedQuery<Setting> q = em.createQuery(cq);
		q.setFirstResult(0);
        q.setMaxResults(1);
        List<Setting> allitems = q.getResultList();
        if (allitems.isEmpty()) {
        	return new Setting();
        }else{
            Setting setting = allitems.get(0);
            return setting;
        }
	}
}
