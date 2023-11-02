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
    color: gold; /* 별의 색상을 금색으로 설정합니다. */
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
	이미지/${productVO.productName }/${productVO.productId}
		/${orderVO.productPrice }/ ${orderVO.productQuantity }
<input type="hidden" id="productId" name="productId" value="${productVO.productId}">
		
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
	<input type="text" id="reviewContent" placeholder="리뷰 작성 칸">
	</div>
	<!-- 등록 버튼 -->
	<div>
	<div id="contentLength">
	</div>
	 <input type="hidden" id="csrfToken" name="${_csrf.parameterName}" value="${_csrf.token}">
	 <button type="submit" id="submit">리뷰 작성</button>
	</div>
</body>
</html>