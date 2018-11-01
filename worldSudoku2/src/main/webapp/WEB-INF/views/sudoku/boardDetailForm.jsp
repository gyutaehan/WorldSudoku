<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<table border="1">
<tr>
<td>글제목</td>
<td>${board.title}</td>
</tr>
<tr>
<td>작성자</td>
<td>${board.userId}</td>
</tr>
<tr>
<td>글내용</td>
<td><textArea cols="100" rows="40">${board.content}</textArea></td>
</tr>
<tr>
<td colspan="2"><button>확인</button></td>
</tr>
</table>

</body>
</html>