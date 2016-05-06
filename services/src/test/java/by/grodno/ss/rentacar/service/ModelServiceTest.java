package by.grodno.ss.rentacar.service;

import javax.inject.Inject;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import by.grodno.ss.rentacar.datamodel.Brand;
import by.grodno.ss.rentacar.datamodel.Model;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:service-context-test.xml" })
public class ModelServiceTest {

	@Inject
	private ModelService modelService;
	
	@Inject
	private BrandService brandService;
	
	@Test
	public void testModel(){
		Brand brand = new Brand();
		brand.setBrandName("Mitsubishi");
		brandService.register(brand);
		Brand registeredBrand = brandService.get(brand.getId());
		Assert.assertNotNull(registeredBrand);
		
		Model model = new Model();
		model.setBrand(brand);
		model.setModelName("Space Star");
		modelService.register(model);
		Model registeredModel = modelService.get(model.getId());
		Assert.assertNotNull(registeredModel.getBrand().getId());
	}
}
