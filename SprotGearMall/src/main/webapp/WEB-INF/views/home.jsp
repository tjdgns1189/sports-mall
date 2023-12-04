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

    <!-- 신상품 섹션 -->
    <section class="py-5">
        <h3>&nbsp;신상품</h3>
        <hr>
        <div class="container px-4 px-lg-5 mt-5">
            <div class="row gx-4 gx-lg-5 row-cols-2 row-cols-md-3 row-cols-xl-4 justify-content-center" id="newProductList">
                <!-- 신상품 목록이 여기에 동적으로 추가될 것입니다. -->
            </div>
        </div>
    </section>

    <!-- 축구공 섹션 -->
    <section class="py-5">
        <h3>&nbsp;축구공</h3>
        <hr>
        <div class="container px-4 px-lg-5 mt-5">
            <div class="row gx-4 gx-lg-5 row-cols-2 row-cols-md-3 row-cols-xl-4 justify-content-center" id="soccerBallList">
                <!-- 축구공 목록이 여기에 동적으로 추가될 것입니다. -->
            </div>
        </div>
    </section>

    <!-- 농구공 섹션 -->
    <section class="py-5">
        <h3>&nbsp;농구공</h3>
        <hr>
        <div class="container px-4 px-lg-5 mt-5">
            <div class="row gx-4 gx-lg-5 row-cols-2 row-cols-md-3 row-cols-xl-4 justify-content-center" id="basketballList">
                <!-- 농구공 목록이 여기에 동적으로 추가될 것입니다. -->
            </div>
        </div>
    </section>

    <!-- 추가 카테고리 섹션들을 필요에 따라 추가 -->

    <input type="hidden" id="insertAlert" value="${insert_result }">

    <script type="text/javascript">
    $(()=>{
    	console.log('dkdkdkdkdkdkdkdkd');
    	var list = ${list};  // 뷰에서 전달받은 상품 목록
    	list = JSON.parse('${list}');  // JSON 문자열을 JavaScript 객체로 파싱
    	console.log('list', list);
        displayProductsByCategory('신상품', 'newProductList');

        // 카테고리별로 상품 출력
        displayProductsByCategory('축구공', 'soccerBallList');
        displayProductsByCategory('농구공', 'basketballList');
    })
        

        function displayProductsByCategory(category, containerId) {
        	console.log('displayProductsByCategory 호출');
            var filteredProducts = list.filter(function(vo) {
                return vo.category === category;
            });

            var str = '';
            filteredProducts.forEach(function(vo) {
                str += '<div class="col mb-5">' +
                    '<div class="card h-100" onclick="location.href=\'${pageContext.request.contextPath}/product/detail?productId=' + vo.productId + '\'">' +
                    '<img class="card-img-top" src="https://storage.googleapis.com/edu-mall-img/' + vo.productImgPath + '" alt="이미지" />' +
                    '<div class="text-center">' +
                    '<span class="fw-bolder">' + vo.productName + '</span><br>' +
                    vo.productPrice + ' 원' +
                    '</div>' +
                    '</div>' +
                    '</div>';
            });

            $('#' + containerId).html(str);
        }

        // 초기화: 신상품 출력


        // 다른 카테고리 섹션들에 대한 출력 코드를 필요에 따라 추가

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
