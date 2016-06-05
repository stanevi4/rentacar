package by.grodno.ss.rentacar.dataaccess.impl;

import org.springframework.stereotype.Repository;

import by.grodno.ss.rentacar.dataaccess.TypeDao;
import by.grodno.ss.rentacar.datamodel.Type;

@Repository
public class TypeDaoImpl extends AbstractDaoImpl<Type, Long> implements TypeDao{

	protected TypeDaoImpl() {
		super(Type.class);
	}
}
