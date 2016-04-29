package by.grodno.ss.rentacar.service.impl;

import java.util.Date;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import by.grodno.ss.rentacar.dataaccess.UserCredentialsDao;
import by.grodno.ss.rentacar.dataaccess.UserProfileDao;
import by.grodno.ss.rentacar.datamodel.UserCredentials;
import by.grodno.ss.rentacar.datamodel.UserProfile;
import by.grodno.ss.rentacar.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	private static Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);
	
	@Inject
	private UserProfileDao userProfileDao;

	@Inject
	private UserCredentialsDao userCredentialsDao;

	@Override
	public void register(UserProfile profile, UserCredentials userCredentials) {
		
		userCredentialsDao.insert(userCredentials);
		profile.setUserCredentials(userCredentials);
		
		profile.setCreated(new Date());
		userProfileDao.insert(profile);
		
		LOGGER.info("User regirstred: {}", userCredentials.getEmail());
	}

	@Override
	public UserProfile getProfile(Long id) {
		return userProfileDao.get(id);
	}

	@Override
	public UserCredentials getCredentials(Long id) {
		return userCredentialsDao.get(id);
	}

	@Override
	public void update(UserProfile profile) {
		userProfileDao.update(profile);
	}

	@Override
	public void delete(Long id) {
		LOGGER.info("User deleted: {}", userCredentialsDao.get(id).getEmail());
		userProfileDao.delete(id);
        userCredentialsDao.delete(id);
	}

}
