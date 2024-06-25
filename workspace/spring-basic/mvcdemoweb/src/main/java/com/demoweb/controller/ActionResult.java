package com.demoweb.controller;

public class ActionResult {
	
	private String viewName;
	private boolean redirect;
	private boolean responseBody;
	
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
	public boolean isResponseBody() {
		return responseBody;
	}
	public void setResponseBody(boolean responseBody) {
		this.responseBody = responseBody;
	}

}
