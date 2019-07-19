<%@page import="kr.yi.board.model.Article"%>
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
	td{
		border: 1px solid black;
	}
</style>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script type="text/javascript">
	$(function() {
		$("#delete").click(function() {
			var value=confirm("정말 삭제할래요?");
			if(value==true){
				location.href="${pageContext.request.contextPath }/article/delete.do?no=${article.article_no}";
			}
		})
		
		$("#modify").click(function() {
			var value=confirm("정말 수정할래요?");
			if(value==true){
				location.href="${pageContext.request.contextPath }/article/modify.do?no=${article.article_no}";
			}
			
		})
	})
</script>
</head>
<body>
	<table>
		<tr>
			<td>번호</td>
			<td>${article.article_no}</td>
		</tr>
		<tr>
			<td>작성자</td>
			<td>${article.writer_name }</td>
		</tr>
		<tr>
			<td>제목</td>
			<td>${article.title }</td>
		</tr>
		<tr>
			<td>내용</td>
			<td>${ac.content }</td>
		</tr>
		<tr>
			<td colspan="2">
				<a href="list.do?page=${page }">목록</a>
				<c:if test="${Auth.id == article.writer_id }">
					<a href="#" id="modify">수정</a>
					<a href="#" id="delete">삭제</a>
				</c:if>
			</td>
		</tr>
	</table>
</body>
</html>