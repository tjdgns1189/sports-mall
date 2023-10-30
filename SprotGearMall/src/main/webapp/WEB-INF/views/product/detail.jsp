<%@page import="edu.spring.mall.domain.ProductVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/includes/header.jsp"%>

<!DOCTYPE html>
<html>
<head>
<script src="https://code.jquery.com/jquery-3.7.1.js"></script>
<meta charset="UTF-8">
<title>${vo.productName }</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.0/font/bootstrap-icons.css" rel="stylesheet">
<link href="<c:url value="/resources/css/detail.css" />" rel="stylesheet">
<script src="<c:url value="/resources/js/detail.js" />"></script>
<script>
    var isLiked = ${isLiked}; 
</script>
</head>
<body>
	<div class="container">
		<div class="row">
			<!-- 이미지 들어가는곳 -->
			<div class="col-md-6">
				<div style="height: 400px;">
					<img src="<c:url value='/resources/img/product1.webp' />"
						alt="Product Image" class="img-fluid h-100">
				</div>
			</div>
			<!-- 물건 정보 -->
			<div class="col-md-6">
			<br>
				<h1>${vo.productName}</h1>
				<p class="h6 my-2">제조사 : ${vo.productMaker}</p>
				<hr>
				<p class="price h4 my-2">판매가 : ${vo.productPrice}</p><hr>
				
				<!-- 버튼 컨테이너 -->
				<div class="d-flex justify-content-between align-items-center mt-4 gx-2">
				<!-- 구매하기 버튼 -->
					<div class="col-6 px-0">
						<a href="payment?productId=${vo.productId}" class="btn btn-success btn-lg w-100">구매하기</a>
					</div>
					<!-- 좋아요 버튼 -->
					<div class="col-3 px-1">
						<button class="like-btn btn btn-outline-danger btn-lg w-100">
							<span class="heart"></span>
						</button>
					</div>
					<!-- 장바구니 버튼 -->
					<div class="col-3 px-1">
						<button class="btn btn-outline-primary btn-lg w-100"> <i class="bi bi-cart3"></i></button>
					</div>
				</div>
			</div>
		</div>
	</div>
   <sec:authorize access="hasRole('ROLE_ADMIN')">
	<a href="update?productName=${vo.productName }&page=${page }"><input type="button" value="상품 수정"></a>
	<a href="update?productId=${vo.productId }&page=${page }"><input type="button" value="상품 수정"></a>
	<form action="delete" method="POST">
		<input type="hidden" id="productId" name="productId" value="${vo.productId }">
		<input type="hidden" id="memberId" name="memberId" value="${pageContext.request.userPrincipal.name}">
		<input type="hidden" id="csrfToken" name="${_csrf.parameterName}" value="${_csrf.token}">
		<input type="submit" value="상품 삭제">
	</form>
</sec:authorize>
</body>
</html>