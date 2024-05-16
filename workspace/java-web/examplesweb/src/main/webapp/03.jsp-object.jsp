<%@page import="java.util.Date"%>
<%@ page language="java" 
		 contentType="text/html; charset=UTF-8"
    	 pageEncoding="UTF-8"%>
    	 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSP Object</title>
</head>
<body>

	<h2><% out.println("JSP 내장 객체 연습"); %></h2>
	
	<%
	// 내장 객체에 데이터 저장
	pageContext.setAttribute("page-data", "페이지 컨텍스트 객체에 저장된 데이터");
	request.setAttribute("request-data", "요청 객체에 저장된 데이터");
	session.setAttribute("session-data", "세션 객체에 저장된 데이터");
	application.setAttribute("application-data", "애플리케이션 객체에 저장된 데이터");
	%>
	
	<h3><%= pageContext.getAttribute("page-data") %></h3>
	<h3><%= request.getAttribute("request-data") %></h3>
	<h3><%= session.getAttribute("session-data") %></h3>
	<h3><%= application.getAttribute("application-data") %></h3>

</body>
</html>