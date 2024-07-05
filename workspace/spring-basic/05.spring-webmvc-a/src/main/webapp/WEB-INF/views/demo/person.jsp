<%@ page language="java" 
		 contentType="text/html; charset=UTF-8"
    	 pageEncoding="UTF-8"%>

<h3>PERSON INFO (made in jsp)</h3>
<table>
	<tr>
		<th>이름</th>
		<td>${ person.name }</td>
	</tr>
	<tr>
		<th>이메일</th>
		<td>${ person.email }</td>
	</tr>
	<tr>
		<th>전화</th>
		<td>${ person.phone }</td>
	</tr>
	<tr>
		<th>나이</th>
		<td>${ person.age }</td>
	</tr>
	<tr>
		<th>생년월일</th>
		<td>${ person.birthDate }</td>
	</tr>
</table>