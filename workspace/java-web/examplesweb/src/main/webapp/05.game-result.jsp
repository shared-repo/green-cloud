<%@ page language="java" 
		 contentType="text/html; charset=UTF-8"
    	 pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>가위 바위 보 게임 결과</title>
</head>
<body>

	<h1>게임 결과 : <%= request.getAttribute("result") %></h1>
	
	[ <a href="/examplesweb/">홈으로 이동</a> ]
	[ <a href="/examplesweb/05.game.jsp">게임 다시</a> ]

</body>
</html>