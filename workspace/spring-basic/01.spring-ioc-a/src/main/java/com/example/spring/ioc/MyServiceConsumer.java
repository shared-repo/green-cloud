package com.example.spring.ioc;

public class MyServiceConsumer implements ServiceConsumer {

	// 1. 직접 인스턴스 생성 ( new 사용 )
	// private TimeService timeService = new MyTimeService(); 
	// private MessageService messageService = new MyMessageService();
	
	// 2. IoC 컨테이너를 사용해서 인스턴스 만들기
	private TimeService timeService;
	public void setTimeService(TimeService timeService) {
		this.timeService = timeService;
	}
	private MessageService messageService;
	public void setMessageService(MessageService messageService) {
		this.messageService = messageService;
	}

	public void doSomething() {
		String message = messageService.getMessage();
		System.out.println(message);
		message = timeService.getTimeString();
		System.out.println(message);
	}

}
