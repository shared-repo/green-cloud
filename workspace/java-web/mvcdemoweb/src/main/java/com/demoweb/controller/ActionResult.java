package com.demoweb.controller;

public class ActionResult {
	
	private String viewName;
	private boolean redirect;
	
	public ActionResult() {}
	public ActionResult(String viewName, boolean redirect) {
		this.viewName = viewName;
		this.redirect = redirect;
	}
	
	public String getViewName() {
		return viewName;
	}
	public void setViewName(String viewName) {
		this.viewName = viewName;
	}
	public boolean isRedirect() {
		return redirect;
	}
	public void setRedirect(boolean redirect) {
		this.redirect = redirect;
	}

}
