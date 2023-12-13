<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 
    
<!DOCTYPE html>
<html>
<head>
<title>리뷰하기</title>
<script src="https://code.jquery.com/jquery-3.7.1.js"></script>
<script src="<c:url value="/resources/js/review.js" />"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.2/css/all.css">

<style>
  .close {
    float: right; 
    border: none; 
    background-color: transparent; 
    cursor: pointer; 
  }

  .close .fas {
    color: #000; 
  }
  
    #star-rating {
    text-align: center; 
  }

  #star-rating > span {
    font-size: 30px; 
    cursor: pointer; 
  }
    #star-rating .fa-star {
    color: gold; 
  }
   .modal-header .btn-close {
      padding: 0.5rem 0.5rem;
      margin: -0.5rem -0.5rem -0.5rem auto;
        }
    .btn-custom {
    width: 100%; /* 부모의 전체 너비를 차지하게 설정 */
   	height: 50px;
}

.img{
width: 40%;
height:40%;
}
</style>
<meta charset="UTF-8">
</head>
<body>
<div class="container my-4">
    <div class="modal-header">
        <h5 class="modal-title" id="reviewModalLabel">리뷰 작성</h5>
        <button type="button" class="btn-close" onclick="window.close();"></button>
    </div>
    <div class="d-flex">
    <!-- 이미지 -->
    <div class="img">
        <img alt="상품이미지" src="https://storage.googleapis.com/edu-mall-img/${productVO.productImgPath }" class="img-fluid">
    </div>
    
    <!-- 상품 정보 -->
    <div class="flex-grow-1 ms-3">
        <p>주문번호: ${orderVO.orderId }</p>
        <input type="hidden" id="orderId" name="orderId" value="${orderVO.orderId}">
        <p>상품명: ${productVO.productName }</p>
        <p>상품번호: ${productVO.productId}</p>
        <p>구매 가격: ${orderVO.productPrice }</p>
        <p>구매 수량: ${orderVO.productQuantity }</p>
        <input type="hidden" id="productId" name="productId" value="${productVO.productId}">
        
        <c:if test="${not empty reviewVO}">
            <input type="hidden" id="reviewRating" name="orderRating" value="${reviewVO.reviewRating}">
            <input type="hidden" id="reviewId" name="reviewId" value="${reviewVO.reviewId}">
        </c:if>
        
        <c:if test="${empty reviewVO}">
            <input type="hidden" id="reviewRating" value="0">
        </c:if>
    </div>
</div>
<hr>
<!-- 별점 넣기 -->
 <div id="currentRating">
</div>
 <div id="star-rating" class="star-rating my-3">
  			<span class="far fa-star" data-rating="1"></span>
  			<span class="far fa-star" data-rating="2"></span>
 			<span class="far fa-star" data-rating="3"></span>
  			<span class="far fa-star" data-rating="4"></span>
  			<span class="far fa-star" data-rating="5"></span>
  		
	</div>
	 <!-- 리뷰 글 작성 -->
  	<div class="form-label-group">
        <label for="reviewContent">리뷰 내용</label>
        <textarea class="form-control" id="reviewContent" rows="5" placeholder="상품의 장단점을 알려주세요">${reviewVO.reviewContent}</textarea>
        <div id="contentLength" class="text-danger mt-2"></div>
    </div>
  	<!-- 리뷰글 10글자 미만일시 경고 넣는곳 -->
	<div>
		<div id="contentLength">
	</div>
	<!-- 등록 버튼 -->
	 
	 <input type="hidden" id="csrfToken" name="${_csrf.parameterName}" value="${_csrf.token}">
		<c:if test="${empty reviewVO}">
	 <button type="submit" id="submit" class="btn btn-success btn-custom">리뷰 작성</button>
	 </c:if>
	 <c:if test="${not empty reviewVO }">
	 	<button type="submit" id="updateSubmit" class="btn btn-success btn-custom">리뷰 수정</button>
	 </c:if>
	</div>
	</div>
</body>
</html>