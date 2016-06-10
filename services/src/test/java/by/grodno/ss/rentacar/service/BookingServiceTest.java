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
import by.grodno.ss.rentacar.datamodel.OrderStatus;
import by.grodno.ss.rentacar.datamodel.TransmissionType;
import by.grodno.ss.rentacar.datamodel.Type;
import by.grodno.ss.rentacar.datamodel.UserCredentials;
import by.grodno.ss.rentacar.datamodel.UserProfile;
import by.grodno.ss.rentacar.datamodel.UserRole;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:service-context-test.xml" })
public class BookingServiceTest {

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

	private Location location = new Location();
	private UserProfile profile = new UserProfile();
	private UserCredentials userCredentials = new UserCredentials();
	private Type type = new Type();
	private Car car = new Car();
	private Booking booking = new Booking();

	public BookingServiceTest() {
		super();
		createObjects();
	}

	@Test
	public void testServicesInitialization() {
		Assert.assertNotNull(bookingService);
		Assert.assertNotNull(locationService);
		Assert.assertNotNull(userService);
		Assert.assertNotNull(typeService);
		Assert.assertNotNull(carService);		
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
	public void testLocation(){
		
		locationService.saveOrUpdate(location);
		Assert.assertNotNull(locationDao.get(location.getId()));
		
		locationService.delete(location);
		Assert.assertNull(locationDao.get(location.getId()));
		
		ArrayList<Location> list = new ArrayList<Location>();
		list = (ArrayList<Location>) locationService.find(new LocationFilter());
		Assert.assertNotNull(list.size());
	}
	
	@Test
	public void testUser(){
		
		userService.register(profile, userCredentials);
		Assert.assertNotNull(userService.getProfile(profile.getId()));
		Assert.assertNotNull(userService.getCredentials(userCredentials.getId()));
		
		userService.delete(userCredentials.getId());
		Assert.assertNull(userService.getProfile(profile.getId()));
	}
	
	@Test
	public void testType(){
		
		typeService.saveOrUpdate(type);
		Assert.assertNotNull(typeDao.get(type.getId()));
		
		typeService.delete(type);
		Assert.assertNull(typeDao.get(type.getId()));		
	}
	
	@Test
	public void testCar(){
		typeService.saveOrUpdate(type);
		locationService.saveOrUpdate(location);
		
		carService.saveOrUpdate(car);
		Assert.assertNotNull(carDao.get(car.getId()));
		
		carService.delete(car);
		Assert.assertNull(carDao.get(car.getId()));		
	}
	
	@Test
	public void testBooking(){
		typeService.saveOrUpdate(type);
		locationService.saveOrUpdate(location);
		carService.saveOrUpdate(car);
		userService.register(profile, userCredentials);
		
		
		bookingService.saveOrUpdate(booking);
		Assert.assertNotNull(bookingDao.get(booking.getId()));
		
		Long i = bookingService.count(new BookingFilter());
		Assert.assertNotNull(i);
		
		ArrayList<Booking> listbooking = new ArrayList<Booking>();
		listbooking = (ArrayList<Booking>) bookingService.find(new BookingFilter());
		Assert.assertNotNull(listbooking.size());
		
		bookingService.delete(booking);
		Assert.assertNull(bookingDao.get(booking.getId()));
		
		carService.delete(car);
		typeService.delete(type);
		locationService.delete(location);
		userService.delete(userCredentials.getId());				
	}
	
	public void createObjects() {

		location.setName("Grodno office1");
		location.setLat(45.55);
		location.setLng(66.44);

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
		

		type.setName("Premium");
		type.setNumBags(4);
		type.setNumDoors(5);
		type.setNumPassengers(5);
		type.setPricePerHour(new BigDecimal("5.5"));
		type.setTransmissionType(TransmissionType.automatic);
		

		car.setName("BMW");
		car.setLocation(location);
		car.setType(type);
		car.setCarStatus(CarStatus.availible);		
		
		booking.setClient(profile);
		booking.setCreated(new Date());
		booking.setLocationFrom(location);
		booking.setLocationTo(location);
		booking.setDateFrom(new Date());
		booking.setDateTo(new Date());
		booking.setOrderStatus(OrderStatus.confirmed);
		booking.setSumm(new BigDecimal("15.55"));
		booking.setCar(car);		
	}
}
