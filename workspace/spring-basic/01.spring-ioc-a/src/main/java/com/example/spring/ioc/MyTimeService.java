package com.example.spring.ioc;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Component;

public class MyTimeService implements TimeService {

	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");	

	public String getTimeString() {
		
		return format.format(new Date());
		
	}


	
	
}





