<%@ page language="java" 
		 contentType="text/html; charset=UTF-8"
    	 pageEncoding="UTF-8"%>
    	 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로또 번호 추출기</title>
</head>
<body>
	
	<h3>로또 당첨 예상 번호 뽑기</h3>
	<form action="/examplesweb/select-numbers" method="post">
		평균 하한 : <input type="text" name="min_avg">
		평균 상한 : <input type="text" name="max_avg">
		<input type="submit" value="번호 뽑기">
	</form>
	
</body>
</html>




