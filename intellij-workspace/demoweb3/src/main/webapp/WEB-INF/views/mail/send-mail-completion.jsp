<%@ page language="java" 
		 contentType="text/html; charset=UTF-8"
    	 pageEncoding="UTF-8"%>
    	 
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
</head>
<body>
	<script type="text/javascript">
	alert("${success ? '메일을 전송했습니다.' : '메일 전송 실패'}");
	location.href = "${success ? '/home' : '/home'}";
	
	<%--
	<c:choose>
		<c:when test="${success}">
			alert('메일을 전송했습니다.');
			location.href = "/home";
		</c:when>
		<c:otherwise>
			alert('메일 전송 실패');
			location.href = "/home";
		</c:otherwise>
	</c:choose>
	--%>
	</script>
</body>
</html>