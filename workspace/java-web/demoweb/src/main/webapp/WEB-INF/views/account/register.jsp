<%@ page language="java" 
		 contentType="text/html; charset=utf-8" 
		 pageEncoding="utf-8"%>

<!DOCTYPE html>

<html>
<head>
	<meta charset='utf-8' />
	<title>Register</title>
	<link rel='Stylesheet' href='/demoweb/styles/default.css' />
	<link rel='Stylesheet' href='/demoweb/styles/input.css' />
</head>
<body>

	<div id='pageContainer'>
		
		<jsp:include page="/WEB-INF/views/modules/header.jsp" />
		
		<div id="inputcontent">
			<br /><br />
		    <div id="inputmain">
		        <div class="inputsubtitle">회원기본정보</div>
		        <!-- <form id="registerform" action="/demoweb/account/register" method="post"> --><!-- 절대경로표시 -->
		        <form id="registerform" action="register" method="post"><!-- 상대경로표시 -->
		        <table>
		            <tr>
		                <th>아이디(ID)</th>
		                <td>
		                    <input type="text" id="memberId" name="memberId" style="width:180px" />
		                    <button id="btn-dup-check" style="width:90px">중복검사</button>
		                </td>
		            </tr>
		            <tr>
		                <th>비밀번호</th>
		                <td>
		                	<input type="password" id="passwd" name="passwd" style="width:280px" />
		                </td>
		            </tr>
		            <tr>
		                <th>비밀번호 확인</th>
		                <td>
		                    <input type="password" id="confirm" name="confirm" style="width:280px" />
		                </td>
		            </tr>
		            <tr>
		                <th>이메일</th>
		                <td>
		                	<input type="text" id="email" name="email" style="width:280px" />
		                </td>
		            </tr>
		                       		            
		        </table>
		        <div class="buttons">
		        	<input id="register" type="submit" value="등록" style="height:25px" />
		        	<input id="cancel" type="button" value="취소" style="height:25px"  />
		        </div>
		        </form>
		    </div>
		</div>   	
	</div>

	<script src="http://code.jquery.com/jquery-3.7.1.js"></script>
	<script type="text/javascript">
	$(function() {
		
		$('#btn-dup-check').on('click', function(event) {
			
			const memberId = $('#memberId').val();
			if (memberId.length == 0) {
				alert("아이디를 입력하세요");
				return;
			}
			
			// 아이디에 대한 요구사항 (예:영문자 숫자 특수문자 조합 8개이상 20개 이하) 검사
			
			$.ajax({
				"url": "account/dup-check",
				"data": "id=" + memberId,
				"dataType": "text", // 수신 컨텐츠 종류
				"success": function(result, status, xhr) {
					if (result == "success") {
						alert('사용 가능한 아이디');
					} else {
						alert('이미 사용중인 아이디');
					}
				},
				"error": function(xhr, status, err) {
					alert('중복 검사 수행중에 문제가 발생했습니다. 다시 시도해 주세요.');
				}
			});
			
		});
		
	});
	</script>

</body>
</html>




























