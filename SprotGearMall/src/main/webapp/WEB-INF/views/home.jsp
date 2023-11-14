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

<style>
	.sort-dropdown {
  position: relative; // 상대적으로 위치 조정
  display: inline-block;
}

.sort-button {
  background-color: #007BFF;
  color: #fff;
  border: none;
  padding: 10px;
  cursor: pointer; // 마우스 커서를 포인터(pointer)로 변경하여 버튼 클릭이 가능함을 표시
}

.sort-options {
  display: none;
  position: absolute; // 절대적 위치에 배치
  background-color: #fff;
  min-width: 120px;
  box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);
  z-index: 1; // 요소의 쌓임 순서(z-index)를 설정하여 다른 요소 위에 표시
}

.sort-dropdown:hover .sort-options {
  display: block;
}

.sort-options a {
  display: block;
  padding: 10px;
  text-decoration: none; // 링크의 기본 텍스트 밑줄을 제거
  color: #333;
  transition: background-color 0.3s; // 배경색에 대한 변화가 부드럽게 일어나도록 0.3초 동안의 전환 효과를 적용
}

.sort-options a:hover {
  background-color: #f5f5f5;
}


</style>

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
	<section class="py-5">
			<h3>&nbsp;신상품</h3>
			<div class="sort-dropdown">
			  <button class="sort-button">정렬</button>
			  <div class="sort-options">
			    <a href="#">가격 순</a>
			    <a href="#">등록 순</a>
			    <a href="#">좋아요 순</a>
			    <a href="#">가나다 순</a>
			    <a href="#">평점 순</a>
			    <a href="#">리뷰 순</a>
			  </div>
			</div>
		<hr>
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

