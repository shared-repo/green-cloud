package com.example.spring.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller // @Component + 추가 기능
public class HomeController {
	
	@RequestMapping(path = { "/", "/home" })
	public String home(@RequestParam(defaultValue = "") String syncResult, Model model) {
		
		model.addAttribute("syncResult", syncResult);
		return "home";	// "/WEB-INF/views/" + home + ".jsp"
	}

}
