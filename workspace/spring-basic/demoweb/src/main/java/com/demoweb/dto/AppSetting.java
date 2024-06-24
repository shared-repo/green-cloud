package com.demoweb.dto;

public class AppSetting {
	
	private String settingName;
	private String settingValue;
			
	public AppSetting() {}
	public AppSetting(String settingName, String settingValue) {	
		this.settingName = settingName;
		this.settingValue = settingValue;
	}
	
	public String getSettingName() {
		return settingName;
	}
	public void setSettingName(String settingName) {
		this.settingName = settingName;
	}
	public String getSettingValue() {
		return settingValue;
	}
	public void setSettingValue(String settingValue) {
		this.settingValue = settingValue;
	}

}
