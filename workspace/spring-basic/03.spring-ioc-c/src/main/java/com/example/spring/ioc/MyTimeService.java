package com.example.spring.ioc;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Component;

public class MyTimeService implements TimeService {

	// 1. 직접 인스턴스 생성 ( new 사용 )
	// SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	
	// 2. 
	SimpleDateFormat format;
	public MyTimeService() {}
	public MyTimeService(SimpleDateFormat format) {
		this.format = format;
	}

	public String getTimeString() {
		
		return format.format(new Date());
		
	}


	
	
}





