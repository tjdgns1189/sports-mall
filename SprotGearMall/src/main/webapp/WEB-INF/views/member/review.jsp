<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 
    
<!DOCTYPE html>
<html>
<head>
<script src="https://code.jquery.com/jquery-3.7.1.js"></script>
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.2/css/all.css">
<script src="<c:url value="/resources/js/review.js" />"></script>
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
</style>
<meta charset="UTF-8">
</head>
<body>
<h2 style="display: inline-block;">리뷰하기</h2>
<button class="close" onclick="window.close();" style="float: right;"><i class="fas fa-times"></i></button>
<hr>
<!-- 상품 정보 넣을곳 -->
<!-- product는 당연히 와야함 order 와야함 -->
<div>
주문번호 : ${orderVO.orderId }<br>
<input type="hidden" id="orderId" name="orderId" value=${orderVO.orderId }>
	이미지/
	상품명 : ${productVO.productName }<br>
	상품번호 : ${productVO.productId}<br>
	구매 가격 : ${orderVO.productPrice }<br>
	구매 수량 :  ${orderVO.productQuantity }<br>
	별점 확인용 : ${reviewVO.reviewRating }<br>
	
<input type="hidden" id="productId" name="productId" value="${productVO.productId}">
<c:if test="${not empty reviewVO }">
	리뷰아이디 확인용 : ${reviewVO.reviewId }
<input type="hidden" id="reviewRating" name="orderRating" value="${reviewVO.reviewRating }">
<input type="hidden" id="reviewId" name="reviewId" value="${reviewVO.reviewId }">

</c:if>
<c:if test="${empty reviewVO }">
<input type="hidden" id="reviewRating" value="0">
</c:if>
</div>
<hr>
<!-- 별점 넣기 -->
 <div id="currentRating">
</div>
 <div id="star-rating" class="star-rating">
  			<span class="far fa-star" data-rating="1"></span>
  			<span class="far fa-star" data-rating="2"></span>
 			<span class="far fa-star" data-rating="3"></span>
  			<span class="far fa-star" data-rating="4"></span>
  			<span class="far fa-star" data-rating="5"></span>
	</div>
	<!-- 리뷰 글 작성 -->
	<div>
	<input type="text" id="reviewContent" placeholder="리뷰 작성 칸" value="${reviewVO.reviewContent}">
	</div>
	<!-- 등록 버튼 -->
	<div>
	<div id="contentLength">
	</div>
	 
	 <input type="hidden" id="csrfToken" name="${_csrf.parameterName}" value="${_csrf.token}">
	<c:if test="${empty reviewVO}">
	 <button type="submit" id="submit">리뷰 작성</button>
	 </c:if>
	 <c:if test="${not empty reviewVO }">
	 	<button type="submit" id="updateSubmit">리뷰 수정</button>
	 </c:if>
	 <button onclick="window.close()">취소</button>
	</div>
</body>
</html>