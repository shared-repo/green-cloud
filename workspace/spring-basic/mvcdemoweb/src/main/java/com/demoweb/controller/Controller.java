package com.demoweb.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface Controller {
	
	ActionResult handleRequest(HttpServletRequest req, HttpServletResponse resp);

}
