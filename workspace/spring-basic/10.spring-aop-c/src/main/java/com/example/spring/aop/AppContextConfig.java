package com.example.spring.aop;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy
@ComponentScan(basePackages = { "com.example.spring.aop" })
public class AppContextConfig {
	
	@Bean
	public TestA testA() {
		return new TestA();
	}
	@Bean
	public TestB testB() {
		return new TestB();
	}
//	@Bean
//	public Logger logger() {
//		return new Logger();
//	}

}
