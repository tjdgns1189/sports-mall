<%@page import="edu.spring.mall.domain.ProductVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<%
        ProductVO vo = (ProductVO) request.getAttribute("vo");
    %>
	<hr>
	<h1>${vo.productName } 디테일창입니다.</h1>
	
	<div style="text-align">
		<button><a href="payment?productId=${vo.productId }">구매</button>
	</div>
</body>
</html>