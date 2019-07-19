<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="css/common.css">

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script src="js/common.js"></script>
<script type="text/javascript">
	$(function() {
		$("#f1").submit(function() {
			var idcheck=0;
			var error=true;
			var regId=/^[a-zA-Z0-9]{6,15}$/;
			var regPass=/^[a-zA-Z0-9!@#$%^&*()_+]{1,10}$/;
			var regName=/^[가-힣]{2,5}$/;
			
			if(checkInputEmpty($("input[name]"))==false){
				error=false;
			}
			
			$(".error").css("color","black");
			$(".confirmError").css("display","none");
			$(".duplicatedId").css("display","none");
			
			if(regId.test($("input[name='id']").val())==false){
				$("input[name='id']").next().css("color","red");
				error=false;
			}
			if(regName.test($("input[name='name']").val())==false){
				$("input[name='name']").next().css("color","red");
				error=false;
			}
			
			if(regPass.test($("input[name='password']").val())==false){
				$("input[name='password']").next().css("color","red");
				error=false;
			}
			
			
			if($("input[name='password']").val()!=$("input[name='confirmPassword']").val()){
				$("input[name='confirmPassword']").next().next().css("display","inline");
				error=false;
			}
			if(idcheck==-1){
				alert("중복체크를 해주세요");
				return false;
			}
			return error;
			
			
		})
		
		$("#check").click(function() {
			$.ajax({
				url:"idCheck.do",
				type:"get",
				data:{"id":$("input[name=id]").val()},
				dataType:"json",
				success:function(res){ 
					console.log(res);
					if(res==true){
						alert("사용가능");
						idcheck=1;
						$("#check").css("color","black");
					}
					else{
						alert("사용불가능");
						idcheck=-1;
						$("#check").css("color","red");
					}
				}
			})
		})
	})
</script>
</head>
<body>
	<form action="join.do" method="post" id="f1">
		<p>
			<label>아이디</label>
			<input type="text" name="id" value="${param.id }">
			<span class="error">ID를 입력하세요(6~15 영어,숫자)</span>
			<c:if test="${duplicatedId==true }">
				<span class="duplicatedId">이미 사용중인 ID입니다</span>
			</c:if>
			<button id="check">중복체크</button>
		</p>
		<p>
			<label>이름</label>
			<input type="text" name="name" value="${param.name }">
			<span class="error">이름을 입력하세요(2~5 한글)</span>
		</p>
		<p>
			<label>비밀번호</label>
			<input type="password" name="password">
			<span class="error">암호를 입력하세요(1~10 영어,숫자,특문)</span>
		</p>
		<p>
			<label>비밀번호 확인</label>
			<input type="password" name="confirmPassword">
			<span class="error">암호 확인란을 입력하세요</span>
			<span class="confirmError">암호가 일치하지 않습니다.</span>
		</p>
		<p>
			<input type="submit">
		</p>
		
	</form>
</body>
</html>