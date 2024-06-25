package com.demoweb.service;

import java.io.FileInputStream;
import java.util.Properties;

import com.demoweb.dao.AppSettingsDao;
import com.demoweb.dao.MySqlAppSettingsDao;
import com.demoweb.dao.OracleAppSettingsDao;
import com.demoweb.dto.AppSetting;
import com.demoweb.factory.DemoWebBeanFactory;
import com.demoweb.factory.DemoWebBeanFactory2;

public class AppSettingsService {
	
	// 1. Class 사용 + new 사용
	// private MySqlAppSettingsDao dao = new MySqlAppSettingsDao();
	// private OracleAppSettingsDao dao = new OracleAppSettingsDao();
	
	// 2. interface 사용 + new 사용
	// private AppSettingsDao dao = new MySqlAppSettingsDao();
	// private AppSettingsDao dao = new OracleAppSettingsDao();
	
	// 3. interface 사용 + factory 사용
	// AppSettingsDao dao = DemoWebBeanFactory.getSettingsDao("mysql");
	// AppSettingsDao dao = DemoWebBeanFactory.getSettingsDao("oracle");
	
	// 4. interface 사용 + factory 사용 + 설정 파일 사용
//	AppSettingsDao dao;
//	public AppSettingsService() {
//		Properties props = new Properties();
//		try {
//			String path = "D:\\instructor-och\\green-cloud\\workspace\\java-web\\demoweb\\src\\main\\webapp\\WEB-INF\\config\\bean-config.properties";
//			FileInputStream fis = new FileInputStream(path);
//			props.load(fis);
//			String dsn = (String)props.get("app.settings.dsn");
//			dao = DemoWebBeanFactory.getSettingsDao(dsn);
//		} catch (Exception ex) {
//			ex.printStackTrace();
//		}
//	}
	
	// 5. interface 사용 + factory 사용 + 설정 파일 사용 2
	AppSettingsDao dao;
	public AppSettingsService() {		
		dao = DemoWebBeanFactory2.getSettingsDao();
	}	
	
	public AppSetting findAppSettingBySettingName(String settingName) {
		AppSetting appSetting = dao.selectAppSettingBySettingName(settingName);
		return appSetting;
	}
	
	public void modifyAppSetting(AppSetting appSetting) {
		dao.updateAppSetting(appSetting);
	}

}






