<%@page import="java.util.ArrayList"%>
<%@page import="com.demoweb.dto.BoardDto"%>
<%@ page language="java" 
		 contentType="text/html; charset=utf-8"
    	 pageEncoding="utf-8"%>
    	 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>

<html>
<head>
	<meta charset="utf-8" />
	<title>게시글 목록</title>
	<link rel="Stylesheet" href="/demoweb/styles/default.css" />
	<style>
	a { text-decoration: none }
	</style>
</head>
<body>

	<div id="pageContainer">
	
	
		<jsp:include page="/WEB-INF/views/modules/header.jsp" />
		
		<div style="padding-top:25px;text-align:center">
		
			[ <a href="write">게시글 쓰기</a> ]
			<br /><br />
			
			<table border="1" style="width:600px;margin:0 auto">
				<tr style="background-color:orange;height:30px">
					<th style="width:100px">번호</th>
					<th style="width:300px">제목</th>
					<th style="width:125px">작성자</th>
					<th style="width:50px">조회수</th>
					<th style="width:125px">작성일</th>
					<th style="width:125px">수정일</th>
				</tr>
				
				<%-- 게시물 목록 표시 : 위의 <tr>과 <th>를 참고해서 구현, 반복문 사용 필요 --%>
				<c:forEach var="board" items="${ boardList }">				
				<tr style="height:30px">
					<td>${ board.boardNo }</td>
					<td style="text-align:left;padding-left:5px">
					<c:choose>
					<c:when test="${ board.deleted }">										
						<span style='color:gray'>${ board.title } [삭제된 글입니다]</span>
					</c:when>
					<c:otherwise>
						<a href="detail?boardno=${ board.boardNo }&pageNo=${ pageNo }">${ board.title }</a>
					</c:otherwise>
					</c:choose>
					</td>
					<td style="text-align:left;padding-left:5px">${ board.writer }</td>
					<td>${ board.readCount }</td>
					<td>${ board.writeDate }</td>
					<td>${ board.modifyDate }</td>
				</tr>
				</c:forEach>
								
			</table>
			
			<br><br>
			
			${ pager }
						
			<br /><br /><br /><br />
		
		</div>
		
	</div>
		

</body>
</html>











