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

<a href="../qnaBoard/qnaBoard">qna게시판</a>
<a href="register"><input type="button" value="상품 등록"></a>
<hr>
<h1>상품목록리스트</h1>
	<table>
    	<thead>
    		<tr>
    		<!--  상품 헤드 -->
    			<th style="width : 60px">번호</th>
    			<th style="width : 100px">이름</th>
    			<th style="width : 100px">가격</th>
    			<th style="width : 100px">재고</th>
    			<th style="width : 100px">제조사</th>
    			<th style="width : 100px">이미지</th>
    			<th style="width : 100px">분류</th>
    		</tr>
    	</thead>
    	<tbody>
    	
    	<!-- 상품 목록 -->
    		<c:forEach var="vo" items="${list }"> 
    			<tr>
    				<td>${vo.productId }</td>
    				<td>${vo.productPrice }</td>
    				<td><a href="detail?productId=${vo.productId }">${vo.productName }</a></td>
    				<td>${vo.productStock }</td>
    				<td>${vo.productMaker }</td>
    				<td><img src="${vo.productImgPath }"></td>
    				<td>${vo.productCategory }</td>
    			</tr>
    		</c:forEach>
    	</tbody>
    </table>
	<ul>	
		<c:if test="${pageMaker.hasPrev }">
			<li><a href="list?page=${pageMaker.startPageNo - 1 }">이전</a></li>
		</c:if>
		<c:forEach begin="${pageMaker.startPageNo }" end="${pageMakger.endPageNo }" var="num">
		<li><a href="list?page=${num }">${num }</a></li>
		</c:forEach>
		<c:if test="${pageMaker.hasNext }">
			<li><a href="list?page=${pageMaker.endPageNo + 1 }">다음</a></li>
		</c:if>
	</ul>


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
				<div
					class="row gx-4 gx-lg-5 row-cols-2 row-cols-md-3 row-cols-xl-4 justify-content-center">
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
				</div>
			</div>

		</section>
	<input type="hidden" id="insertAlert" value="${insert_result }">

	<script type="text/javascript">
		var result = $('#insertAlert').val();
		if (result == 'success') {
			alert('새 상품 등록 성공!');
		}
	</script>

</body>
</html>