package com.demoweb.controller;

import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

	@Setter(onMethod_ = { @Autowired})
	public ResourceLoader resourceLoader;
	
	@RequestMapping(path = { "/", "/home" })
	public String home() {
//		try {
//			System.out.println(resourceLoader.getResource("classpath:/static/").getFile().getAbsolutePath());
//		} catch (Exception ex) {
//			ex.printStackTrace();
//		}
		return "home";		// /WEB-INF/views/ + home + .jsp
	}

}
