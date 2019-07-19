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
<script src="js/common.js"></script>
<script type="text/javascript">
	$(function() {
		$("#f1").submit(function() {
			$(".error").css("color","black");
			$(".error2").css("display","none");
			var error=true;				
			if(checkInputEmpty($("input[name]"))==false){
				error=false;
			}   
			return error;
		}) 
	})
</script>
</head>
<body>
<form action="changePwd.do" method="post" id="f1">
	현재암호:<br><input type="password" name="pass1">
	<span class="error">비밀번호를 입력해주세요</span>
	<c:if test="${res<0 }">
		<span class="error2">현재 암호가 일치하지 않습니다.</span>
	</c:if>
	<br>
	새 암호:<br><input type="password" name="pass2">
	<span>비밀번호를 입력해주세요</span>
	<br><input type="submit" value="암호변경">
</form>
</body>
</html>