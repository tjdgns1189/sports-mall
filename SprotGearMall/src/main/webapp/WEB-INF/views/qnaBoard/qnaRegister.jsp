<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ include file="/WEB-INF/views/includes/header.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글 작성 페이지</title>
</head>
<body>
	<h2>글 작성 페이지</h2>
	<form action="register" method="POST">
		<div>
			<p>제목 : </p>
			<input type="text" name="qnaBoardTitle" placeholder="제목 입력" required>
		</div>
		<div>
			<p>작성자 : </p>
			<input type="text" name="memberId" value="${pageContext.request.userPrincipal.name}" readonly="readonly">
		</div>
		<div>
			<p>내용 : </p>
			<textarea rows="20" cols="120" name="qnaBoardContent" placeholder="내용 입력"></textarea>
		</div>
		<div>
			<input type="submit" value="등록">
		</div>
	</form>

</body>
</html>