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
</style>
<head>
<script src="<c:url value="/resources/js/review.js" />"></script>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<!-- ${vo.getOrderProductName() } -->
<!--  ${vo.product.productName }-->
	<h1>${memberId }의 구입내역출력</h1>
	
		<table>
    	<thead>
    		<tr>
    			<th style="width : 50px">삭제</th>
    			<th style="width : 80px">주문번호</th>
    			<th style="width : 200px">상품이름</th>
    			<th style="width : 100px">상품구매수량</th>
    			<th style="width : 100px">상품 총 가격</th>
    			<th style="width : 150px">구매 날짜</th>
    		</tr>
    	</thead>
    	<tbody>
    		<c:forEach var="vo" items="${list }"> 
    			<tr>
    				<td><input id="${vo.orderId }" type="checkbox"></td> 
    				<td>${vo.orderId }</td> 				
    				<td><a href="../product/detail?productId=${vo.productId}">
						테이블 조인예정</a>
    				</td>
    				<td>${vo.productQuantity }</td>
    				<td>${vo.productQuantity * vo.productPrice }</td>
    				<td>${vo.orderCreatedDate }</td>
				<td><button type="button" class="btn btn-primary review-btn" data-toggle="modal" data-target="#reviewModal" data-productid="${vo.productId }">리뷰하기</button></td>
    			</tr>
    		</c:forEach>
    	</tbody>
    </table>
</body>

<br>

	<div>
		<button id="btnDeleteCheck">선택목록삭제</button>
	</div>
	<!-- 모달 -->
<div class="modal fade" id="reviewModal" tabindex="-1" role="dialog" aria-labelledby="reviewModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="reviewModalLabel">상품 정보</h5>
        <!-- 닫기버튼 -->
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
        <div id="star-rating" class="star-rating">
          <span class="fa fa-star-o"></span>
          <span class="fa fa-star-o"></span>
          <span class="fa fa-star-o"></span>
          <span class="fa fa-star-o"></span>
          <span class="fa fa-star-o"></span>
        </div>
        <textarea id="reviewText" class="form-control mt-3" rows="5" placeholder="리뷰를 작성해주세요."></textarea>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-primary" id="submitReview">보내기</button>
      </div>
    </div>
  </div>
</div>
	
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
</script>


	
</html>