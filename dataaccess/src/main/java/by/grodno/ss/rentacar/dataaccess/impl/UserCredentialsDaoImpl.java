package by.grodno.ss.rentacar.dataaccess.impl;

import org.springframework.stereotype.Repository;

import by.grodno.ss.rentacar.dataaccess.UserCredentialsDao;
import by.grodno.ss.rentacar.datamodel.UserCredentials;

@Repository
public class UserCredentialsDaoImpl extends AbstractDaoImpl<UserCredentials, Long> implements UserCredentialsDao {

	protected UserCredentialsDaoImpl() {
		super(UserCredentials.class);
	}

}
