<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="/WEB-INF/views/includes/header.jsp" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="<c:url value="/resources/js/review.js" />"></script>

<title>리뷰 리스트</title>
<style type="text/css">
.img{
width: 60px;
height: 60px;
}
</style>
</head>
<body>

<!-- 전체 컨테이너 -->
<div class="container-fluid">
  <!-- 전체 행 -->
  <div class="row">
  
    <!-- 사이드바 -->
	<jsp:include page="../includes/mypage-sidebar.jsp" />

    <!-- 메인 콘텐츠 -->
    <div class="col-md-10">
      <c:if test="${not empty list }">
        <div class="accordion" id="accordionExample">
          <c:forEach var="vo" items="${list }" varStatus="status">
            <div class="card" id="review-${vo.review.reviewId}">
              <div class="card-header" id="heading${status.index}" 
                   data-bs-toggle="collapse" data-bs-target="#collapse${status.index}"
                   aria-expanded="false" aria-controls="collapse${status.index}" style="cursor:pointer;">
                <!-- 이미지와 상품정보를 수평으로 배치 -->
                <div class="d-flex">
                  <!-- 상품 이미지 -->
                  <div>
                    <img alt="구매상품 이미지" src="https://storage.googleapis.com/edu-mall-img/${vo.product.productImgPath }" class="img">
                  </div>    
                  <!-- 상품정보 -->
                  <div>
                    <h5 class="mb-0">
                      상품명 : ${vo.product.productName} - 별점 : ${vo.review.reviewRating }<br> 
                     </h5>
                      ${vo.product.productPrice }원
                      <button class="update_btn" data-id="${vo.review.reviewId}" onclick="openReviewWindow('reviewUpdate?reviewId=${vo.review.reviewId}');">리뷰 수정</button>
                      <button class="delete_btn" data-id="${vo.review.reviewId}">리뷰 삭제</button>
                    
                  </div>
                </div>
              </div>
              <!-- 리뷰 내용들어가는 아코디언 박스 -->
              <div id="collapse${status.index}" class="collapse" aria-labelledby="heading${status.index}" data-bs-parent="#accordionExample">
                <div class="card-body">
                  ${vo.review.reviewContent }
                </div>
              </div>
            </div>
          </c:forEach>
        </div>
      </c:if>
    </div>
    
  </div>
</div>

<script type="text/javascript">
function openReviewWindow(url) {
    window.open(url, 'reviewPopup', 'width=484,height=764');
  }

</script>
</body>
</html>