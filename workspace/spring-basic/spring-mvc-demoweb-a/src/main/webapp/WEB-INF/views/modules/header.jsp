<%@ page language="java" 
		 contentType="text/html; charset=UTF-8"
    	 pageEncoding="UTF-8"%>
    	   	 
<%@ taglib prefix="c" uri="jakarta.tags.core" %> <%-- jstl 라이브러리의 core 객체들을 c:tagname 형식으로 사용하는 선언 --%>
		
		<div id="header">
		
            <div class="title">
                <a href="/spring-demoweb/home">DEMO WEBSITE</a>
            </div>
            <div class="links">
            <c:choose>
            	<c:when test="${ empty loginuser }">
            	<a href="/spring-demoweb/account/login">로그인</a>
                <a href="/spring-demoweb/account/register">회원가입</a>
            	</c:when>
            	<c:otherwise>
            	${ loginuser.memberId }님 환영합니다.
            	<a href="/spring-demoweb/account/logout">로그아웃</a>
            	</c:otherwise>
            </c:choose>            
            </div>
        </div>
                
        <div id="menu">
            <div>
                <ul>
                    <li><a href="#">사용자관리</a></li>
					<li><a href="/spring-demoweb/mail/send-mail">메일보내기</a></li>
					<li><a href="#">자료실</a></li>
					<li><a href="/spring-demoweb/board/list">게시판</a></li>
                </ul>
            </div>
		</div>
		
		<div id="counter" style="text-align:right; padding:5px; border: solid 1px">
		[TOTAL : ${ total == null ? "0" : total }]
		[CURRENT : ${ applicationScope.current == null ? "0" : current }]
		</div>