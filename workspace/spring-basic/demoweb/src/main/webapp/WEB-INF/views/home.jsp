<%@ page language="java" 
		 contentType="text/html; charset=UTF-8"
    	 pageEncoding="UTF-8"%>

<!DOCTYPE html>

<html>
<head>
	<meta charset='utf-8' />
	<title>Home</title>
	<link rel='Stylesheet' href='/demoweb/styles/default.css' />
</head>
<body>

	<div id='pageContainer'>
		
		<%-- <% pageContext.include("/WEB-INF/views/modules/header.jsp"); %> --%>
		<%-- <%@include file="/WEB-INF/views/modules/header.jsp" %> --%> <%-- 두 jsp 파일을 병합해서 실행 --%>
		
		<%-- 아래는 두 jsp 파일을 각각 실행해서 결과를 병합 --%>
		<jsp:include page="/WEB-INF/views/modules/header.jsp">
			<jsp:param name="bgcolor" value="aqua" />
			<jsp:param name="mydata" value="10" />
		</jsp:include> 
		
		<div id='content'>
			<br /><br /><br />
			<h2 style='text-align:center'>Welcome to Demo WebSite !!!</h2>
		</div>
	</div>

</body>
</html>


    