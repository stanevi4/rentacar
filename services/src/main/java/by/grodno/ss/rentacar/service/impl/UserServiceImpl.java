package by.grodno.ss.rentacar.service.impl;

import java.util.Date;

import javax.inject.Inject;
import org.springframework.stereotype.Service;
import by.grodno.ss.rentacar.dataaccess.UserCredentialsDao;
import by.grodno.ss.rentacar.dataaccess.UserProfileDao;
import by.grodno.ss.rentacar.datamodel.UserCredentials;
import by.grodno.ss.rentacar.datamodel.UserProfile;
import by.grodno.ss.rentacar.service.UserService;

@Service
public class UserServiceImpl implements UserService {

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
		
		//LOGGER.info("User regirstred: {}", userCredentials);
	}

	@Override
	public UserProfile getProfile(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserCredentials getCredentials(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(UserProfile profile) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		
	}

}
