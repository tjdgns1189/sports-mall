<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html; charset=UTF-8" %>

<html>
<head>
<meta charset="UTF-8">
	<title>Home</title>
</head>
<body>
<h1>
	메인페이지
</h1>
<sec:authentication property="principal.username"/>
<P>  The time on the server is ${serverTime}. </P>
<sec:authorize access="isAuthenticated()">
    로그인한 사용자: <sec:authentication property="username"/>
    <a href="member/logout">로그아웃</a>
	<a href="member/info?memberId=" >마이페이지</a>
</sec:authorize>
<a href="member/loginForm">로그인</a>
<a href="member/register">회원가입</a>
<!-- 변수넣기전 -->
</body>
</html>
