<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
삭제버튼 제약조건 두기 전까지 하지말기
	<input type="hidden" id="memberId" value="${pageContext.request.userPrincipal.name}">
	<input type="hidden" id="csrfToken" name="${_csrf.parameterName}" value="${_csrf.token}"/>
	 <c:if test="${not empty list}">
	<div class="container mt-5">
		<c:forEach var="vo" items="${list }">
			<div class="row mb-3" onclick="location.href='../product/detail?productId=${vo.productId}'">
				<div class="col-md-2">
					<img src="<c:url value='/resources/img/product1.webp' />" class="img-fluid" alt="Product Image">
				</div>
				<div class="col-md-7">
					<h5>상품 명 : ${vo.productName}</h5>
					<p>제조사 : ${vo.productMaker}</p>
					<p>카테고리 : ${vo.productCategory }</p>
					<p>가격 : ${vo.productPrice}원</p>
					<p>재고 : ${vo.productStock }</p>
				</div>
				<div class="col-md-3">
					<button type="button" class="btn btn-danger btn-delete" data-product-id="${vo.productId}">삭제</button>
				</div>
				<hr>
			</div>
			
		</c:forEach>
	</div>
	</c:if>
	
</body>
</html>