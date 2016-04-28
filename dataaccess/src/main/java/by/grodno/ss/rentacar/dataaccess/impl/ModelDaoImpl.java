package by.grodno.ss.rentacar.dataaccess.impl;

import org.springframework.stereotype.Repository;

import by.grodno.ss.rentacar.dataaccess.ModelDao;
import by.grodno.ss.rentacar.datamodel.Model;

@Repository
public class ModelDaoImpl extends AbstractDaoImpl<Model, Long> implements ModelDao {

	protected ModelDaoImpl() {
		super(Model.class);
	}
}
