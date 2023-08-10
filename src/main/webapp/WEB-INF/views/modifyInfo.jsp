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
    <div class="container">
    <form id="modifyForm" action="modify" method="POST">
	<h1 align="center">회원정보 수정</h1>
        <div class="form-group">
            <label for="inputName">이름</label>
            <input type="text" class="form-control" id="inputName" name="username" value="${user.username}" placeholder="사용자 이름">
        </div>
        <div class="form-group">
            <label for="inputEmail">이메일</label>
            <input type="text" class="form-control" id="inputEmail" name="email" value="${user.email}" placeholder="사용자 이메일">
        </div>
        <div class="form-group">
            <label for="inputPassword">현재 비밀번호</label>
            <input type="password" class="form-control" id="inputPassword" name="password" placeholder="사용자 비밀번호">
        </div>
        <div class="form-group">
    <label for="newpassword">새 비밀번호</label>
    <input type="password" class="form-control" id="newpassword" name="newPassword" placeholder="새 비밀번호">
</div>
<div class="form-group">
    <label for="passwordConfirmation">새 비밀번호 확인</label>
    <input type="password" class="form-control" id="passwordConfirmation" name="passwordConfirmation" placeholder="새 비밀번호 확인">
</div>
 
        <button id="passwordChange" type="submit" class="btn btn-dark" >변경하기</button>

<!-- <a href="#" id="userdelete">회원 탈퇴</a> -->

        <a  href="deleteuser?user_id=${user.user_id}" id="userdelete">회원 탈퇴</a>
	</form>
    </div>

</body>
<script type="text/javascript">
$(document).ready(function() {
    $("#passwordChange").click(function() {
        var newPassword = $("#newpassword").val();
        var passwordConfirmation = $("#passwordConfirmation").val();

        if (newPassword !== passwordConfirmation) {
            alert("새 비밀번호와 비밀번호 확인이 일치하지 않습니다.");
            return;
        }

        var formData = $("#modifyForm").serialize();
        
        $.ajax({
            type: "POST",
            data: formData,
            url: "modify",
            success: function(data) {
                if (data === "ok") {
                    alert("수정 성공!!");
                } else {
                    alert("수정 실패...");
                }
            }
        });
    });

});
$(document).ready(function() {
    $("#userdelete").click(function(e) {
        // 링크 클릭 이벤트를 막음 (기본 동작 방지)
        e.preventDefault();

        // 확인창 띄우기
        var confirmDelete = confirm("정말로 회원 탈퇴하시겠습니까?");

        // 확인창에서 확인 버튼을 누른 경우
        if (confirmDelete) {
            // 회원 탈퇴 URL로 이동
            window.location.href = $(this).attr("href");
        }
    });
});


</script>






</html>


