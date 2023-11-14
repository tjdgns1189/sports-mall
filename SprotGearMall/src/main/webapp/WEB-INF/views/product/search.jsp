<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/includes/header.jsp"%>

<!DOCTYPE html>
<html>
<head>
<meta name="viewport"
content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<meta name="description" content="" />
<meta name="author" content="" />
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="<c:url value="/resources/css/products.css" />" rel="stylesheet">
</head>
<body>

	<!-- Header-->
	<header class="bg-dark py-5">
		<div class="container px-4 px-lg-5 my-5">
			<div class="text-center text-white">
				<h1 class="display-4 fw-bolder">메인페이지</h1>
				<p class="lead fw-normal text-white-50 mb-0">이미지랑 글자 넣기</p>
			</div>
		</div>
	</header>

	<!-- Section-->
	<section class="py-5"><h3>&nbsp;신상품</h3><hr>
			<div class="container px-4 px-lg-5 mt-5">
				<div class="row gx-4 gx-lg-5 row-cols-2 row-cols-md-3 row-cols-xl-4 justify-content-center">
					<c:forEach var="vo" items="${list }">
					<div class="col mb-5">
						<div class="card h-100" onclick="location.href='detail?productId=${vo.productId}'">
							<!-- 상품 이미지-->
							<img class="card-img-top"
								src="https://storage.googleapis.com/edu-mall-img/${vo.productImgPath }"  alt="이미지" />
							<div class="text-center">
								<!-- 상품 이름-->
								<span class="fw-bolder">${vo.productName}</span><br>
								<!-- 가격들어가는곳-->
								${vo.productPrice } 원
							</div>
						</div>
					</div>
				</c:forEach>
				</div>
			</div>
		</section>

</body>
</html>