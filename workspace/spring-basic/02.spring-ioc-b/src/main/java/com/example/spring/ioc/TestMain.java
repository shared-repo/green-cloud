package com.example.spring.ioc;

import org.springframework.context.support.GenericXmlApplicationContext;

public class TestMain {

	public static void main(String[] args) {
		
		// 1. 직접 인스턴스 생성 ( new 사용 )
		// ServiceConsumer serviceConsumer = new MyServiceConsumer();
		
		// 2. IoC 컨테이너를 사용해서 인스턴스 생성
		GenericXmlApplicationContext appContext = new GenericXmlApplicationContext("app-context.xml");
		// app-context.xml 설정 파일에서 id=serviceConsumer로 등록된 클래스의 인스턴스 반환
		ServiceConsumer serviceConsumer = appContext.getBean("serviceConsumer", ServiceConsumer.class);
				
		serviceConsumer.doSomething();
		
		ServiceConsumer serviceConsumer1 = appContext.getBean("serviceConsumer", ServiceConsumer.class);
		serviceConsumer1.doSomething();
		
		System.out.println(serviceConsumer == serviceConsumer1);
		
		System.out.println("==========================================");
		
		ServiceConsumer serviceConsumer2 = appContext.getBean("serviceConsumer2", ServiceConsumer.class);
		serviceConsumer2.doSomething();
		ServiceConsumer serviceConsumer21 = appContext.getBean("serviceConsumer2", ServiceConsumer.class);
		serviceConsumer21.doSomething();
		
		System.out.println(serviceConsumer2 == serviceConsumer21);
		
		appContext.close();

	}

}
