package by.grodno.ss.rentacar.service;

import javax.inject.Inject;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import by.grodno.ss.rentacar.datamodel.Model;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:service-context-test.xml" })
public class ModelServiceTest {
	
	@Inject
	private ModelService modelService;
	
	@Test
	public void testModel(){
		
		Model model = new Model();
		model.setModelName("Space Star");
		modelService.register(model);
		Model registeredModel = modelService.get(model.getId());
		Assert.assertNotNull(registeredModel);
		
		String updatedModelName = "Space Runner";
		registeredModel.setModelName(updatedModelName);;
		modelService.update(registeredModel);
		Assert.assertEquals(updatedModelName, modelService.get(registeredModel.getId()).getModelName());
		modelService.delete(registeredModel.getId());
		Assert.assertNull(modelService.get(registeredModel.getId()));
	}
}
