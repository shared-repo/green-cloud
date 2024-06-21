package com.example.spring.ioc;

import lombok.Setter;

public class MyServiceConsumer2 implements ServiceConsumer {

	@Setter // setTimeService() 자동 생성
	private TimeService timeService;
	@Setter // setMessageService() 자동 생성
	private MessageService messageService;

	public void doSomething() {
		String message = messageService.getMessage();
		System.out.println(message);
		message = timeService.getTimeString();
		System.out.println(message);
	}

}
