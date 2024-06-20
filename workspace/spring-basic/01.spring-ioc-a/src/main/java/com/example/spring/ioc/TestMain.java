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
		
		appContext.close();

	}

}
