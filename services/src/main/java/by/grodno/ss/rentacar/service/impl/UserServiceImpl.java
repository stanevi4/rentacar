package by.grodno.ss.rentacar.service.impl;

import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import javax.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import by.grodno.ss.rentacar.dataaccess.UserCredentialsDao;
import by.grodno.ss.rentacar.dataaccess.UserProfileDao;
import by.grodno.ss.rentacar.dataaccess.filters.UserFilter;
import by.grodno.ss.rentacar.datamodel.UserCredentials;
import by.grodno.ss.rentacar.datamodel.UserProfile;
import by.grodno.ss.rentacar.datamodel.UserRole;
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
		
		userCredentials.setRole(UserRole.CLIENT);
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
		String flName = String.format("id=%1$d %2$d %3$d", profile.getId(), profile.getFirstName(), profile.getLastName());
		LOGGER.info("User profile updated: {}", flName);
		userProfileDao.update(profile);
	}

	@Override
	public void delete(Long id) {
		LOGGER.info("User deleted: {}", userCredentialsDao.get(id).getEmail());
		userProfileDao.delete(id);
        userCredentialsDao.delete(id);
	}

	@Override
	public List<UserProfile> find(UserFilter filter) {
		return userProfileDao.find(filter);
	}

	@Override
	public List<UserProfile> getAll() {
		return userProfileDao.getAll();
	}

	@Override
	public long count(UserFilter filter) {
		return userProfileDao.count(filter);
	}

	@Override
	public UserCredentials getByNameAndPassword(String email, String password) {
		return userCredentialsDao.find(email, password);
	}

	@Override
	public Collection<? extends String> resolveRoles(Long id) {
		UserCredentials userCredentials = userCredentialsDao.get(id);
        return Collections.singletonList(userCredentials.getRole().name());
	}

	@Override
	public void update(UserCredentials credentials) {
		LOGGER.info("User credential updated: {}", credentials.getEmail());
		userCredentialsDao.update(credentials);		
	}

}
