<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="/WEB-INF/views/includes/header.jsp" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
		<c:forEach var="vo" items="${list }">
					<div class="col mb-5">
						<div class="card h-100" onclick="location.href='detail?productId=${vo.productId}'">
							<!-- 상품 이미지-->
							<img class="card-img-top"
								src="<c:url value="/resources/img/product1.webp" />" alt="이미지" />
							<div class="text-center">
								<!-- 상품 이름-->
								<span class="fw-bolder">${vo.productName}</span><br>
								<!-- 가격들어가는곳-->
								${vo.productPrice } 원
							</div>
						</div>
					</div>
				</c:forEach>
</body>
</html>