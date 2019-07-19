<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
table {
	border-collapse: collapse;
}

td,th {
	border: 1px solid black;
}
</style>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script type="text/javascript">
	
</script>
</head>
<body>
	<table>
		<tr>
			<th>아이디</th>
			<th>이름</th>
			<th>비밀번호</th>
			<th>가입날자</th>
			
		</tr>
		<c:forEach var="member" items="${list }">

			<tr>
				<td>${member.memberid }</td>
				<td>${member.password }</td>
				<td>${member.name }</td>
				<td>${member.regdate }</td>
			</tr>

		</c:forEach>
	</table>
</body>
</html>