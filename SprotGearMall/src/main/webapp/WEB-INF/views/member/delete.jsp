<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
     <%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
		 <h2><a href="/mall/index">회원탈퇴</a></h2>
		<c:if test="${not empty error}"> 
       		<div class="alert alert-danger" role="alert">비밀번호가 맞지 않습니다</div>
       </c:if>
		<form action="delete" method="post">
		<input type="hidden" name="memberId" id ="memberId" value="${pageContext.request.userPrincipal.name}" required="required" placeholder="아이디"><br>
		<p><input type="password" id="password" name="password" class="form-control" placeholder="비밀번호확인" required="required"></p>
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
		<button type="submit">회원탈퇴</button>
		</form>
		<a href="/mall/index"><button type="submit" >취소</button></a>
</body>
</html>