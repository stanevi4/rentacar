package by.grodno.ss.rentacar.service;

import java.lang.reflect.Field;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import by.grodno.ss.rentacar.dataaccess.UserProfileDao;
import by.grodno.ss.rentacar.dataaccess.impl.AbstractDaoImpl;
import by.grodno.ss.rentacar.datamodel.UserCredentials;
import by.grodno.ss.rentacar.datamodel.UserProfile;
import by.grodno.ss.rentacar.datamodel.UserRole;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:service-context-test.xml" })
public class UserServiceTest {

	@Inject
    private UserService userService;

    @Inject
    private UserProfileDao userProfileDao;

    @Test
    public void test() {
        Assert.assertNotNull(userService);
    }
    
    @Test
    public void testEntityManagerInitialization() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
        Field f = AbstractDaoImpl.class.getDeclaredField("entityManager");
        f.setAccessible(true);
        EntityManager em = (EntityManager) f.get(userProfileDao);

        Assert.assertNotNull(em);
    }
    
    @Test
    public void testRegistration() {
        UserProfile profile = new UserProfile();
        UserCredentials userCredentials = new UserCredentials();

        profile.setFirstName("testFName");
        profile.setLastName("testLName");
        //profile.setPassportNumber("KH6754329");
        String datePattern = "MM.dd.yyyy";
        SimpleDateFormat format = new SimpleDateFormat(datePattern);
        Date date = null;
		try {
			date = format.parse("12.31.1990");
		} catch (ParseException e) {
			e.printStackTrace();
		}
        profile.setBirthDay(date);
        profile.setAddress("г.Гродно, ул.Космонавтов 50/55");
        profile.setPhoneNumber("+375-29-7886543");
        
        userCredentials.setEmail(System.currentTimeMillis() + "mail@test.by");
        userCredentials.setPassword("pswd");
        userCredentials.setRole(UserRole.ADMIN);
        userService.register(profile, userCredentials);
        
        Assert.assertNotNull(userService.getProfile(profile.getId()));
        Assert.assertNotNull(userService.getCredentials(userCredentials.getId()));
        
        userService.delete(userCredentials.getId());
        Assert.assertNull(userService.getProfile(profile.getId()));
        Assert.assertNull(userService.getCredentials(profile.getId()));
    }
}
