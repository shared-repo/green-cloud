package com.demoweb.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class LogoutController implements Controller {

	@Override
	public ActionResult handleRequest(HttpServletRequest req, HttpServletResponse resp) {

		HttpSession session = req.getSession(); 

		session.invalidate();
		
		ActionResult ar = new ActionResult("/mvcdemoweb/home", true);
		return ar;
	}

}
