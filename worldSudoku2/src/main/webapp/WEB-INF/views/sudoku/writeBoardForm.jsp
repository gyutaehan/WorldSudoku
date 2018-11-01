<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1> 글쓰기 ( 관리자 ) </h1>
<form action="writeBoard" method="post">
<table border="1">
<tr>
<td>제목</td>
<td><input type="text" name="title"/></td>
</tr>
<tr>
<td>내용</td>
<td><textarea rows="20" cols="100" name="content"></textarea></td>
</tr>

<tr>
<td colspan="2"><input type="submit" value="글쓰기"/><input type="button" value="취소"/></td>
</tr>
</table>
</form>
</body>
</html>