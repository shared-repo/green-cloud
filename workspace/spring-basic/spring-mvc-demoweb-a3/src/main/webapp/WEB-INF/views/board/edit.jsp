<%@ page language="java" 
		 contentType="text/html; charset=utf-8"
    	 pageEncoding="utf-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>

<html>
<head>
	<meta charset="utf-8" />
	<title>글수정</title>
	<link rel="Stylesheet" href="/spring-demoweb/resources/styles/default.css" />
	<link rel="Stylesheet" href="/spring-demoweb/resources/styles/input2.css" />
</head>
<body>

	<div id="pageContainer">
	
		<jsp:include page="/WEB-INF/views/modules/header.jsp" />
		
		<div style="padding-top:25px;text-align:center">
		<div id="inputcontent">
		    <div id="inputmain">
		        <div class="inputsubtitle">게시글 정보</div>
		        <form action="edit" method="post" enctype="multipart/form-data">		        
		        <input type="hidden" name="boardNo" value="${ board.boardNo }">
		        <input type="hidden" name="pageNo" value="${ pageNo }">
		        <table>
		            <tr>
		                <th>제목</th>
		                <td>
		                    <input type="text" name="title" style="width:580px" value="${ board.title }" />
		                </td>
		            </tr>
		            <tr>
		                <th>작성자</th>
		                <td>${ board.writer }</td>
		            </tr>
		            <tr>
		                <th>첨부파일</th>
		                <td>
		                	<c:forEach var="attach" items="${ board.attachments }">
		                	<div data-attachno='${ attach.attachNo }'>
		                	${ attach.userFileName } 
		                	[<a href='javascript:' class='delete-attach' data-attachno='${ attach.attachNo }'>삭제</a>]
		                	</div>
		                	</c:forEach>
		                    <input type="file" name="attach" style="width:580px;height:20px" />
		                </td>
		            </tr>
		            <tr>
		                <th>글내용</th>
		                <td>
		                	<textarea name="content" 
		                		style="width:580px" rows="15">${ board.content }</textarea>
		                </td>
		            </tr>
		        </table>
		        <div class="buttons">
		        	<input type="submit" value="글수정" style="height:25px" />
		        	<input id="btn-cancel" type="button" value="취소" style="height:25px"  />
		        </div>
		        </form>
		    </div>
		</div>		  	
	
	</div>
	</div>	
	
	<script src="https://code.jquery.com/jquery-3.7.1.js"></script>
	<script type="text/javascript">
		$(function() {
			$('#btn-cancel').on('click', function(event) {
				location.href = 'detail?boardno=${ board.boardNo }&pageNo=${ pageNo }';
			});
			
			$('.delete-attach').on('click', function(event) {
				const attachNo = $(this).data('attachno');
				// alert(attachNo);
				$.ajax({
					"url": "delete-attach",
					"method": "get",
					"data": { 'attachNo': attachNo }, // 'attachNo=' + attachNo
					"success": function(result, status, xhr) {
						if (result === "success") {
							alert('첨부파일을 삭제했습니다.')
							$("div[data-attachno=" + attachNo + "]").remove();
						} else {
							alert('fail to delete attach 1');
						}
					},
					"error": function(xhr, status, err) {
						alert('fail to delete attach 2');	
					}
					
				});
			});
		});
	</script>

</body>
</html>






