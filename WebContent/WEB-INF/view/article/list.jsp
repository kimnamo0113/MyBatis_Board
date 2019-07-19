<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
	table{
		border-collapse: collapse;
	}
	tr:first-child td{
		text-align: right;
		color:blue;
	}
	
	th{
		border: 1px solid black;
	}
	td{ 
		border:1px solid black;
	}
	td:nth-child(2){
		display:inline-block;
		width:350px;
		white-space:nowrap; 
		overflow: hidden;
		text-overflow: ellipsis;
	}
	.page_wrap a{
		text-decoration: none;
		color:black;
		width:30px;
		display:inline-block;
		text-align: center;
	}
	.page_wrap a:hover{
		text-decoration: underline;
	} 
	
	.page_wrap a.page2{
		width:50px;
	}
	.active{
		background: pink;
		border-radius: 10px;
	}
</style>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script type="text/javascript">
	$(function() {
		$(".article").click(function() {
			var index=$(".article").index(this);
			var no=$(".article").eq(index).children().eq(0).text();
			location.href="${pageContext.request.contextPath }"+"/article/read.do?no="+no+"&page=${articlePage.currentPage}";
		})  
	}) 
</script> 
</head> 
<body>
	<table>  
		<tr>
			<td colspan="4">
				<a href="${pageContext.request.contextPath }/article/write.do">[게시글 쓰기]</a>
			</td>
		</tr>
		<tr>
			<th>번호</th>
			<th>제목</th>
			<th>작성자</th>
			<th>조회수</th> 
		</tr>
		<c:forEach var="article" items="${articlePage.content}">
		<tr class="article">
			<td>${article.article_no }</td>
			<td>${article.title }</td>
			<td>${article.writer_name }</td>
			<td>${article.read_cnt }</td>
		</tr>
		</c:forEach>
		<tr>
			<td colspan="4" class="page_wrap">
				<c:if test="${articlePage.startPage > 10 }">
					<a href="list.do?page=${articlePage.startPage-1}" class="page2">[이전]</a>
				</c:if>
				<c:forEach var="pNo" begin="${articlePage.startPage }" end="${articlePage.endPage }">
				<c:if test="${pNo==articlePage.currentPage }">
					<a href="list.do?page=${pNo }" class="active">${pNo }</a>
				</c:if> 
				<c:if test="${pNo!=articlePage.currentPage }">
					<a href="list.do?page=${pNo }">${pNo }</a>
				</c:if>
				</c:forEach>
				<c:if test="${articlePage.endPage < articlePage.totalPage }">
					<a href="list.do?page=${articlePage.endPage+1 }" class="page2">[다음]</a>
				</c:if>
			</td>
		</tr>
	</table>
	
</body>
</html>