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
	<style type="text/css">
	.card-img-top {
    height: 350px; /* 이미지 높이 고정 */
    object-fit: cover; /* 이미지 비율 유지 */
}

.text-outline {
    text-shadow:
        -1px -1px 0 #000,  
         1px -1px 0 #000,
        -1px  1px 0 #000,
         1px  1px 0 #000; /* 각 방향으로 1px 검은색 테두리 */
}

.main-img .text-white-50 {
    color: black; /* 글자색을 검은색으로 변경 */
}

.lead{
	width: 20%;        /* 선의 길이 */
    margin: auto;  
    white-space: nowrap; /* 줄바꿈 방지 */
    overflow: hidden; /* 넘치는 텍스트 숨김 */
}

.subtitle{
	width: 90%;        /* 선의 길이 */
    margin: auto;  
    white-space: nowrap; /* 줄바꿈 방지 */
    font-size: 1.3em; /* h3 태그의 대략적인 폰트 크기 */
    
}


.card .text-center {
    height: 100px; /* 텍스트 박스 높이 고정 */
}

.main-img {
    background-image: url('https://storage.googleapis.com/edu-mall-img/main-img/main-img.jpg'); 
    background-size: cover; /* 이미지가 header 영역을 전체적으로 커버하도록 설정 */
    background-position: center; /* 이미지가 중앙에 위치하도록 설정 */
}
	</style>
</head>
<body>



<!-- Header-->
	<header class="main-img py-5">
		<div class="container px-4 px-lg-5 my-5">
			<div class="text-center text-white">
				<h1 class="display-4 fw-bolder text-outline">당신의 스포츠 정신을 깨우세요</h1>
				<p class="fw-normal text-white mb-0 subtitle">당신의 열정에 완벽한 동반자, 고품질 스포츠 용품이 기다리고 있습니다</p>
			</div>
		</div>
	</header>
	
	
	
	<!-- Section-->
<section class="py-5">
    <div class="container">
        <div class="row align-items-center mb-4">
            <!-- 텍스트 섹션 -->
            <div class="col text-center">
                <h3>축구공</h3>
                <hr class="lead">
                <p>코난이 즐겨차는 축구공</p>
            </div>
            <!-- 버튼 섹션 -->
            <div class="col-auto">
                <a href="/mall/product/search?searchtext=축구공" class="btn btn-primary">전체 보기</a>
            </div>
        </div>
        <hr>
        <div class="row gx-4 gx-lg-5 row-cols-2 row-cols-md-3 row-cols-xl-4 justify-content-center">
				<c:forEach var="vo" items="${soccerBallList}" begin="0" end="3">
            
                <div class="col mb-5">
                    <div class="card h-100" onclick="location.href='${pageContext.request.contextPath}/product/detail?productId=${vo.productId}'">
                        <img class="card-img-top" src="https://storage.googleapis.com/edu-mall-img/${vo.productImgPath}" alt="이미지" />
                        <div class="text-center">
                            <span class="fw-bolder">${vo.productName}</span><br>
                            <fmt:formatNumber value="${vo.productPrice}" type="number" pattern="#,###"/>원
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
</section>


<section class="py-5">
    <div class="container">
        <div class="row align-items-center mb-4">
            <!-- 텍스트 섹션 -->
            <div class="col text-center">
                <h3>야구공</h3>
                <hr class="lead">
                <p>마운드의 주인공이 되는 야구공</p>
            </div>
            <!-- 버튼 섹션 -->
            <div class="col-auto">
                <a href="/mall/product/search?searchtext=야구공" class="btn btn-primary">전체 보기</a>
            </div>
        </div>
        <hr>
        <div class="row gx-4 gx-lg-5 row-cols-2 row-cols-md-3 row-cols-xl-4 justify-content-center">
				<c:forEach var="vo" items="${baseBallList}" begin="0" end="3">
            
                <div class="col mb-5">
                    <div class="card h-100" onclick="location.href='${pageContext.request.contextPath}/product/detail?productId=${vo.productId}'">
                        <img class="card-img-top" src="https://storage.googleapis.com/edu-mall-img/${vo.productImgPath}" alt="이미지" />
                        <div class="text-center">
                            <span class="fw-bolder">${vo.productName}</span><br>
                            <fmt:formatNumber value="${vo.productPrice}" type="number" pattern="#,###"/>원
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
</section>


<section class="py-5">
    <div class="container">
        <div class="row align-items-center mb-4">
            <!-- 텍스트 섹션 -->
            <div class="col text-center">
                <h3>농구공</h3>
                <hr class="lead">
                <p class="lead">확실한 존재감</p>
            </div>
            <!-- 버튼 섹션 -->
            <div class="col-auto">
                <a href="/mall/product/search?searchtext=농구공" class="btn btn-primary">전체 보기</a>
            </div>
        </div>
        <hr>
        <div class="row gx-4 gx-lg-5 row-cols-2 row-cols-md-3 row-cols-xl-4 justify-content-center">
            <c:forEach var="vo" items="${basketBallList}" begin="0" end="3">
            
                <div class="col mb-5">
                    <div class="card h-100" onclick="location.href='${pageContext.request.contextPath}/product/detail?productId=${vo.productId}'">
                        <img class="card-img-top" src="https://storage.googleapis.com/edu-mall-img/${vo.productImgPath}" alt="이미지" />
                        <div class="text-center">
                            <span class="fw-bolder">${vo.productName}</span><br>
                            <fmt:formatNumber value="${vo.productPrice}" type="number" pattern="#,###"/>원
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
</section>



    <!-- 추가 카테고리 섹션들을 필요에 따라 추가 -->

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
