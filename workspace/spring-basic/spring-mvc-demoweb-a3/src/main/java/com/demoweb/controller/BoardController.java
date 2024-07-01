package com.demoweb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = { "/board" })
public class BoardController {
	
	@GetMapping(path = {"/list"})
	public String list() {
		
		
		return "board/list-without-page"; 	// /WEB-INF/views/ + board/list-without-page + .jsp
	}
	

}
