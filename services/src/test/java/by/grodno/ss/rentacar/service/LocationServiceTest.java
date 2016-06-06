package by.grodno.ss.rentacar.service;

import java.lang.reflect.Field;
import java.util.ArrayList;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import by.grodno.ss.rentacar.dataaccess.LocationDao;
import by.grodno.ss.rentacar.dataaccess.filters.LocationFilter;
import by.grodno.ss.rentacar.dataaccess.impl.AbstractDaoImpl;
import by.grodno.ss.rentacar.datamodel.Location;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:service-context-test.xml" })
public class LocationServiceTest {

	@Inject
	private LocationService locationService;

	@Inject
	LocationDao locationDao;

	@Test
	public void test() {
		Assert.assertNotNull(locationService);
	}

	@Test
	public void testEntityManagerInitialization()
			throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		Field f = AbstractDaoImpl.class.getDeclaredField("entityManager");
		f.setAccessible(true);
		EntityManager em = (EntityManager) f.get(locationDao);

		Assert.assertNotNull(em);
	}

	@Test
	public void testLocation() {
		Location location = new Location();

		location.setName("Grodno office1");
		location.setLat(45.55);
		location.setLng(66.44);

		locationService.saveOrUpdate(location);
		Assert.assertNotNull(locationDao.get(location.getId()));
		
		Long count = locationService.count(new LocationFilter());
		Assert.assertNotNull(count);
		
		ArrayList<Location> list = new ArrayList<Location>();
		list = (ArrayList<Location>) locationService.find(new LocationFilter());
		Assert.assertNotNull(list.size());
		
		locationService.delete(location);
		Assert.assertNull(locationDao.get(location.getId()));
	}

}
