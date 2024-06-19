package com.demoweb.factory;

import java.io.InputStream;
import java.util.Properties;

import com.demoweb.controller.Controller;
import com.demoweb.dao.AppSettingsDao;
import com.demoweb.dao.BoardDao;
import com.demoweb.dao.MemberDao;

public class DemoWebControllerFactory {
	
	private Properties mappings;
	public DemoWebControllerFactory() {
		mappings = new Properties();
		try {			
			// 클래스패스 경로에서 파일 읽기
			InputStream is = this.getClass()
								 .getClassLoader()
					 			 .getResourceAsStream("com/demoweb/config/controller-config.properties");
			mappings.load(is);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public Controller getInstance(String action) {
		Controller controller = null;
		try {
			// reflection을 사용해서 인스턴스 만들기
			String className = mappings.getProperty(action); // 클래스 이름 읽기 (패키지명 포함)
			Class claz = Class.forName(className); // 클래스 이름으로 클래스 정보 객체 생성
			controller = (Controller)claz.getDeclaredConstructor().newInstance(); // 클래스 정보 객체를 사용해서 인스턴스 만들기 (new X)
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return controller;
	}
}
