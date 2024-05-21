package com.demoweb.listener;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;

@WebListener
public class DemoWebListener implements ServletContextListener, HttpSessionListener {
	
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		ServletContext application = sce.getServletContext(); // ServletContext == JSP의 application 내장객체
		application.setAttribute("current", 0);
		application.setAttribute("total", 0);
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
	}

}










