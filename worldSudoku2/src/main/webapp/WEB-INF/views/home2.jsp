<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<title>sudoku</title>
<style type="text/css">
    body {
        text-align: center;
        color: #FFF;
        width: 900px;
    }
    div#wapper {
        width: 100%;
        text-align: left;
        min-height: 300px;
        margin: 0 auto;
    }
    header, footer, nav, aside, section {
        border: 1px solid #999;
        margin: 5px;
        padding: 10px;
    }
    header {
        height: 50px;
        background-color: red;
        
    }
    nav, section, aside {
        float: left;
        height: 200px;
    }
    nav {
        background-color: goldenrod;
        width: 100px;
    }
    section {
        background-color: green;   
        width: 604px;
    }
    aside {
        background-color: goldenrod;
        width: 100px;
    }
    footer {
        height: 50px;
        background-color: blue;
        position: relatiev;
        clear: both;
    }
    article {
        width: 90%;
        margin: 20px;
        background-color: #999;
    }
</style>


<script type="text/javascript">
	function loginBtn() {
		location.href = "loginForm";
	}

	function signupBtn() {
		location.href = "signupForm";
	}
	function userInfoBtn() {
		location.href = "userInfoForm";
	}
	function sudokuBtn() {
		location.href = "sudokuForm";
	}
	function rank() {
		location.href = "rankForm";
	}
	
	function leagueForm(){
		location.href = "leagueForm";
	}
</script>

</head>
<body>
	<div id="wapper">
		<!--헤더시작-->
		<header>
			<h1>hello SudokuWorld</h1>
		</header>
		<!--네비게이션-->
		<nav>
			<p>nav</p>
			<ul>
				<li><a href="">link</a></li>
				<li><a href="">link</a></li>
				<li><a href="">link</a></li>
			</ul>
		</nav>
		<!--콘텐츠부분-->
		<section>
			<p>section</p>
			<article>
				<p>article</p>
			</article>
		</section>
		<!--사이드바-->
		<aside>
			<p>aside</p>
		</aside>
		<!--풋터-->
		<footer>
		<div>
			made by hangyutae, trapkka997@gmail.com</br>
		</div>
		
		</footer>
	</div>



	<c:if test="${not empty sessionScope.userId}">
		<input type="button" value="개인정보" onclick="userInfoBtn()" />
		<a href="logout"><input type="button" value="로그아웃"></a>
		<input type="button" value="스도쿠퍼즐하기" onclick="sudokuBtn()" />
		<input type="button" value="랭킹보기" onclick="rank()" />
		<input type="button" value="대회" onclick="leagueForm()" />
	</c:if>
	<c:if test="${empty sessionScope.userId}">
		<input type="button" value="로그인" onclick="loginBtn()">
		<input type="button" value="회원가입" onclick="signupBtn()">
	</c:if>
</body>
</html>
