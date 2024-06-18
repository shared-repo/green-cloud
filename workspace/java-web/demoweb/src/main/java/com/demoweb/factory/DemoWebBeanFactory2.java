package com.demoweb.factory;

import java.io.FileInputStream;
import java.util.Properties;

import com.demoweb.dao.AppSettingsDao;
import com.demoweb.dao.MySqlAppSettingsDao;
import com.demoweb.dao.OracleAppSettingsDao;

public class DemoWebBeanFactory2 {
	
	Properties mappings;
	public DemoWebBeanFactory2() {
		mappings = new Properties();
		try {
			String path = "D:\\instructor-och\\green-cloud\\workspace\\java-web\\demoweb\\src\\main\\webapp\\WEB-INF\\config\\bean-config.properties";
			FileInputStream fis = new FileInputStream(path);
			mappings.load(fis);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public AppSettingsDao getSettingsDao() {
		
		AppSettingsDao settingsDao = null;
		try {
			// reflection을 사용해서 인스턴스 만들기
			String className = mappings.getProperty("app.settings.dao"); // 클래스 이름 읽기 (패키지명 포함)
			Class claz = Class.forName(className); // 클래스 이름으로 클래스 정보 객체 생성
			settingsDao = (AppSettingsDao)claz.getDeclaredConstructor().newInstance(); // 클래스 정보 객체를 사용해서 인스턴스 만들기 (new X)
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		return settingsDao;
		
	}

}
