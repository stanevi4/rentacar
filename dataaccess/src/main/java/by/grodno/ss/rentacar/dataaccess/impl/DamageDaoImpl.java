package by.grodno.ss.rentacar.dataaccess.impl;

import org.springframework.stereotype.Repository;

import by.grodno.ss.rentacar.dataaccess.DamageDao;
import by.grodno.ss.rentacar.datamodel.Damage;

@Repository
public class DamageDaoImpl extends AbstractDaoImpl<Damage, Long> implements DamageDao {
	
	protected DamageDaoImpl() {
		super(Damage.class);
	}
	
}
