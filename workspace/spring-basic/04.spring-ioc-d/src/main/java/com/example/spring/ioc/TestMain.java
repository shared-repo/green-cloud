package com.example.spring.ioc;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class TestMain {

	public static void main(String[] args) {
		
		// 1. 직접 인스턴스 생성 ( new 사용 )
		// ServiceConsumer serviceConsumer = new MyServiceConsumer();
		
		// 2. IoC 컨테이너를 사용해서 인스턴스 생성
		AnnotationConfigApplicationContext appContext = 
				new AnnotationConfigApplicationContext(MyBeanConfig.class);
		// MyBeanConfig 설정 클래스에서 serviceConsumer()로 등록된 클래스의 인스턴스 반환
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
