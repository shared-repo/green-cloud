package com.example.springboot.exampleweb2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(path = { "/", "/home" })
public class HomeController {

	@GetMapping
	@ResponseBody
	public String home() {
		return "Hello, Spring Boot with Maven";
	}
	
}
