<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="/WEB-INF/views/includes/header.jsp" %>

<!DOCTYPE html>

<html>
<head>
<meta charset="UTF-8">
	<title>Home</title>

</head>
<body>



<h2>메인 페이지</h2>

	<sec:authorize access="isAuthenticated()">
    로그인한 사용자: <sec:authentication property="principal.username"/>
    	
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


<footer>
    <%@ include file="/WEB-INF/views/includes/footer.jsp" %>
</footer>
</body>
</html>
