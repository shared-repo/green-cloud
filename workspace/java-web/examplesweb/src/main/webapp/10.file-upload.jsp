<%@ page language="java" 
		 contentType="text/html; charset=UTF-8"
    	 pageEncoding="UTF-8"%>
    	 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>File Upload Test</title>
</head>
<body>

<h3>폼 데이터와 파일 전송</h3>
<form action="file-upload" method="post" enctype="multipart/form-data">
DATA 1 : <input type="text" name="data1"><br>
DATA 2 : <input type="text" name="data2"><br>
첨부파일 : <input type="file" name="attach"><br>
<input type="submit" value="전송">
</form>

</body>
</html>