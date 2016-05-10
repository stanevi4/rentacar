package by.grodno.ss.rentacar.service;

import javax.inject.Inject;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import by.grodno.ss.rentacar.datamodel.Brand;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:service-context-test.xml" })
public class BrandServiceTest {
	
	@Inject
	private BrandService brandService;
	
	@Test
	public void testBrand(){
		
		Brand brand = new Brand();
		brand.setBrandName("Mitsubishi");
		brandService.register(brand);
		Brand brandMitsubishi = brandService.get(brand.getId());
		Assert.assertNotNull(brandMitsubishi);
		
		String updatedBrandName = "Mazda";
		brandMitsubishi.setBrandName(updatedBrandName);
		brandService.update(brandMitsubishi);
		Assert.assertEquals(updatedBrandName, brandService.get(brandMitsubishi.getId()).getBrandName());
		
		brandService.delete(brandMitsubishi.getId());
        Assert.assertNull(brandService.get(brandMitsubishi.getId()));
	}
}
