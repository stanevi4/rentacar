package by.grodno.ss.rentacar.dataaccess.impl;

import org.springframework.stereotype.Repository;

import by.grodno.ss.rentacar.dataaccess.ReasonDao;
import by.grodno.ss.rentacar.datamodel.Reason;

@Repository
public class ReasonDaoImpl extends AbstractDaoImpl<Reason, Long> implements ReasonDao {

	protected ReasonDaoImpl() {
		super(Reason.class);
	}

}
