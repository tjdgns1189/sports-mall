<%@ page session="false" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="/WEB-INF/views/includes/header.jsp" %>


<!DOCTYPE html>

<html>
<head>
<link href="<c:url value="/resources/css/product.css" />" rel="stylesheet">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />

<meta charset="UTF-8">

	<title>Home</title>

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
				<div
					class="row gx-4 gx-lg-5 row-cols-2 row-cols-md-3 row-cols-xl-4 justify-content-center">
					<c:forEach var="vo" items="${list }">
					<div class="col mb-5">
						<div class="card h-100" onclick="location.href='${pageContext.request.contextPath}/product/detail?productId=${vo.productId}'">
						
							<!-- 상품 이미지-->
							<img class="card-img-top"
								src="https://storage.googleapis.com/edu-mall-img/${vo.productImgPath }" alt="이미지" />
							<div class="text-center">
								<!-- 상품 이름-->
								<span class="fw-bolder">${vo.productName}</span><br>
								<!-- 가격들어가는곳-->
                			<fmt:formatNumber value="${vo.productPrice}" type="number" pattern="#,###"/>원
							</div>
						</div>
					</div>
				</c:forEach>
				</div>
			</div>

		</section>
		<ul>
		<c:if test="${pageMaker.hasPrev }">
		    <li class="page-item">
      	<a class="page-link" href="${pageMaker.startPageNo - 1 }" aria-label="Previous">
       	 <span aria-hidden="true">&laquo;</span>
     	 </a>
    	</li>
		</c:if>
		<c:forEach begin="${pageMaker.startPageNo }" end="${pageMakger.endPageNo }" var="num">
		<li><a href="list?page=${num }">${num }</a></li>
		</c:forEach>
		<c:if test="${pageMaker.hasNext }">
		<li class="page-item">
      		<a class="page-link" href="list?page=${pageMaker.endPageNo + 1 }" aria-label="Next">
        <span aria-hidden="true">&raquo;</span>
     		 </a>
		</c:if>
	</ul>

	<input type="hidden" id="insertAlert" value="${insert_result }">

	<script type="text/javascript">
		var result = $('#insertAlert').val();
		if(result == 'success') {
			alert('새 상품 등록 성공!');
		}
	</script>


<footer>
    <%@ include file="/WEB-INF/views/includes/footer.jsp" %>
</footer>
</body>
</html>

