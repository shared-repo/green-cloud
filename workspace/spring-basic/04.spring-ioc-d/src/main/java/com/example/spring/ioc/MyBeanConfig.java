package com.example.spring.ioc;

import java.text.SimpleDateFormat;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration // spring ioc container가 사용할 bean 설정 파일 역할 수행
@ComponentScan(basePackages = { "com.example.spring.ioc" })
public class MyBeanConfig {
	
	@Bean
	public SimpleDateFormat timeFormat() {
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	}

}










