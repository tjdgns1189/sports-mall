<%@page import="org.springframework.security.core.Authentication"%>
<%@page import="org.springframework.security.core.context.SecurityContext"%>
<%@page import="org.springframework.security.core.context.SecurityContextHolder"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html; charset=UTF-8" %>
	<%@ include file="/WEB-INF/views/includes/header.jsp" %>



<!DOCTYPE html>

<html>
<head>
<meta charset="UTF-8">
	<title>Home</title>
</head>
<body>


<h1>
	실험용  예비 페이지
</h1>


<button type="button" class="btn btn-primary position-relative">
  Profile
  <span class="position-absolute top-0 start-100 translate-middle p-2 bg-danger border border-light rounded-circle">
  </span>
</button>
<br>
visually-hidden:이거 사용하면 배지 숨길수 있음
position-absolute: 위치 절대적위치로 배치<br>
top-0:가장자리 상단에 배치<br>
start-100:왼쪽 가장자리 에서 오른쪽으로 100퍼센트 떨어진 위치<br>
translate-middle: 부모요소 중앙정렬<br>
p-2:모든방향 패딩 부트스트랩 p-2만큼<br>
bg-danger:색을 danger색으로<br>
border:테두리 추가<br>
rounded-circle : 배지를 둥글게<br>
visually-hidden: 배지안 글자 숨김
<br><br><hr>

임시 이미지 업로드
<form method="POST" action="upload" enctype="multipart/form-data">
    <input type="file" name="file" />
  	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    <button type="submit">Upload</button>
</form>




<P>  The time on the server is ${serverTime}. </P>
<sec:authorize access="isAuthenticated()">
    로그인한 사용자: <sec:authentication property="principal.username"/>
    <c:forEach var="authority" items="${authorities}">
    	${authority.authority}<br/>
	</c:forEach>
     <c:url var="logoutUrl" value="/logout"/>
      <form class="form-inline" action="${logoutUrl}" method="post">
          <input type="submit" value="Log out" />
          <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
      </form>
	<a href="member/mypage" >마이페이지</a>
</sec:authorize>
<sec:authorize access="isAnonymous()">
<a href="member/loginForm">로그인</a>
<a href="member/register">회원가입</a>
</sec:authorize>
<div id="carouselExampleIndicators" class="carousel slide" data-bs-ride="carousel">
  <!-- Indicators -->
  <div class="carousel-indicators">
    <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="0" class="active" aria-current="true" aria-label="Slide 1"></button>
    <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="1" aria-label="Slide 2"></button>
    <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="2" aria-label="Slide 3"></button>
  </div>

  <!-- Wrapper for slides -->
  <div class="carousel-inner">
    <div class="carousel-item active">
      <img src="<c:url value="/resources/img/slid1.jpg" />" class="d-block w-100" alt="...">
    </div>
    <div class="carousel-item">
      <img src="<c:url value="/resources/img/slid2.jpg" />" class="d-block w-100" alt="...">
    </div>
    <div class="carousel-item">
      <img src="<c:url value="/resources/img/slid3.jpg" />" class="d-block w-100" alt="...">
    </div>
  </div>

  <!-- Controls -->
  <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide="prev">
    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
    <span class="visually-hidden">Previous</span>
  </button>
  <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide="next">
    <span class="carousel-control-next-icon" aria-hidden="true"></span>
    <span class="visually-hidden">Next</span>
  </button>
</div>



<footer>
    <%@ include file="/WEB-INF/views/includes/footer.jsp" %>
</footer>
</body>
</html>
