package by.grodno.ss.rentacar.service;
import javax.transaction.Transactional;
import by.grodno.ss.rentacar.datamodel.Setting;

public interface SettingService {

	Setting get();
	
	@Transactional
	void saveOrUpdate(Setting setting);
}
