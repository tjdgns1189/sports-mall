<%@page import="edu.spring.mall.domain.ProductVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<script src="https://code.jquery.com/jquery-3.7.1.js"></script>
<meta charset="UTF-8">
<title>${vo.productName }</title>
</head>
<body>
	<h2>상품 정보</h2>
	
	<div>
		<p>상품 번호 : ${vo.productId }</p>
	</div>
	
	<div>
		<p>상품 이름 : ${vo.productName } </p>
	</div>
	
	<div>
		<p>상품 가격 : ${vo.productPrice }</p>
	</div>
	
	<div>
		<p>상품 재고 : ${vo.productStock }</p>
	</div>
	
	<div>
		<p>상품 제조사 : ${vo.productMaker }</p>
	</div>
	
	<!-- 
	<div>
		<p>상품 이미지 : ${vo.productImgPath }</p>
	</div>
	 -->

	<div style="text-align">
		<button><a href="payment?productId=${vo.productId }">구매</button>
	</div>
	
	<a href="list?page=${page }"><input type="button" value="상품 목록"></a>
	<a href="update?productName=${vo.productName }&page=${page }"><input type="button" value="상품 수정"></a>
	<form action="delete" method="POST">
		<input type="hidden" id="productName" name="productName" value="${vo.productName }">
		<input type="submit" value="상품 삭제">
	</form>
	
</body>

</html>