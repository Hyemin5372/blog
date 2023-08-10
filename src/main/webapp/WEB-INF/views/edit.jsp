<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous">
</head>
<body>
<section>
<h1>게시글 수정</h1>
<!-- <form action="edit" method="GET"> -->
<form action="modifyboard" method="POST">
    <input type="hidden" name="board_id" value="${board.board_id}">
    <input type="text" name="title" value="${board.title}" class="form-control" placeholder="제목을 입력해주세요 "><br>
    <div class="form-floating">
        <textarea name="content" class="form-control" placeholder="Leave a comment here" id="floatingTextarea2" style="height: 100px">${board.content}</textarea>
        <label for="floatingTextarea2">내용</label>
    </div><br>
    <button type="submit" class="btn btn-outline-secondary">완료</button>
    <button onclick="history.back()" type="button" class="btn btn-outline-secondary">작성 취소</button>
</form>

</section>
</body>
</html>












