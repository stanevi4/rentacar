package by.grodno.ss.rentacar.service;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import by.grodno.ss.rentacar.dataaccess.BookingDao;
import by.grodno.ss.rentacar.dataaccess.CarDao;
import by.grodno.ss.rentacar.dataaccess.LocationDao;
import by.grodno.ss.rentacar.dataaccess.TypeDao;
import by.grodno.ss.rentacar.dataaccess.filters.BookingFilter;
import by.grodno.ss.rentacar.dataaccess.filters.LocationFilter;
import by.grodno.ss.rentacar.dataaccess.impl.AbstractDaoImpl;
import by.grodno.ss.rentacar.datamodel.Booking;
import by.grodno.ss.rentacar.datamodel.Car;
import by.grodno.ss.rentacar.datamodel.CarStatus;
import by.grodno.ss.rentacar.datamodel.Location;
import by.grodno.ss.rentacar.datamodel.TransmissionType;
import by.grodno.ss.rentacar.datamodel.Type;
import by.grodno.ss.rentacar.datamodel.UserCredentials;
import by.grodno.ss.rentacar.datamodel.UserProfile;
import by.grodno.ss.rentacar.datamodel.UserRole;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:service-context-test.xml" })
public class OrderServiceTest {

	@Inject
	private LocationService locationService;
	@Inject
	private LocationDao locationDao;
	@Inject
	private UserService userService;
	@Inject
	private BookingDao bookingDao;
	@Inject
	private BookingService bookingService;
	@Inject
	private TypeDao typeDao;
	@Inject
	private TypeService typeService;
	@Inject
	private CarDao carDao;
	@Inject
	private CarService carService;

	private Location location1 = new Location();
	private UserProfile profile = new UserProfile();
	private UserCredentials userCredentials = new UserCredentials();
	private Type type = new Type();
	private Car car = new Car();

	@Test
	public void test() {
		Assert.assertNotNull(bookingService);
	}

	@Test
	public void testEntityManagerInitialization()
			throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		Field f = AbstractDaoImpl.class.getDeclaredField("entityManager");
		f.setAccessible(true);
		EntityManager em = (EntityManager) f.get(bookingDao);

		Assert.assertNotNull(em);
	}

	@Test
	public void createObjects() {

		location1.setName("Grodno office1");
		location1.setLat(45.55);
		location1.setLng(66.44);
		locationService.saveOrUpdate(location1);
		Assert.assertNotNull(locationDao.get(location1.getId()));

		profile.setFirstName("testFName");
		profile.setLastName("testLName");
		profile.setLicenseNumber("LIC987495");
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
		userCredentials.setRole(UserRole.CLIENT);
		userService.register(profile, userCredentials);
		Assert.assertNotNull(userService.getProfile(profile.getId()));
		Assert.assertNotNull(userService.getCredentials(userCredentials.getId()));

		type.setName("Premium");
		type.setNumBags(4);
		type.setNumDoors(5);
		type.setNumPassengers(5);
		type.setPricePerHour(new BigDecimal("5.5"));
		type.setTransmissionType(TransmissionType.automatic);
		typeService.saveOrUpdate(type);
		Assert.assertNotNull(typeDao.get(type.getId()));

		car.setName("BMW");
		car.setLocation(location1);
		car.setType(type);
		car.setCarStatus(CarStatus.availible);
		carService.saveOrUpdate(car);
		Assert.assertNotNull(carDao.get(car.getId()));

		ArrayList<Location> list = new ArrayList<Location>();
		list = (ArrayList<Location>) locationService.find(new LocationFilter());
		Assert.assertNotNull(list.size());
		
		
		Booking booking = new Booking();
		booking.setClient(profile);
		booking.setCreated(new Date());
		booking.setLocationFrom(location1);
		booking.setLocationTo(location1);
//		order.setDateFrom(new Date());
//		order.setDateTo(new Date());
//		order.setOrderStatus(OrderStatus.confirmed);
//		order.setSumm(new BigDecimal("15.55"));
		//booking.setCar(car);
		bookingService.saveOrUpdate(booking);
		Assert.assertNotNull(bookingDao.get(booking.getId()));
		
		Long i = bookingService.count(new BookingFilter());
		Assert.assertNotNull(i);
		
		ArrayList<Booking> listbooking = new ArrayList<Booking>();
		listbooking = (ArrayList<Booking>) bookingService.find(new BookingFilter());
		Assert.assertNotNull(listbooking.size());
		
		
		bookingService.delete(booking);
		carService.delete(car);
		typeService.delete(type);
		userService.delete(userCredentials.getId());
		locationService.delete(location1);
		Assert.assertNull(bookingDao.get(booking.getId()));
		Assert.assertNull(carDao.get(car.getId()));
		Assert.assertNull(typeDao.get(type.getId()));
		Assert.assertNull(userService.getProfile(profile.getId()));
		Assert.assertNull(locationDao.get(location1.getId()));
		
	}
}
