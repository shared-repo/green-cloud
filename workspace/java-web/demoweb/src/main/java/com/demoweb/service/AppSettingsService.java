package com.demoweb.service;


import com.demoweb.dao.AppSettingsDao;
import com.demoweb.dto.AppSetting;

public class AppSettingsService {
	
	AppSettingsDao dao = new AppSettingsDao();
	
	public AppSetting findAppSettingBySettingName(String settingName) {
		AppSetting appSetting = dao.selectAppSettingBySettingName(settingName);
		return appSetting;
	}
	
	public void modifyAppSetting(AppSetting appSetting) {
		dao.updateAppSetting(appSetting);
	}

}






