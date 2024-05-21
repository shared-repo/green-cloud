<%@ page language="java" 
		 contentType="text/html; charset=UTF-8"
    	 pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Web Listener</title>
</head>
<body>
	<a href="09.listener.jsp">새로고침</a>
	<hr>	
	<% if (session.getAttribute("initialized") == null) { // 처음 접속 %>
		<% session.setAttribute("initialized", "초기화 데이터"); %>
		<h3>세션이 초기화되었습니다.</h3>		
	<% } else { %>
		<% session.invalidate(); %>
		<h3>세션을 종료했습니다.</h3>
	<% } %>
	
</body>
</html>