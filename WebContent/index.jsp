<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">

</style>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script type="text/javascript">

</script>
</head>
<body>
	<c:if test="${Auth!=null }"><!-- 로그인 상태-->
		<p>${Auth.name }[${Auth.id}]님, 로그인하였습니다.</p>
		<a href="memberList.do">[회원 리스트]</a>
		<a href="logout.do">로그아웃</a>
		<a href="changePwd.do">비밀번호 변경</a>
	</c:if>
	<c:if test="${Auth==null }"><!-- 로그아웃 상태-->
		<a href="login.do">로그인</a>
		<a href="join.do">[회원가입]</a>
	</c:if>
	<a href="article/list.do">[게시판 리스트 보기]</a>
</body>
</html>