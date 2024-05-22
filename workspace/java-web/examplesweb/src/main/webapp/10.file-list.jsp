<%@page import="java.net.URLEncoder"%>
<%@page import="java.io.File"%>
<%@ page language="java" 
		 contentType="text/html; charset=UTF-8"
    	 pageEncoding="UTF-8"%>
    	 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Uploaded File List</title>
</head>
<style>
	a { text-decoration: none; }
</style>
<body>

[ <a href="10.file-upload.jsp">파일 업로드</a> ]
<hr>

<% 
String uploadDir = application.getRealPath("/upload-files");
File dir = new File(uploadDir);	// File : 파일 또는 폴더를 관리하는 객체
File[] files = dir.listFiles();	// 폴더에 포함된 모든 파일과 폴더를 배열로 반환
%>
<% for (File file : files) { %>
<a href="file-download?filename=<%= file.getName() %>"><%= file.getName() %></a>
<br><br>
<% } %>


</body>
</html>











