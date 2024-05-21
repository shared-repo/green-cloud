package com.demoweb.listener;

import com.demoweb.dto.AppSetting;
import com.demoweb.service.AppSettingsService;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;

@WebListener
public class DemoWebListener implements ServletContextListener, HttpSessionListener {
	
	private AppSettingsService appSettingService = new AppSettingsService();
	
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		
		AppSetting appSetting = appSettingService.findAppSettingBySettingName("total_counter");
		
		ServletContext application = sce.getServletContext(); // ServletContext == JSP의 application 내장객체
		application.setAttribute("current", 0);
		application.setAttribute("total", Integer.parseInt(appSetting.getSettingValue()));
	}
	
	@Override
	public void sessionCreated(HttpSessionEvent se) {
		ServletContext application = se.getSession().getServletContext();
		int current = (int)application.getAttribute("current");
		int total = (int)application.getAttribute("total");
		application.setAttribute("current", current + 1);
		application.setAttribute("total", total + 1);
	}
	
	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		ServletContext application = se.getSession().getServletContext();
		int current = (int)application.getAttribute("current");
		int total = (int)application.getAttribute("total");
		application.setAttribute("current", current - 1);
		// application.setAttribute("total", total - 1);
	}
	
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		ServletContext application = sce.getServletContext(); // ServletContext == JSP의 application 내장객체
		int total = (int)application.getAttribute("total");
		AppSetting appSetting = new AppSetting("total_counter", String.valueOf(total));
		appSettingService.modifyAppSetting(appSetting);
	}

}










