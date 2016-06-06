package by.grodno.ss.rentacar.service.impl;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import by.grodno.ss.rentacar.dataaccess.SettingDao;
import by.grodno.ss.rentacar.datamodel.Setting;
import by.grodno.ss.rentacar.service.SettingService;

@Service
public class SettingServiceImpl implements SettingService {
	private static Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);
	
	@Inject
	private SettingDao settingDao;
	
	@Override
	public Setting get() {
		return settingDao.get();
	}

	@Override
	public void saveOrUpdate(Setting setting) {
		if (setting.getId() == null) {
            settingDao.insert(setting);
            LOGGER.info("Settings added: {}", setting.toString());
        } else {
            settingDao.update(setting);
            LOGGER.info("Settings updated: {}", setting.toString());
        }
	}

}
