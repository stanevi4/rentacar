package by.grodno.ss.rentacar.service.impl;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import by.grodno.ss.rentacar.dataaccess.SettingDao;
import by.grodno.ss.rentacar.datamodel.Setting;
import by.grodno.ss.rentacar.service.SettingService;

@Service
public class SettingServiceImpl implements SettingService {

	@Inject
	private SettingDao settingDao;
	
	@Override
	public Setting get() {
		Long id = (long) 1;
		return settingDao.get(id);
	}

	@Override
	public void saveOrUpdate(Setting setting) {
		if (setting.getId() == null) {
			setting.setId((long) 1);
            settingDao.insert(setting);
        } else {
            settingDao.update(setting);
        }
	}

}
