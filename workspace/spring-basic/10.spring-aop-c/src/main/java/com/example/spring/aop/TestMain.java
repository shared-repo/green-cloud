package com.example.spring.aop;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class TestMain {

	public static void main(String[] args) {
		
		// GenericXmlApplicationContext appContext = new GenericXmlApplicationContext("app-context.xml");
		AnnotationConfigApplicationContext appContext = 
				new AnnotationConfigApplicationContext(AppContextConfig.class);
		
		TestA a = appContext.getBean("testA", TestA.class);
		TestB b = appContext.getBean("testB", TestB.class);
		
		a.doSomething1();
		a.doSomething2();
		b.doSomething1();
		b.doSomething2();
		a.doAnother1();
		a.doAnother2();
		b.doAnother1();
		b.doAnother2();
		
		appContext.close();

	}

}
