package com.demoweb.factory;

import java.io.InputStream;
import java.util.Properties;

import com.demoweb.dao.AppSettingsDao;
import com.demoweb.dao.BoardDao;
import com.demoweb.dao.MemberDao;

public class DemoWebBeanFactory2 {
	
	static Properties mappings;
	static {
		mappings = new Properties();
		try {			
//			String path = "D:\\instructor-och\\green-cloud\\workspace\\java-web\\demoweb\\src\\main\\webapp\\WEB-INF\\config\\bean-config.properties";
//			FileInputStream is = new FileInputStream(path);
			
			// 클래스패스 경로에서 파일 읽기
			InputStream is = DemoWebBeanFactory2.class
					 			 				.getClassLoader()
					 			 				.getResourceAsStream("com/demoweb/config/bean-config.properties");
			mappings.load(is);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	// generic 호환성 활용한 객체 생성기
	private static <E> E getInstanceFromSettings(String key) {
		E obj = null;
		try {
			// reflection을 사용해서 인스턴스 만들기
			String className = mappings.getProperty(key); // 클래스 이름 읽기 (패키지명 포함)
			Class claz = Class.forName(className); // 클래스 이름으로 클래스 정보 객체 생성
			obj = (E)claz.getDeclaredConstructor().newInstance(); // 클래스 정보 객체를 사용해서 인스턴스 만들기 (new X)
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return obj;
	}
	// object 호환성 활용한 객체 생성기
	private static Object getInstanceFromSettings2(String key) {
		Object obj = null;
		try {
			// reflection을 사용해서 인스턴스 만들기
			String className = mappings.getProperty(key); // 클래스 이름 읽기 (패키지명 포함)
			Class claz = Class.forName(className); // 클래스 이름으로 클래스 정보 객체 생성
			obj = claz.getDeclaredConstructor().newInstance(); // 클래스 정보 객체를 사용해서 인스턴스 만들기 (new X)
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return obj;
	}
	
	public static AppSettingsDao getSettingsDao() {
		
		AppSettingsDao settingsDao = getInstanceFromSettings("app.settings.dao");
		// AppSettingsDao settingsDao2 = (AppSettingsDao)getInstanceFromSettings2("app.settings.dao");
		
		return settingsDao;
		
	}
	
	public static MemberDao getMemberDao() {		
		
		MemberDao memberDao = getInstanceFromSettings("member.dao");
		return memberDao;		
		
	}
	
	public static BoardDao getBoardDao() {		
		
		BoardDao boardDao = getInstanceFromSettings("board.dao");
		return boardDao;		
		
	}
	
	

}
