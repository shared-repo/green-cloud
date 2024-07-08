package com.example.springboot.exampleweb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {
	
	@RequestMapping(path = { "/", "/home" })
	@ResponseBody
	public String home() {
		return "Hello, Spring Boot";
	}

}
