package com.example.spring.ioc;

public class MyServiceConsumer implements ServiceConsumer {

	private TimeService timeService = new MyTimeService(); 
	private MessageService messageService = new MyMessageService();	

	public void doSomething() {
		String message = messageService.getMessage();
		System.out.println(message);
		message = timeService.getTimeString();
		System.out.println(message);
	}

}
