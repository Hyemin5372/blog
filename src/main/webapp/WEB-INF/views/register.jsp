<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous">
<style type="text/css">

</style>
</head>
<body>
	<form method="post" action="registerOk">
    <div class="container">
	<h1 align="center">회원가입</h1>
        <div class="form-group">
            <label for="inputId">아이디</label>
            <input type="text" class="form-control" id="inputId" name="user_id" placeholder="사용자 아이디">
        </div>
        <div class="form-group">
            <label for="inputName">이름</label>
            <input type="text" class="form-control" id="inputName" name="username" placeholder="사용자 이름">
        </div>
        <div class="form-group">
            <label for="inputEmail">이메일</label>
            <input type="text" class="form-control" id="inputEmail" name="email" placeholder="사용자 이메일">
        </div>
        <div class="form-group">
            <label for="inputPassword">비밀번호</label>
            <input type="password" class="form-control" id="inputPassword" name="password" placeholder="사용자 비밀번호">
        </div>
<!--         <div class="form-group"> -->
<!--             <label for="inputRePassword">비밀번호 확인</label> -->
<!--             <input type="password" class="form-control" id="inputPassword" name="password" placeholder="비밀번호 확인"> -->
<!--         </div> -->
        <button type="submit" class="btn btn-primary">가입 완료</button>
    </div>
</form>

</body>
</html>
</body>
</html>
