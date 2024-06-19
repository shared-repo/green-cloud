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
		        <div class="inputsubtitle">패스워드 초기화</div>
		        
		        <form action="reset-passwd" method="post">
		       
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
		        	<input type="submit" value="변경" style="height:25px" />
		        	<input type="button" id="cancel-btn" value="취소" style="height:25px" />		        	
		        </div>
		        </form>
		        
		    </div>
		</div>  	
	</div>
	
	<script src="http://code.jquery.com/jquery-3.7.1.js"></script>
	<script type="text/javascript">
	$(function() {
		
		$('#cancel-btn').on("click", function(event) {
			// location.href = "/mvcdemoweb/account/login";
			history.back(); // 이전으로 이동하기
		});	
		
	});
	
	</script>

</body>
</html>


