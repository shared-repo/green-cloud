<%@page import="com.demoweb.dto.MemberDto"%>
<%@page import="com.demoweb.dto.BoardAttachDto"%>
<%@page import="com.demoweb.dto.BoardDto"%>
<%@ page language="java" 
		 contentType="text/html; charset=utf-8"
    	 pageEncoding="utf-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
    
<!DOCTYPE html>

<html>
<head>
	<meta charset="utf-8" />
	<title>글상세보기</title>
	<link rel="Stylesheet" href="/demoweb/styles/default.css" />
	<link rel="Stylesheet" href="/demoweb/styles/input.css" />
</head>
<body>

	<div id="pageContainer">
	
		<jsp:include page="/WEB-INF/views/modules/header.jsp" />
		
		<div style="padding-top:25px;text-align:center">
		<div id="inputcontent">
		    <div id="inputmain">
		        <div class="inputsubtitle">게시글 정보</div>		        
		        <table>
		            <tr>
		                <th>제목</th>
		                <td>${ board.title }</td>
		            </tr>
		            <tr>
		                <th>작성자</th>
		                <td>${ board.writer }</td>
		            </tr>
		            <tr>
		            	<th>조회수</th>
		            	<td>${ board.readCount }</td>
		            </tr>
		            <tr>
		            	<th>작성일자</th>
		            	<td><fmt:formatDate value="${ board.writeDate }" pattern="yyyy-MM-dd HH:mm:ss" /></td>
		            </tr>
		            <tr>
		            	<th>수정일자</th>
		            	<td><fmt:formatDate value="${ board.writeDate }" pattern="yyyy-MM-dd HH:mm:ss" /></td>
		            </tr>
		            <tr>
		                <th>첨부파일</th>
		                <td>
		                <c:forEach var="attach" items="${ board.attachments }">
		                	<a href="download?attachno=${ attach.attachNo }">
		                	${ attach.userFileName }
		                	</a><br>
						</c:forEach>
		                </td>
		            </tr>
		            <tr>
		                <th>글내용</th>
						<td>
<c:set var="enter" value="
" />
							${ fn:replace(board.content, enter, "<br>") }
						</td>
		            </tr>
		        </table>
		        <div class="buttons">
		        	<%-- <c:if test="${ not empty loginuser and loginuser.memberId eq board.writer }"> --%>
		        	<c:if test="${ loginuser != null && loginuser.memberId == board.writer}">
		        	<input type="button" id="edit_button" value="편집" style="height:25px" />
		        	<input type="button" id="delete_button" value="삭제" style="height:25px" />
		        	</c:if>
		        	<input type="button" id="tolist_button" value="목록보기" style="height:25px" />
		        </div>
		    </div>
		</div>   	
	
	</div>
	</div>
	
	<script src="https://code.jquery.com/jquery-3.7.1.js"></script>
	<script type="text/javascript">
		$(function() {
			$('#delete_button').on('click', function(event) {
				const ok = confirm("${ board.boardNo }번 글을 삭제할까요?");
				if (ok) {
					location.href = 'delete?boardno=${ board.boardNo }';
				}
			});
			
			$('#edit_button').on('click', function(event) {
				location.href = 'edit?boardno=${ board.boardNo }';
			})
		});
	</script>

</body>
</html>










