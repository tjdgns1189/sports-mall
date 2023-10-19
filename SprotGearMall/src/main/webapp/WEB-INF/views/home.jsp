<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@ page contentType="text/html; charset=UTF-8" %>


<html>
<head>
<meta charset="UTF-8">
	<title>Home</title>
</head>
<body>
<h1>
	Hello world! 
</h1>

<P>  The time on the server is ${serverTime}. </P>
<a href="member/loginForm">로그인</a>
<a href="member/register">회원가입</a>
</body>
</html>
