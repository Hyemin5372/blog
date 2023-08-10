<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous">
</head>
<body>

	<div class="content-wrapper">	
		<div class="content-header">
			<div class="user-icon"></div>
			<p style="margin-right: 20px;"><b>제목:${board.title}</b></p>
			<div class="content-body" style="border: none;background: white;color: grey; height: 100px">${board.content}</div><hr>
			</div>
			</div>
			<c:if test="${isCurrentUser}">
<!-- 			<button type="submit" class="btn btn-secondary" onclick="location.href='edit'">수정</button>&nbsp;&nbsp; -->
			<button type="submit" class="btn btn-secondary" onclick="goToEditPage(${board.board_id})">수정</button>&nbsp;&nbsp;
			<button type="button" class="btn btn-secondary" onclick="goToDeletePage(${board.board_id})">삭제</button>

			</c:if>
<!-- 		                     <hr>		       -->
		                    
							  <div class="content-footer"></div>
							
		                  <div style="display: flex; justify-content: space-between; width: 100%; margin-top: 50px;">
		                     <div class="form-floating" style="width:90%">
								  <textarea class="form-control" placeholder="Leave a comment here" id="ContentComment" style="height: 100px"></textarea>
								  <label for="floatingTextarea2">댓글</label>
							</div>
		                       <button id="comment-write" style="width: auto; background-color: gray; border:  1px solid #eee;" class="btn btn-primary" type="button">완료</button>
		                    </div>
		
		<div class="comment"></div>
<script type="text/javascript">
  
  function goToEditPage(boardId) {
    // PK 값을 사용하여 URL 생성
    var url = 'edit?board_id=' + boardId;

    // 생성된 URL로 페이지 이동
    window.location.href = url;
  }
  function goToDeletePage(boardId) {
    // PK 값을 사용하여 URL 생성
    var url = 'delete?board_id=' + boardId;

    // 생성된 URL로 페이지 이동
    window.location.href = url;
  }
  //댓글
  var board_id =${board.board_id}
  var user_id=${board.user_id}
   
  
</script>
<!-- 	<script src="js/jquery.min.js"></script> -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<!-- 	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script> -->
	 

</body>
<script src="resources/js/comment.js"></script>
</html>