<%@ page language="java" 
		 contentType="text/html; charset=utf-8" 
		 pageEncoding="utf-8"%>

<!DOCTYPE html>

<html>
<head>
	<meta charset='utf-8' />
	<title>Login</title>
	<link rel='Stylesheet' href='/mvcdemoweb/styles/default.css' />
	<link rel='Stylesheet' href='/mvcdemoweb/styles/input.css' />
</head>
<body>

	<div id='pageContainer'>
		
		<jsp:include page="/WEB-INF/views/modules/header.jsp" />
		
		<div id="inputcontent">
			<br /><br />
		    <div id="inputmain">
		        <div class="inputsubtitle">로그인정보</div>
		        
		        <form action="login" method="post">
		       
		        <table>
		            <tr>
		                <th>아이디(ID)</th>
		                <td>
		                    <input type="text" name="memberId" style="width:280px" />
		                </td>
		            </tr>
		            <tr>
		                <th>비밀번호</th>
		                <td>
		                	<input type="password" name="passwd" style="width:280px" />
		                </td>
		            </tr>
		        </table>
		        
		        <div class="buttons">
		        	<input type="submit" value="로그인" style="height:25px" />
		        	<input type="button" id="cancel-btn" value="취소" style="height:25px" />
		        	<input type="button" id="reset-pwd-btn" value="비밀번호초기화" style="height:25px" />
		        </div>
		        </form>
		        
		    </div>
		</div>  	
	</div>
	
	<script src="http://code.jquery.com/jquery-3.7.1.js"></script>
	<script type="text/javascript">
	$(function() {
		
		$('#cancel-btn').on("click", function(event) {
			location.href = "/mvcdemoweb/home";
		});
	
		$('#reset-pwd-btn').on("click", function(event) {
			location.href = "/mvcdemoweb/account/reset-passwd";
		});
		
		<%-- 서블릿에서 로그인 실패 후 forward로 이동한 경우 --%>
		<% if (request.getAttribute("loginfail") != null) { %>
		alert("로그인 실패 (forward)");
		<% } %>
		
		<%-- 서블릿에서 로그인 실패 후 redirect로 이동한 경우 --%>
		<% if (request.getParameter("loginfail") != null) { %>
		alert("로그인 실패 (redirect)");
		location.href = "login";
		<% } %>
		
	});
	
	</script>

</body>
</html>


