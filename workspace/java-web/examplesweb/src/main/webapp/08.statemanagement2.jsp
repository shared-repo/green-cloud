<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%
String cookieData = null;
//1. Cookie
Cookie[] cookies = request.getCookies();
if (cookies != null) {
	for (int i = 0; i < cookies.length; i++) {
		if (cookies[i].getName().equals("cookiedata")) {
			cookieData = cookies[i].getValue();
		}
	}
}

//2. Session
String sessionData = null;
if (session.getAttribute("sessiondata") != null) {
	sessionData = 
		session.getAttribute("sessiondata").toString();
}

//3. Application
String applicationData = null;
if (application.getAttribute("applicationdata") != null) {
	applicationData = 
		application.getAttribute("applicationdata").toString();
}

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
</head>
<body>
	<a href="08.statemanagement.jsp">돌아가기</a>
	<hr />
	COOKIE DATA : <%= cookieData %><br />
	SESSION DATA : <%= sessionData %><br />
	APPLICATION DATA : <%= applicationData %><br />
</body>
</html>




