<%@page import="com.demoweb.dto.MemberDto"%>
<%@page import="com.demoweb.dto.BoardAttachDto"%>
<%@page import="com.demoweb.dto.BoardDto"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>

    
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
		        <% BoardDto board = (BoardDto)request.getAttribute("board"); %>
		        <table>
		            <tr>
		                <th>제목</th>
		                <td><%= board.getTitle() %></td>
		            </tr>
		            <tr>
		                <th>작성자</th>
		                <td><%= board.getWriter() %></td>
		            </tr>
		            <tr>
		            	<th>조회수</th>
		            	<td><%= board.getReadCount() %></td>
		            </tr>
		            <tr>
		            	<th>작성일자</th>
		            	<td><%= board.getWriteDate() %></td>
		            </tr>
		            <tr>
		            	<th>수정일자</th>
		            	<td><%= board.getModifyDate() %></td>
		            </tr>
		            <tr>
		                <th>첨부파일</th>
		                <td>
		                <% for(BoardAttachDto attach : board.getAttachments()) { %>
		                	<a href="download?attachno=<%= attach.getAttachNo() %>">
		                	<%= attach.getUserFileName() %>
		                	</a><br>
						<% } %>						
		                </td>
		            </tr>
		            <tr>
		                <th>글내용</th>
						<td>
							<%= board.getContent().replace("\r\n", "<br>")
												  .replace("\r", "<br>")
												  .replace("\n", "<br>") %>
						</td>
		            </tr>
		        </table>
		        <div class="buttons">
		        	<% MemberDto loginUser = (MemberDto)session.getAttribute("loginuser"); %>
		        	<% if (loginUser != null	// 로그인한 사용자 조건 
		        		   && loginUser.getMemberId().equals(board.getWriter())) { // 현재 로그인한 사용자가 작성자인 경우 %>
		        	<input type="button" id="edit_button" value="편집" style="height:25px" />
		        	<input type="button" id="delete_button" value="삭제" style="height:25px" />
		        	<% } %>
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
				const ok = confirm("<%= board.getBoardNo() %>번 글을 삭제할까요?");
				if (ok) {
					location.href = 'delete?boardno=<%= board.getBoardNo() %>';
				}
			});
			
			$('#edit_button').on('click', function(event) {
				location.href = 'edit?boardno=<%= board.getBoardNo() %>';
			})
		});
	</script>

</body>
</html>










