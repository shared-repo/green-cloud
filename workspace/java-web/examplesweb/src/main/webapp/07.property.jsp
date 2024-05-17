<%@page import="com.examplesweb.dto.Person"%>
<%@ page language="java" 
		 contentType="text/html; charset=UTF-8"
    	 pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Property Demo</title>
</head>
<body>

	<% Person person = new Person(); %>
	<jsp:useBean id="person2" class="com.examplesweb.dto.Person" scope="page" />
	
	<% person.setName("장동건"); %>
	<% person.setEmail("jdk@example.com"); %>
	<% person.setPhone("010-6523-7894"); %>
	<jsp:setProperty name="person2" property="name" value="공유" />
	<jsp:setProperty name="person2" property="email" value="ky@example.com" />
	<jsp:setProperty name="person2" property="phone" value="010-5214-3369" />
	
	<h2>
		[<%= person.getName() %>]
		[<%= person.getPhone() %>]
		[<%= person.getEmail() %>]
	</h2>
	<h2>
		[<jsp:getProperty name="person2" property="name" />]
		[<jsp:getProperty name="person2" property="phone" />]
		[<jsp:getProperty name="person2" property="email" />]
	</h2>

</body>
</html>









