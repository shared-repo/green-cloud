package com.demoweb.service;

import com.demoweb.dao.AppSettingsDao;
import com.demoweb.dao.MySqlAppSettingsDao;
import com.demoweb.dao.OracleAppSettingsDao;
import com.demoweb.dto.AppSetting;

public class AppSettingsService {
	
	// private MySqlAppSettingsDao dao = new MySqlAppSettingsDao();
	// private OracleAppSettingsDao dao = new OracleAppSettingsDao();
	
	// private AppSettingsDao dao = new MySqlAppSettingsDao();
	private AppSettingsDao dao = new OracleAppSettingsDao();
	
	public AppSetting findAppSettingBySettingName(String settingName) {
		AppSetting appSetting = dao.selectAppSettingBySettingName(settingName);
		return appSetting;
	}
	
	public void modifyAppSetting(AppSetting appSetting) {
		dao.updateAppSetting(appSetting);
	}

}
