package com.example.spring.mvc.web;

import java.util.Set;

import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import com.example.spring.mvc.config.DemoWebConfiguration;

import jakarta.servlet.ServletContainerInitializer;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRegistration;

// web.xml을 대신하는 설정 클래스
public class DemoWebServletContainerInitializer implements ServletContainerInitializer {

	@Override
	public void onStartup(Set<Class<?>> c, ServletContext ctx) throws ServletException {
		
		// IoC Container 생성
		AnnotationConfigWebApplicationContext applicationContext = new AnnotationConfigWebApplicationContext();
		applicationContext.register(DemoWebConfiguration.class);
		
		// FrontController 생성
		DispatcherServlet dispatcherServlet = new DispatcherServlet(applicationContext);
		
		ServletRegistration.Dynamic demoWebRegistration = ctx.addServlet("mvcb", dispatcherServlet);
		demoWebRegistration.setLoadOnStartup(1);
		demoWebRegistration.addMapping("/");
		
	}

}
