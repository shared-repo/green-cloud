<%@ page language="java" 
		 contentType="text/html; charset=UTF-8"
    	 pageEncoding="UTF-8"%>
    	 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>가위 바위 보 게임</title>
</head>
<body>
	
	<h3>가위 바위 보 게임</h3>
	<form action="/examplesweb/dogame" method="post">
		가위 바위 보 선택 : 
		<select name="user">
			<option value="1">가위</option>
			<option value="2">바위</option>
			<option value="3">보</option>
		</select>
		<input type="submit" value="게임 시작">
	</form>
	
</body>
</html>