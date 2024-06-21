package com.example.spring.ioc;

import java.text.SimpleDateFormat;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration // spring ioc container가 사용할 bean 설정 파일 역할 수행
public class MyBeanConfig {
	
	@Bean // <bean 역할 수행, method 이름이 아이디
	public ServiceConsumer serviceConsumer() {
		MyServiceConsumer bean = new MyServiceConsumer();
		bean.setTimeService(timeService()); 		// timeService() 호출은 <bean ref=""와 같은 효과
		bean.setMessageService(messageService());
		return bean;
	}	
	@Bean 
	public TimeService timeService() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return new MyTimeService(format);
	}	
	@Bean(initMethod = "init", destroyMethod = "destroy")
	public MessageService messageService() {
		return new MyMessageService();
	}
	
	@Bean 
	@Scope("prototype")
	public ServiceConsumer serviceConsumer2() {
		MyServiceConsumer2 bean = new MyServiceConsumer2();
		bean.setTimeService(timeService());
		bean.setMessageService(messageService2());
		return bean;
	}
	@Bean(initMethod = "init")
	@Scope("prototype")
	public MessageService messageService2() {
		return new MyMessageService();
	}

}










