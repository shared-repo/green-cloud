<%@ page import="java.util.Date"%>
<%@ page language="java" 
		 contentType="text/html; charset=UTF-8"
    	 pageEncoding="UTF-8"%>
    	 
<%!
String getTimeString() {
	return new Date().toString();	
}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<h2><% out.println(new Date()); %></h2>
	<h2><%= new Date() %></h2>
	<h2><%= getTimeString() %></h2>
	
	<!-- HTML 주석 ( 서블릿 컨테이너는 문자열로 처리 -> 응답 컨텐츠에 포함, 브라우저가 주석으로 처리 ) -->
	<%-- JSP 주석 ( 서블릿 컨테이가 주석으로 초리 -> 응답 컨텐츠에 포함되지 않습니다. ) --%>

</body>
</html>