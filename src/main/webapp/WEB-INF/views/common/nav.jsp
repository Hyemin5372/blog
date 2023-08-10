<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
	/* CSS */
.navbar {
  background-color: #333; /* 네비바 배경색 설정 */
  color: #fff; /* 네비바 텍스트 색상 설정 */
  height: 50px; /* 네비바 높이 설정 */
}

.navbar-container {
  display: flex;
  align-items: center;
  justify-content: space-around;
  height: 100%;
}

.navbar a {
  color: #fff; /* 링크 색상 설정 */
  text-decoration: none; /* 링크 밑줄 제거 */
  padding: 10px; /* 링크 주변 여백 설정 */
}
	
</style>
</head>
<body>
	<!-- HTML -->
<nav class="navbar">
  <div class="navbar-container">
    <a href="#">게시판</a>
  </div>
</nav>
	
</body>
</html>