package com.demoweb.factory;

import com.demoweb.dao.AppSettingsDao;
import com.demoweb.dao.MySqlAppSettingsDao;
import com.demoweb.dao.OracleAppSettingsDao;

public class DemoWebBeanFactory {
	
	public static AppSettingsDao getSettingsDao(String dsn) {
		
		AppSettingsDao settingsDao = null;
		switch (dsn) {
		case "mysql" : 
			settingsDao = new MySqlAppSettingsDao();
			break;
		case "oracle" : 
			settingsDao = new OracleAppSettingsDao();
			break;
		}
		
		return settingsDao;
		
	}

}
