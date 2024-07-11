﻿<%@ page language="java" 
		 contentType="text/html; charset=utf-8" 
		 pageEncoding="utf-8"%>
		 
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>

<html>
<head>
	<meta charset='utf-8' />
	<title>Register</title>
	<link rel='Stylesheet' href='/resources/styles/default.css' />
	<link rel='Stylesheet' href='/resources/styles/input.css' />
	<style>
		.error {
			color: red;
			font-size: 7pt;
			font-weight: bold;
		}
	</style>
</head>
<body>

	<div id='pageContainer'>
		
		<jsp:include page="/WEB-INF/views/modules/header.jsp" />
		
		<div id="inputcontent">
			<br /><br />
		    <div id="inputmain">
		        <div class="inputsubtitle">회원기본정보</div>
		        <form:form id="registerform" action="register" method="post" modelAttribute="member">
		        <table>
		            <tr>
		                <th>아이디(ID)</th>
		                <td>
		                    <form:input type="text" id="memberId" path="memberId" style="width:180px" />
		                    <button id="btn-dup-check" style="width:90px">중복검사</button>
		                    <br>
		                    <form:errors path="memberId" cssClass="error" />
		                </td>
		            </tr>
		            <tr>
		                <th>비밀번호</th>
		                <td>
		                	<form:input type="password" id="passwd" path="passwd" style="width:280px" />
		                	<br>
		                	<form:errors path="passwd" cssClass="error" />
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
		                	<form:input type="text" id="email" path="email" style="width:280px" />
		                	<br>
		                	<form:errors path="email" cssClass="error" />
		                </td>
		            </tr>
		                       		            
		        </table>
		        <div class="buttons">
		        	<input id="register" type="button" value="등록" style="height:25px" />
		        	<input id="cancel" type="button" value="취소" style="height:25px"  />
		        </div>
		        </form:form>
		    </div>
		</div>   	
	</div>

	<script src="http://code.jquery.com/jquery-3.7.1.js"></script>
	<script type="text/javascript">
	$(function() {
		
		$('#btn-dup-check').on('click', function(event) {
			
			event.stopPropagation(); // 이 이벤트를 상위 요소에 전달하지 않도록 설정
			event.preventDefault(); // 이 이벤트와 연관된 기본 동작을 수행하지 않도록 설정 (여기서는 submit)
			
			const memberId = $('#memberId').val();
			if (memberId.length == 0) {
				alert("아이디를 입력하세요");
				return;
			}
			
			// 아이디에 대한 요구사항 (예:영문자 숫자 특수문자 조합 8개이상 20개 이하) 검사
			
			$.ajax({
				"url": "dup-check/" + memberId,
				"dataType": "text", // 수신 컨텐츠 종류
				"success": function(result, status, xhr) {
					if (result == "true") {
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
		
		
		$('input#register').on('click', function(event) {
			event.preventDefault(); // 현재 이벤트를 발생시킨 요소의 기본 동작 수행 차단 ( 예: <a>인 경우 href로 이동하는 동작 수행 차단 )
			
			$('#registerform').submit(); // 서버로 전송
		})
		
	});
	</script>

</body>
</html>




























