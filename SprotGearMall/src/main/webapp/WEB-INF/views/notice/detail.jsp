<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="/WEB-INF/views/includes/header.jsp" %>
    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<body>
	공지사항 제목 : ${vo.noticeTitle }<br>
	공지사항 본문 : ${vo.noticeContent }<br>
	공지사항 시간 : ${vo.noticeCreatedDate }
	
	<a href="update?noticeId=${vo.noticeId }"><button>수정</button></a>
	<form action="delete" method="POST">
	<input type="hidden" name="noticeId" value="${vo.noticeId }">
	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
	<input type="submit" value="삭제">
	</form>
</body>
</html>