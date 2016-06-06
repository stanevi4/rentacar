package by.grodno.ss.rentacar.dataaccess;

import by.grodno.ss.rentacar.datamodel.Setting;

public interface SettingDao extends AbstractDao<Setting, Long> {

	Setting get();
}
