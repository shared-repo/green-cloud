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
	<link rel="Stylesheet" href="/demoweb/styles/input2.css" />
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

		<br>

		<!-- write comment area -->
		<form id="commentform" action="write-comment" method="post">
			<input type="hidden" name="boardNo" value="${ board.boardNo }" />
			<input type="hidden" name="pageNo" value="${ pageNo }" />
			<input type="hidden" name="writer" value="${ loginuser.memberId }" />
			<table style="width:800px;border:solid 1px;margin:0 auto">
				<tr>
					<td style="width:750px">	                	
						<textarea id="comment_content" name="content" style="width:100%;resize: none;" rows="3"></textarea>	                    
					</td>
					<td style="width:50px;vertical-align:middle">
						<a id="write-comment-lnk" href="javascript:" style="text-decoration:none">
							댓글<br />등록
						</a>
					</td>
				</tr>                    
			</table>
		</form>
		<!-- end of write comment area -->
		
		<br>
	    <hr style="width:800px;margin:0 auto">
	    <br>
	    
	    <!-- comment list area -->
	    <table id="comment-list" style="width:800px;border:solid 1px;margin:0 auto">
		<c:forEach var="comment" items="${ board.comments }">				
			<tr>
				<td style="text-align:left;margin:5px;border-bottom: solid 1px;">
					<table>
						<tr>
							<td>
								<c:forEach begin="0" end="${ comment.depth }">
									&nbsp;&nbsp;
								</c:forEach>
								<c:if test="${ comment.depth > 0 }">
									<img src="/spring-demoweb/resources/image/re.gif">
									&nbsp;
								</c:if>
							</td>
							<td>					
								<div id="comment-view-area-${ comment.commentNo }">
								<c:choose>
								<c:when test="${ comment.deleted }">
									<br><br>
									<span style='color:gray'>삭제된 글입니다.</span>
									<br><br>
								</c:when>
								<c:otherwise>
									${ comment.writer } &nbsp;&nbsp; [<fmt:formatDate value="${ comment.writeDate }" pattern="yyyy-MM-dd hh:mm:ss"/>]
								    <br /><br />
								    <span>${ fn:replace(comment.content, enter, "<br>") }</span>
									<br /><br />
									<div style='float:left; display:${ (not empty loginuser and loginuser.memberId == comment.writer) ? "block" : "none" }'>
								    	<a class="edit-comment" data-comment-no="${ comment.commentNo }" href="javascript:">편집</a>
										&nbsp;
										<a class="delete-comment" data-comment-no="${ comment.commentNo }" href="javascript:">삭제</a>
										&nbsp;&nbsp;
									</div>
									<div style='float:left; display:${ not empty loginuser ? "block" : "none" }'>
										<a class="write-recomment" data-comment-no="${ comment.commentNo }" href="javascript:">댓글쓰기</a>
									</div>
									<span style="clear:both"></span>
								</c:otherwise>
								</c:choose>
								</div>	                
								<div id="comment-edit-area-${ comment.commentNo }" style="display: none">
									${ comment.writer } &nbsp;&nbsp; [${ comment.writeDate }]
									<br /><br />
									<form action="edit-comment" method="post">
									<input type="hidden" name="commentNo" value="${ comment.commentNo }" />
									<textarea name="content" style="width: 99%; resize: none" rows="3" maxlength="200">${ comment.content }</textarea>
									</form>
									<br />
									<div>
										<a class="update-comment" data-comment-no="${ comment.commentNo }" href="javascript:">수정</a> 
										&nbsp; 
										<a class="cancel-edit-comment" data-comment-no="${ comment.commentNo }" href="javascript:">취소</a>
									</div>
								</div>
						
							</td>
			
						</tr>
					</table>
				</td>
			</tr>
		</c:forEach>        	
		</table>
		<!-- end of comment list area -->
	
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
			});
			
			//////////////////////////////////////////////////////
			
			// 댓글쓰기
			$('#write-comment-lnk').on('click', function(event) {
				
				if ($('#comment_content').val().length == 0) {
					alert('댓글 내용을 작성하세요');
					$('#comment_content').focus();
					return;
				}
				
				$('#commentform').submit(); // <form> 요소를 서버로 전송하는 명령
				
			});
		});
	</script>

</body>
</html>










