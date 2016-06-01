package by.grodno.ss.rentacar.dataaccess;

import java.util.List;

import by.grodno.ss.rentacar.dataaccess.filters.UserFilter;
import by.grodno.ss.rentacar.datamodel.UserProfile;

public interface UserProfileDao extends AbstractDao<UserProfile, Long> {

	List<UserProfile> find(UserFilter filter);
	
	long count(UserFilter filter);
}
