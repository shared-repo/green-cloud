package com.demoweb.config;

import com.demoweb.interceptor.AuthInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.File;
import java.util.Properties;

@Configuration
public class WebConfiguration implements WebMvcConfigurer {

//	@Override
//	public void addInterceptors(InterceptorRegistry registry) {
//		registry.addInterceptor(authInterceptor()).addPathPatterns("/board/**")
//												  .excludePathPatterns("/board/list", "/board/detail");
//	}
	
	@Bean
	public AuthInterceptor authInterceptor() {
		return new AuthInterceptor();
	}
	
	@Bean
	public JavaMailSender mailSender() {
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		mailSender.setHost("smtp.naver.com");
		mailSender.setPort(465);
		mailSender.setDefaultEncoding("utf-8");
		mailSender.setUsername("");
		mailSender.setPassword("");
		
		Properties props = new Properties();
		props.put("mail.debug", true);
		props.put("mail.smtp.starttls.enable", true);
		props.put("mail.smtp.starttls.required", true);
		props.put("mail.smtp.auth", true);
		props.put("mail.smtp.ssl.enable", true);
		props.put("mail.smtp.ssl.trust", true);
		mailSender.setJavaMailProperties(props);
		
		return mailSender;
	}
	
	@Bean
	public ResourceBundleMessageSource messageSource() {
		ResourceBundleMessageSource source = new ResourceBundleMessageSource();
		source.setBasenames("message.label");
		
		return source;
	}
	
	@Bean
	public StandardServletMultipartResolver multipartResolver() {
		return new StandardServletMultipartResolver();
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		// 별도의 static 경로 지정
		// ( image, css, js, html 등 controller를 거치지 않고 요청할 수 있는 경로 )
		String currentDir = System.getProperty("user.dir");
		File uploadDir = new File(currentDir, "upload");
		if (!uploadDir.exists()) {
			uploadDir.mkdirs();
		}
		System.out.println("1. ----------------------> " + uploadDir.toURI().toString());
		registry
				.addResourceHandler("/demo/uploads/**") // 웹 요청 경로
				.addResourceLocations(uploadDir.toURI().toString()); // static 경로
		// D:\abc\def -> file:D:\abc\def
	}
}










