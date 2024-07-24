<%@ page language="java" 
		 contentType="text/html; charset=UTF-8"
    	 pageEncoding="UTF-8"%>
    	 
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
    	 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Result Page</title>
</head>
<body>

	<h1>Result Page</h1>
	
	<h3>DATA1 : ${ requestScope.newdata1 } / DATA2 : ${ requestScope.newdata2 }</h3>
	<hr>
	<h3>PERSON : ${ requestScope.person }</h3>
	<h3>PERSON : ${ requestScope.person2 }</h3>
	
	<hr>

	<c:forEach var="p" items="${ personList.persons }" varStatus="s">
		<h4>${ s.count }. ${ p }</h4>
	</c:forEach>
</body>
</html>






