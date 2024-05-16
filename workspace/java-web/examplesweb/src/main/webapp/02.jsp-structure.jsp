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

</body>
</html>