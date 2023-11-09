<%@page import="edu.spring.mall.persistence.ProductDAO"%>
<%@page import="edu.spring.mall.domain.ProductVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/includes/header.jsp" %>
<!DOCTYPE html>
<html>
<style type="text/css">
table, th, td {
   border-style : solid;
   border-width : 1px;
   text-align : center;
}

ul {
    list-style-type : none;
}

li {
    display : inline-block;
}

/* 별점 스타일 */
.star-rating .fa {
  color: #ddd; /* 별 기본 색상 */
  font-size: 24px; /* 별 크기 */
}

.star-rating .fa:hover,
.star-rating .fa-star {
  color: #f0ad4e; /* 호버하거나 선택된 별 색상 */
}

review-btn{
  width: 100%; /* 부모 요소의 전체 너비를 차지하도록 설정 */
  height: 100%; /* 부모 요소의 전체 높이를 차지하도록 설정 */
  box-sizing: border-box; /* padding과 border가 width와 height에 포함되도록 설정 */
}
</style>
<head>
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.2/css/all.css">

<script src="<c:url value="/resources/js/review.js" />"></script>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
    <div class="container-fluid">
        <div class="row">
            <!-- 사이드바 -->
                <jsp:include page="../includes/mypage-sidebar.jsp" />
            <!-- 메인 콘텐츠 -->
            <div class="col-md-10">
                <h1>${memberId}의 구입내역출력</h1>
                <table class="table">
                    <thead>
                        <tr>
                            <th>삭제</th>
                            <th>주문번호</th>
                            <th>상품이름</th>
                            <th>상품구매수량</th>
                            <th>상품 총 가격</th>
                            <th>구매 날짜</th>
                            <th>버튼 들어갈곳</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="vo" items="${list}"> 
                            <tr>
                                <td><input id="${vo.order.orderId}" type="checkbox"></td> 
                                <td>${vo.order.orderId}</td>             
                                <td><a href="../product/detail?productId=${vo.product.productId}">
                                    ${vo.product.productName}</a>
                                </td>
                                <td>${vo.order.productQuantity}</td>
                                <td>${vo.order.productQuantity * vo.order.productPrice}</td>
                                <td>${vo.order.orderCreatedDate}</td>
                                <td>
                                    <c:if test="${!vo.hasReview}">
                                    <button type="button" class="btn btn-primary review-btn" 
                                    onclick="openReviewWindow('../member/review?orderId=${vo.order.orderId}&productId=${vo.product.productId}');">리뷰하기</button>
                                    </c:if>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
                <br>
                <div>
                    <button id="btnDeleteCheck">선택목록삭제</button>
                </div>
            </div>
        </div>
    </div>
</body>




	<script>
    $(document).ready(function() {
        $("#btnDeleteCheck").click(function() {
            var checkedIds = [];
            $("input[type=checkbox]:checked").each(function() {
                checkedIds.push($(this).attr("id"));
            });
            $.ajax({
                type: "POST",
                url: "delete", 
                headers : {
					'Content-Type' : 'application/json'
				},
                data: JSON.stringify(checkedIds),
                success: function(result) {
                    console.log(result);
                    alert("구매내역 삭제 성공");
                    location.reload();
                }//end success
            });//end ajax
        });//end btnDeleteCheck
    });//end document
    function openReviewWindow(url) {
    	  window.open(url, 'reviewPopup', 'width=484,height=764');
    	}

</script>

</html>