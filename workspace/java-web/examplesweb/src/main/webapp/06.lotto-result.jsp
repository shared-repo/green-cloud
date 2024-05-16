<%@ page language="java" 
		 contentType="text/html; charset=UTF-8"
    	 pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>당첨 예상 번호</title>
</head>
<body>

	<h1>당첨 예상 번호</h1>
	<% int[] numbers = (int[])request.getAttribute("numbers"); %><%-- getAttribute()는 Object로 데이터 반환 : 사용할 때 형변환 필요 --%>
	<h2>
	<% for (int number : numbers) { %>
		[<%= number %>]
		&nbsp;
	<% } %>
	[평균 : <%= request.getAttribute("avg") %>]
	</h2>
	
	[ <a href="/examplesweb/">홈으로 이동</a> ]
	[ <a href="/examplesweb/06.lotto.jsp">뽑기 다시</a> ]

</body>
</html>