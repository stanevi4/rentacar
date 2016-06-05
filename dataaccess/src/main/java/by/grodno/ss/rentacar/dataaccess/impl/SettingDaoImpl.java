package by.grodno.ss.rentacar.dataaccess.impl;

import org.springframework.stereotype.Repository;

import by.grodno.ss.rentacar.dataaccess.SettingDao;
import by.grodno.ss.rentacar.datamodel.Setting;

@Repository
public class SettingDaoImpl extends AbstractDaoImpl<Setting, Long> implements SettingDao {

	protected SettingDaoImpl() {
		super(Setting.class);
	}
}
