package com.example.spring.ioc;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("timeService")
public class MyTimeService implements TimeService {

	// 1. 직접 인스턴스 생성 ( new 사용 )
	// SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	
	// 2. 
	SimpleDateFormat format;
	// public MyTimeService() {} // 이 생성자를 우선 사용
	// public MyTimeService(@Autowired SimpleDateFormat format) { // <constructor-arg와 같은 효과
	public MyTimeService(SimpleDateFormat format) { // 유일한 생성자의 경우 @Autowired 생략 가능
		this.format = format;
	}

	public String getTimeString() {
		
		return format.format(new Date());
		
	}


	
	
}





