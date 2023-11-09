<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="/WEB-INF/views/includes/header.jsp" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="<c:url value="/resources/js/review.js" />"></script>

<title></title>
</head>
<body>

<c:if test="${not empty list }">
  <div class="accordion" id="accordionExample">
    <c:forEach var="vo" items="${list }" varStatus="status">
      <div class="card" id="review-${vo.review.reviewId}">
        <div class="card-header" id="heading${status.index}" 
             data-bs-toggle="collapse" data-bs-target="#collapse${status.index}"
             aria-expanded="false" aria-controls="collapse${status.index}" style="cursor:pointer;">
          <h5 class="mb-0">
            상품명 : ${vo.product.productName} - ${vo.product.productPrice }원 - 별점 : ${vo.review.reviewRating }
            <button class="update_btn" data-id="${vo.review.reviewId}" onclick="openReviewWindow('reviewUpdate?reviewId=${vo.review.reviewId}');">리뷰 수정</button>
        	 <button class="delete_btn" data-id="${vo.review.reviewId}">리뷰 삭제</button>
          </h5>
        </div>
        
        <div id="collapse${status.index}" class="collapse" aria-labelledby="heading${status.index}" data-bs-parent="#accordionExample">
          <div class="card-body">
            ${vo.review.reviewContent }
          </div>
        </div>
      </div>
    </c:forEach>
  </div>
</c:if>
<script>
$(() => {
  $('.update_btn').click(function() {
    // 여기에 리뷰 수정 관련 코드를 추가할 수 있습니다.
  }); // 여기서 첫 번째 함수가 종료되어야 합니다.

  $('.delete_btn').click(function() {
    var reviewId = $(this).data('id');
    var csrfToken = $("#csrfToken").val();
    var $reviewDiv = $("#review-" + reviewId);
    var headers = {
      'Content-Type': 'application/json',
      'X-CSRF-TOKEN': csrfToken
    };

    $.ajax({
      type: 'delete',
      url: '/mall/member/reviewDelete',
      headers: headers,
      data: JSON.stringify({
        'reviewId': reviewId
      }),
      success: (success) => {
        console.log(success);
        if (success === 'success') {
          alert('리뷰 삭제를 성공했습니다');
          $reviewDiv.remove();
        } else {
          alert('삭제 실패');
        }
      } // end success function
    }); // end ajax call
  }); // end delete_btn.click event

}); // end document.ready function

function openReviewWindow(url) {
  window.open(url, 'reviewPopup', 'width=484,height=764');
}
</script>


</body>
</html>