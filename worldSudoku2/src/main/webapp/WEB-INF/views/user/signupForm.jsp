<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
</head>
<body>
	<h1>회원가입</h1>

	<form action="signup" method="post">
		<table border="1">
			<tr>
				<td>이메일</td>
				<td><input type="text" name="userId"></td>
			</tr>
			<tr>
				<td>비밀번호</td>
				<td><input type="password" name="password"></td>
			</tr>
			<tr>
				<td>비밀번호 확인</td>
				<td><input type="password" name="password2"></td>
			</tr>
			<tr>
				<td colspan="2">
					<input type="submit" value="회원가입">
					<input type="button" value="뒤로가기">
				</td>
			</tr>
		</table>
	</form>
</body>
</html>