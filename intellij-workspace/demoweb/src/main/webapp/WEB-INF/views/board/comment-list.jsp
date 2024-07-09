<%@ page language="java" 
		 contentType="text/html; charset=utf-8"
    	 pageEncoding="utf-8"%>

<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fn" uri="jakarta.tags.functions" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>

		<c:forEach var="comment" items="${ comments }">				
			<tr>
				<td style="text-align:left;margin:5px;border-bottom: solid 1px;">
					<table>
						<tr>
							<td>
								<c:forEach begin="0" end="${ comment.depth }">
									&nbsp;&nbsp;
								</c:forEach>
								<c:if test="${ comment.depth > 0 }">
									<img src="/demoweb/image/re.gif">
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
									<input type="hidden" name="boardNo" value="${ comment.boardNo }" />
									<input type="hidden" name="commentNo" value="${ comment.commentNo }" />
									<textarea name="content" style="width: 99%; resize: none" rows="3" cols="120">${ comment.content }</textarea>
									</form>
									<br />
									<div>
										<a class="modify-comment" data-comment-no="${ comment.commentNo }" href="javascript:">수정</a> 
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