<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/includes/header.jsp"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	내가 문의 한 내용 출력할곳
	<div>
	<c:forEach var="qna" items="${list }">
		문의 번호 : ${qna.prdQnaId }<br>
		문의 상태 : ${qna.prdQnaState }<br>
		문의 작성자 : ${qna.memberId }<br>
		문의 시간 : ${qna.prdQnaCreatedDate }<br>
		문의 내용 : ${qna.prdQnaContent }<br>
		비밀글 여부 : ${qna.prdQnaSecret }<br>
		<hr>
	</c:forEach>
	</div>

	
</body>
</html>