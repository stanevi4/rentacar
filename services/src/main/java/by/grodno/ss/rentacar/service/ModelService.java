package by.grodno.ss.rentacar.service;

import javax.transaction.Transactional;

import by.grodno.ss.rentacar.datamodel.Model;

public interface ModelService {
	
	Model get(Long id);

	@Transactional
	void update(Model model);
	
	@Transactional
	void register(Model model);
	
	@Transactional
	void delete(Long id);
}
