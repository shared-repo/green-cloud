package com.example.spring.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller // @Component + 추가 기능
public class HomeController {
	
	@RequestMapping(path = { "/", "/home" })
	public String home() {
		
		return "home";	// "/WEB-INF/views/" + home + ".jsp"
	}

}
