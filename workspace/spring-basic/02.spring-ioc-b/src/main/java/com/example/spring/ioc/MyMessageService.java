package com.example.spring.ioc;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

@Component("messageService")
@Scope("prototype") // <bean ... scope="prototype"과 같은 효과
public class MyMessageService implements MessageService {
	
	int data;
	public MyMessageService() {
		data = (int)(Math.random() * 900) + 100;
	}
	
	public String getMessage() {
		return "Hello, Spring IoC Container !!! " + data;
	}
	
	@PostConstruct // <bean ... init-method="init"과 같은 효과
	public void init() {
		System.out.println("MyMessageService.init()");
	}
	@PreDestroy // <bean ... destroy-method="destroy"와 같은 효과
	public void destroy() {
		System.out.println("MyMessageService.destroy()");
	}
	

}
