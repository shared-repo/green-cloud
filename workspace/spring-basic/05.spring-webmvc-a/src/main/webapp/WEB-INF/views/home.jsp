<%@page import="java.util.Date"%>
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=utf-8" %>
<html>
<head>
	<title>Home</title>
</head>
<body>
	<br /><br /><br />
	<div style="padding-left:20px">
		<h2>Spring MVC Controller Demo</h2>
		
		<h3>1. <a href="demo/param?data1=this%20is%20test%20data&data2=100">Process Request Parameter (GET)</a></h3>
		<h3>2. Process Request Parameter (POST)</h3>
		<form action="demo/param" method="post">
			이름 : <input type="text" name="name" value="홍길동" /><br />
			전화 : <input type="text" name="phone" /><br />
			메일 : <input type="text" name="email" /><br />
			나이 : <input type="text" name="age" /><br />
			나이 : <input type="date" name="birthDate" /><br />
			<input type="submit" value="전송" />
		</form>
		<h3>3. <a href="#">Transfer Data from Controller to View (Refer to 1, 2)</a></h3>
		<h3>4. <a href="demo/redirect">Redirect to Another Action</a></h3>
		<h3>5. <a href="demo/forward">Forward to HTML</a></h3>
		<h3>6-1. <a href="hello.html">static resource not in resources</a></h3>
		<h3>6-2. <a href="resources/hello.html">static resource in resources</a></h3>
		<h3>7. <a href="demo/custom-view">Custom View</a></h3>
		<h3>8-1. <a href="demo/sync">Synchronous Request/Response</a></h3>
		<h3>8-2. <a href="javascript:" id="async-link">Asynchronous Request/Response</a></h3>
		<div style='border:solid 1px;margin-bottom:2px;padding:5px'><%= new Date() %></div>
		<div id="message" style='border:solid 1px;margin-bottom:2px;padding:5px'>${ syncResult }</div>
	</div>
</body>
</html>
