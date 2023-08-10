<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<style>
#loginForm{
	padding: 20px;
	text-align: center;	
	border-radius: 10px;
	cursor: pointer;
	transition: all 0.3s;
	width: 90%;
	background-color: #FFFAFA;
}
</style>
<meta charset="UTF-8">
<title>Insert title here</title>
 <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous">
</head>
<body>
	<div class="logo" align="center">
		<h1>회원 로그인</h1>
	</div>
	
	<div class="form-wrapper">
		<form id="loginForm" action="login_yn" method="POST">
			<div class="form-floating mb-3">
				<input name="username" type="text" class="form-control" id="floatingInput" placeholder="아이디"> 
				<label for="floatingInput">이름</label>
			</div>
			<div class="form-floating">
				<input name="password" type="password" class="form-control" id="floatingPassword" placeholder="비밀번호"> 
				<label for="floatingPassword">비밀번호</label><br>
			</div>
			<div class="btns">
		<button id="login" type="submit" class="btn btn-secondary">로그인</button>
	</div>
	<a href="register">회원가입</a>
		</form>
	</div>
	
</body>
</html>
