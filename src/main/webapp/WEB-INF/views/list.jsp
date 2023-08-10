<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- 페이징 세로로 된거 가로로 변경 -->
   <style type="text/css">
      .div_page ul{
         display: flex;
         list-style: none;
      }
      .boardlist{
      border: 1px solid #eee;
      
      }
      #writebutton{
      float: right;
      margin: 20px 0;
      }
      
   </style>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous">
</head>
<body>

<jsp:include page="common/nav.jsp" />

	<script type="text/javascript">
	function goToUserPage(userId) {
	    // PK 값을 사용하여 URL 생성
	    var url = 'modify?user_id=' + userId;
	
	    // 생성된 URL로 페이지 이동
	    window.location.href = url;
	  }
	function goToUserDelete(userId) {
	    // PK 값을 사용하여 URL 생성
	    var url = 'delete?user_id=' + userId;
	
	    // 생성된 URL로 페이지 이동
	    window.location.href = url;
	  }
	</script>
<button type="submit" class="btn btn-primary" id="modifyInfo" onclick="goToUserPage(${user_id})"
        style="--bs-btn-padding-y: .25rem; --bs-btn-padding-x: .5rem; --bs-btn-font-size: .75rem; background-color: #F8F8FF;  border: 1px solid #eee;  float: right; color: black;">
  개인정보 수정
</button><br>
<button id="writebutton" type="button" class="btn btn-secondary btn-sm" onclick="location.href='write'">글쓰기</button>
<table class="table table-responsive table-borderless">
	<thead>
		<tr>
<!-- 			<th style="width: 20%">번호</th> -->
			<th style="width: 80%">제목</th>
			

		</tr>
	</thead>
	<tbody>
	<c:forEach items="${list}" var="dto">
		<tr class="boardlist"> 
<%-- 			<td>${dto.board_id}</td> --%>
			<td><a class="move_link" href="read?board_id=${dto.board_id}">${dto.title}</a></td>
			
		</tr>
	</c:forEach>
	</tbody>
</table>
<!--    페이징처리 -->
<div class="div_page">

<!-- <nav aria-label="Page navigation example"> -->
<!--   <ul class="pagination"> -->
<%--   <c:if test="${pageMaker.prev}"> --%>
<!--     <li class="page-item"> -->
<%--       <a class="page-link" href="${pageMaker.startPage - 1}" aria-label="Previous"> --%>
<!--         <span aria-hidden="true">&laquo;</span> -->
<!--       </a> -->
<!--     </li> -->
<%--   </c:if> --%>
<%--   <c:forEach var="num" begin="${pageMaker.startPage}"   end="${pageMaker.endPage}"> --%>
<%--     <li class="page-item"><a class="page-link" href="${num}">${num}</a></li> --%>
<%--     </c:forEach> --%>
<%--     <c:if test="${pageMaker.next}"> --%>
<!--     <li class="page-item"> -->
<%--       <a class="page-link" href="${pageMaker.endPage + 1}" aria-label="Next"> --%>
<!--         <span aria-hidden="true">&raquo;</span> -->
<!--       </a> -->
<!--     </li> -->
<%--     </c:if> --%>
<!--   </ul> -->
<!-- </nav> -->
      <ul>
         <c:if test="${pageMaker.prev}">
            <li class="paginate_button">
<!--             시작페이지 -1 하면 이전의 10개 페이지 표시  -->
<!--              ex>11=>10(1~10), 21=>20(11~20) -->
               <a href="${pageMaker.startPage - 1}">
                  [이전]
               </a>
            </li>
         </c:if>      
         <c:forEach var="num" begin="${pageMaker.startPage}"   end="${pageMaker.endPage}">
<!--             페이지번호 색깔주기(빨간색) -->
<%--              <li ${pageMaker.cri.pageNum == num ? "style='color:red'":""}>  --%>
<!--             페이지번호 배경색깔주기(노란색) -->
   <div class="btn-group me-2" role="group" aria-label="Second group">
            <li class="paginate_button" ${pageMaker.cri.pageNum == num ? "style='background-color:yellow'":""}>
<!--                클릭한 현재페이지 번호를 링크로 연결 -->
               <a href="${num}">
                  [${num}]   
               </a>
               </div> 
            </li>
         </c:forEach>
         <c:if test="${pageMaker.next}">
            <li class="paginate_button">
<!--              끝페이지 +1 하면 이후의 10개 페이지 표시  -->
<!--             ex>10=>11(11~20), 20=>21(21~30) -->
               <a href="${pageMaker.endPage + 1}">
                  [다음]
               </a>
            </li>
         </c:if>   
      </ul>
   </div>
   
   <form method="get" id="actionForm" action="#">
      <input type="hidden" name="pageNum" value="${pageMaker.cri.pageNum}">
      <input type="hidden" name="amount" value="${pageMaker.cri.amount}">
   </form>
</body>
</html>
<!-- 제이쿼리 -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script>
//회원정보 수정
function goToUserPage(userId) {
	var userId = "${user.user_id}"; 
    // PK 값을 사용하여 URL 생성
    var url = 'modify?user_id=' + userId;

    // 생성된 URL로 페이지 이동
    window.location.href = url;
  }

  
  //페이징
   var actionForm = $("#actionForm");
      
//    페이지 번호 처리
   $(".paginate_button a").on("click", function(e) {
//       기본 동작 막음:페이지 링크를 통해서 이동
      e.preventDefault();
//       console.log("click~!!!");
//       find("a")해도됨
//       console.log("@# href ===>"+$(this).find("a").attr("href"));
      console.log("@# href ===>"+$(this).attr("href"));
      actionForm.find("input[name='pageNum']").val($(this).attr("href"));
//       actionForm.submit();>>누르면 페이지 이동
      actionForm.submit();
   });
   </script>

<jsp:include page="common/footer.jsp" />
</body>
</html>





