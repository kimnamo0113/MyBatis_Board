<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">

</style>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script type="text/javascript">
	$(function() {
		$("#btn1").click(function() {
			$.ajax({ 
				url:"json1.do",
				type:"post",
				data:{"no":$("#no").val()},
				dataType:"json",
				success:function(res){ 
					//번호,작성자 아이디, 작성자 이름, 제목, 생성시간
					var date=new Date(res.regdate);
					$("#result").append(res.article_no);
					$("#result").append(res.writer_id);
					$("#result").append(res.writer_name);
					$("#result").append(res.title);
					$("#result").append(date.getFullYear()+"년"+date.getMonth()+"월"+date.getDate()+"일");                          
				}
			})
		})
		$("#btn2").click(function() {
			$.ajax({ 
				url:"json2.do",
				type:"get",
				data:{"page":$("#page").val()},
				dataType:"json",
				success:function(res){ 
					console.log(res);
						
					$(res.content).each(function(i,obj) {
						var str="<li>";
						str+=obj.article_no;
						str+=obj.writer_id;
						str+=obj.writer_name;
						str+=obj.title;
						str+=obj.regdate;
						str+=obj.moddate;1
						str+=obj.read_cnt;
						str+="</li>"
						$("#list").append(str);
					})
					
					
				}
			})
		})
	})
</script>
</head>
<body>
	<h1>게시글 상세 보기 데이터 가져오기</h1>
	<input type="text" id="no">
	<button id="btn1">가져오기</button>
	<div id="result">
	</div>
	
	<hr>
	<h1>게시글 리스트</h1>
	페이지 번호 : <input type="text" id="page">
	<button id="btn2">리스트 가져오기</button>
	<ul id="list">
		
	</ul>
</body>
</html>