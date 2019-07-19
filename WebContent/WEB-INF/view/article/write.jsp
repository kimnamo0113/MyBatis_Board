<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">

</style>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/common.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script src="${pageContext.request.contextPath }/js/common.js"></script>
<script type="text/javascript">
	$(function() {
		$("#f1").submit(function() {
			$(".error").css("css","black");
			
			if(checkInputEmpty($("input[name]"))==false){
				return false;
			}
		})
	})
</script>
</head>
<body>
	<form action="${pageContext.request.contextPath }/article/write.do" method="post" id="f1">
		<label>제목:</label><br>
		<input type="text" name="title">
		<span class="error">제목을 입력하세요</span>
		<br>
		<label>내용:</label><br>
		<textarea name="content" rows="10" cols="30"></textarea>
		<input type="submit">
	</form>
</body>
</html>