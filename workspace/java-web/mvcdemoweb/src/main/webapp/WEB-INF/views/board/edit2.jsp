<%@page import="com.demoweb.dto.BoardAttachDto"%>
<%@page import="com.demoweb.dto.BoardDto"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>

<!DOCTYPE html>

<html>
<head>
	<meta charset="utf-8" />
	<title>글수정</title>
	<link rel="Stylesheet" href="/mvcdemoweb/styles/default.css" />
	<link rel="Stylesheet" href="/mvcdemoweb/styles/input2.css" />
</head>
<body>

	<div id="pageContainer">
	
		<jsp:include page="/WEB-INF/views/modules/header.jsp" />
		
		<div style="padding-top:25px;text-align:center">
		<div id="inputcontent">
		    <div id="inputmain">
		        <div class="inputsubtitle">게시글 정보</div>
		        <form action="edit" method="post" enctype="multipart/form-data">
		        <% BoardDto board = (BoardDto)request.getAttribute("board"); %>
		        <input type="hidden" name="boardno" value="<%= board.getBoardNo() %>">
		        <table>
		            <tr>
		                <th>제목</th>
		                <td>
		                    <input type="text" name="title" style="width:580px" value="<%= board.getTitle() %>" />
		                </td>
		            </tr>
		            <tr>
		                <th>작성자</th>
		                <td><%= board.getWriter() %></td>
		            </tr>
		            <tr>
		                <th>첨부파일</th>
		                <td>
		                	<% for (BoardAttachDto attach : board.getAttachments()) { %>
		                	<%= attach.getUserFileName() %> 
		                	[<a href='delete-attach?attachno=<%= attach.getAttachNo() %>&boardno=<%= board.getBoardNo() %>'>삭제</a>]<br>
		                	<% } %>
		                    <input type="file" name="attach" style="width:580px;height:20px" />
		                </td>
		            </tr>
		            <tr>
		                <th>글내용</th>
		                <td>
		                	<textarea name="content" 
		                		style="width:580px" rows="15"><%= board.getContent() %></textarea>
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
	
	</script>

</body>
</html>






