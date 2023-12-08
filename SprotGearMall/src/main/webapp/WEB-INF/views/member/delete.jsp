<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원탈퇴</title>
<link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/css/bootstrap.min.css" 
 rel="stylesheet" integrity="sha384-/Y6pD6FV/Vv2HJnA6t+vslU6fwYXjCFtcEpHbNJ0lyAFsXTsjBbfaDjzALeQsN6M" crossorigin="anonymous">
<link href="https://getbootstrap.com/docs/4.0/examples/signin/signin.css" 
 rel="stylesheet" crossorigin="anonymous">
</head>
<body>
<header>
<%@ include file="/WEB-INF/views/includes/header.jsp" %>
</header>
	<div class="container">
    <div class="row justify-content-center">
        <div class="col-md-6">
            <h2 class="text-center mb-4"><a href="/mall/index">회원탈퇴</a></h2>
            <c:if test="${not empty param.error}"> 
                <div class="alert alert-danger" role="alert">비밀번호가 틀렸습니다</div>
            </c:if>
            <form action="delete" method="post" class="mb-3">
                <input type="hidden" name="memberId" id="memberId" value="${pageContext.request.userPrincipal.name}">
                <div class="form-group">
                    <input type="password" id="password" name="password" class="form-control" placeholder="비밀번호 확인" required="required">
                </div>
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                <button type="submit" class="btn btn-primary btn-block">회원탈퇴</button>
            </form>
            <a href="/mall" class="btn btn-secondary btn-block">취소</a>
        </div>
    </div>
</div>
<footer>
    <%@ include file="/WEB-INF/views/includes/footer.jsp" %>
</footer>
</body>
</html>