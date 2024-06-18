package com.demoweb.dao;

import com.demoweb.dto.AppSetting;

public interface AppSettingsDao {

	AppSetting selectAppSettingBySettingName(String settingName);

	void updateAppSetting(AppSetting appSetting);

}