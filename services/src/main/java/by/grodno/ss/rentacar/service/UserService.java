package by.grodno.ss.rentacar.service;

import java.util.Collection;
import java.util.List;
import javax.transaction.Transactional;

import by.grodno.ss.rentacar.dataaccess.filters.UserFilter;
import by.grodno.ss.rentacar.datamodel.UserCredentials;
import by.grodno.ss.rentacar.datamodel.UserProfile;

public interface UserService {

	@Transactional
	void register(UserProfile profile, UserCredentials userCredentials);

	UserProfile getProfile(Long id);

	UserCredentials getCredentials(Long id);

	@Transactional
	void update(UserProfile profile);

	@Transactional
	void delete(Long id);
	
	List<UserProfile> find(UserFilter filter);

    List<UserProfile> getAll();

    long count(UserFilter filter);

    UserCredentials getByNameAndPassword(String userName, String password);

    Collection<? extends String> resolveRoles(Long id);

    @Transactional
    void update(UserCredentials credentials);
    
    void setLogingLog(String email, boolean login);
}
