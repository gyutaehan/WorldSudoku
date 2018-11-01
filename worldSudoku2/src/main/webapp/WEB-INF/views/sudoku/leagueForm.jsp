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

<script type="text/javascript">
	function writeBtn() {
		location.href = "writeBoardForm";
	}
	
	function tableClick(boardSeq){
		location.href = "boardDetailForm?boardSeq="+boardSeq;
	}
</script>

</head>
<body>
	<h1>대회 게시판</h1>
	<table>
		<tr>
			<th>게시판번호</th>
			<th>제목</th>
			<th>작성자</th>
			<th>조회수</th>
		</tr>

		<c:forEach var="board" items="${boardList}">
			<tr onclick="tableClick(${board.boardSeq})">
				<td>${board.boardSeq}</td>
				<td>${board.title}</td>
				<td>${board.userId}</td>
				<td>${board.hitcount}</td>
			</tr>
		</c:forEach>
		<tr>
			<td colspan="4"><input type="button" value="글쓰기( 관리자 )"
				onclick="writeBtn()">
				<a href="/sudoku"><input type="button" value="이전"></a></td>
		</tr>

	</table>


</body>
</html>