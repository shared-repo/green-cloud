package com.examplesweb.listener;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;

// @WebListener -- web.xml 의 <listener> 설정을 대신하는 annotation
public class ExamplesWebListener implements ServletContextListener, HttpSessionListener {

	// 서버가 시작될 때 호출되는 메서드
	public void contextInitialized(ServletContextEvent sce) {
		System.out.println("애플리케이션 시작");
	}

	// 브라우저가 처음 요청했을 때 호출되는 메서드 -> 세션이 시작될 때
	public void sessionCreated(HttpSessionEvent se) {
		System.out.println("세션 시작");
	}

	// 브라우저가 서버를 떠날 때 -> session.invalidate() 호출 또는 세션 유효시간이 경과될 때
	public void sessionDestroyed(HttpSessionEvent se) {
		System.out.println("세션 종료");
	}

	// 서버가 종료될 때 호출되는 메서드
	public void contextDestroyed(ServletContextEvent sce) {
		System.out.println("애플리케이션 종료");
	}

}
