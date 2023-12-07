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

<!-- <script type="text/javascript">
	function fetchProducts(searchText) {
	console.log("hey");
    var url = "/mall/product/search?searchtext=" + searchText;

    // AJAX를 사용하여 상품 목록을 가져옴
    fetch(url)
        .then(response => response.json())
        .then(data => {
            products = data; // 받아온 데이터를 배열에 저장
            
        });
   	 
}
</script> -->


</head>
<body>

	<!-- Section-->
	<section class="py-5">
		<h3>&nbsp;신상품</h3>
		<div class="sort-dropdown">
			  <button class="sort-button">정렬</button>
			  <div class="sort-options">
			   <input type="button" value="가격 오름차순" onclick="sort('asc')">
			   <input type="button" value="가격 내림차순" onclick="sort('desc')">
			   <input type="button" value="최신 등록순" onclick="sort('registration')">
			   <input type="button" value="좋아요순" onclick="sort('likes')">
			   <input type="button" value="가나다 순" onclick="sort('alphabetize')">
			   <input type="button" value="평점 순" onclick="sort('rating')">
			   <input type="button" value="리뷰 순" onclick="sort('review')">
			  </div>
			</div>	
		<hr>
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
								<input type="hidden" id="searchTextValue" value="${searchText}">
							</div>
						</div>
					</div>
				</c:forEach>
				</div>
			</div>
		</section>
	
	 <script>
		function sort(order) {
	        var searchTextValue = ($('#searchTextValue').val()); 
	        console.log('URL:', '/mall/product/search?searchtext=' + searchTextValue);
	        
	        var dataToSend = { searchtext : searchTextValue };
	        
	        if (order === 'asc') {
	            dataToSend.order = 'asc';
	        } else if (order === 'desc') {
	            dataToSend.order = 'desc';
	        } else if (order === 'registration') {
	            dataToSend.order = 'registration';
	        } else if (order === 'alphabetize') {
	            dataToSend.order = 'alphabetize';
	        } else if (order === 'likes') {
	            dataToSend.order = 'likes';
	        } else if (order === 'rating') {
	            dataToSend.order = 'rating';
	        } else if (order === 'review') {
	            dataToSend.order = 'review';
	        } 
	        
	     // Ajax를 사용하여 서버에서 데이터를 가져옴
	        $.ajax({
	            url: '/mall/product/search',  // 요청을 처리하는 서버의 URL
	            type: 'GET',
	            data: dataToSend,  // 필요한 데이터 전달
	            dataType: 'json',
	            headers: {
	                'Accept': 'application/json'  // JSON 형식의 응답을 요청하는 헤더
	            },
	            success: function(data) {
	            	// 가져온 데이터를 이용하여 가격 순으로 정렬
	                if (order === 'asc') {
	                    data.sort(function(a, b) {
	                        // 오름차순 정렬 (낮은 가격부터)
	                        return a.productPrice - b.productPrice;
	                    });
	                } else if (order === 'desc') {
	                    // 내림차순 정렬 (높은 가격부터)
	                    data.sort(function(a, b) {
	                        return b.productPrice - a.productPrice;
	                    });
	                } else if (order === 'alphabetize') {
	                	// 가나다순 정렬
	                	data.sort(function(a, b) {
	                		return a.productName.localeCompare(b.productName);
	                	});
	                } else if (order === 'registration') {
	                	// 최신등록순 정렬
	                	data.sort(function(a, b) {
                    		return new Date(a.productCreatedDate) - new Date(b.productCreatedDate);
	                	});
	                }
	            	
	            	console.log(data);
	            	
	                var str = '';
	                $.each(data, function(index, vo) {
	                	str += '<div class="col mb-5">' +
	                    '<div class="card h-100" onclick="location.href=\'detail?productId=' + vo.productId + '\'">' +
	                    '<img class="card-img-top" src="https://storage.googleapis.com/edu-mall-img/' + vo.productImgPath + '" alt="이미지" />' +
	                    '<div class="text-center">' +
	                    '<span class="fw-bolder">' + vo.productName + '</span><br>' +
	                    vo.productPrice + ' 원' +
	                    '<input type="hidden" id="searchTextValue" value="' + searchTextValue + '">' +
	                    '</div>' +
	                    '</div>' +
	                    '</div>';
	                });
	              
	                $('.row.gx-4.gx-lg-5.row-cols-2.row-cols-md-3.row-cols-xl-4.justify-content-center').html(str);
	            },
	            error: function(error) {
	                console.log('Error:', error);
	            }
	        });
	    } 
	</script>
</body>
</html>