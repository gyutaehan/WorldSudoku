<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style>
table {
	border-collapse: collapse;
	width: 100%;
}

th, td {
	text-align: left;
	padding: 8px;
}

tr:nth-child(even) {
	background-color: #f2f2f2;
}
</style>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
</head>
<body>
	<h1>당신의 포인트 랭킹은 ${index+1}위 !!</h1>

	<table border="1">
		<tr>
			<td>난이도</td>
			<td>아이디</td>
			<td>시간</td>
		</tr>
		<tr>
			<td>쉬움</td>
			<td>${recordList[0].userId}</td>
			<td>${recordList[0].userRecord}</td>
		</tr>
		<tr>
			<td>일반</td>
			<td>${recordList[1].userId}</td>
			<td>${recordList[1].userRecord}</td>
		</tr>
		<tr>
			<td>어려움</td>
			<td>${recordList[2].userId}</td>
			<td>${recordList[2].userRecord}</td>
		</tr>
	</table>
	
	<table>
		<c:forEach var="member" items="${memberList}">
			<tr>
				<td>${member.userId}</td>
				<td>${member.point}</td>
			</tr>
		</c:forEach>
	</table>
	<div align="left">
		<a href="/sudoku/"><button type="button" class="btn btn-primary btn-sm">이전버튼</button></a>
	</div>

</body>
</html>