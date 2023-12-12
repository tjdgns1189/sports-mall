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
   border-width : 0px;
   text-align : center;
   vertical-align: middle;
   font-size: 18px;
}

.table {
	width : 70%;
}

td input[type="checkbox"]{
	transform: scale(1.5);
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
  width: 100%; /* 버튼을 셀의 너비에 맞춤 */
  white-space: nowrap; /* 버튼 텍스트가 줄바꿈 되지 않도록 함 */
}
</style>
<head>
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.2/css/all.css">

<script src="<c:url value="/resources/js/review.js" />"></script>
<meta charset="UTF-8">
<title>주문내역 조회</title>
</head>
<body>

    <div class="container-fluid">
        <div class="row">
            <!-- 사이드바 -->
                <jsp:include page="../includes/mypage-sidebar.jsp" />
            <!-- 메인 콘텐츠 -->
            <div class="col-md-10">
                <h1>${pageContext.request.userPrincipal.name}의 구입내역출력</h1>
                <!-- <button id="sortByDateBtn">시간순 정렬</button>  -->
                <table class="table">
                    <thead>
                        <tr>
                            <th>삭제</th>
                            <th width="150">이미지</th>
                            <th>상품이름</th>
                            <th>상품구매수량</th>
                            <th>상품 총 가격</th>
                            <th>구매 날짜</th>
                            <th>상태</th>
                            <th>리뷰</th>
                            <th>환불</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="vo" items="${list}"> 
                            <tr>
                                <td><input id="${vo.order.orderId}" type="checkbox"></td> 
                                <td>
                                <div>
                                	<img class="card-img-top"
                    				src="https://storage.googleapis.com/edu-mall-img/${vo.product.productImgPath }" alt="이미지" />
                    			</div>	
                                </td>         
                                <td><a href="../product/detail?productId=${vo.product.productId}">
                                    ${vo.product.productName}</a>
                                </td>
                                <td>${vo.order.productQuantity}</td>
                                <td>${vo.order.productQuantity * vo.order.productPrice}</td>
                                <td class="orderDate"><fmt:formatDate value="${vo.order.orderCreatedDate}" pattern="yyyy-MM-dd HH:mm" /></td>
                                <td id="state-${vo.order.orderId }">${vo.order.orderState }</td>
                                <td>
                                    <c:if test="${!vo.hasReview}">
                                    <button type="button" class="btn btn-primary review-btn" 
                                    onclick="openReviewWindow('../member/review?orderId=${vo.order.orderId}&productId=${vo.product.productId}');">리뷰하기</button>
                                    </c:if>
                                </td>
                                <td>
                                <c:if test="${vo.order.orderState == '구매완료'}">
                                <button type="button" id="btn-${vo.order.orderId}" class="btn btn-outline-danger" onclick="refund(this)" data-order-id="${vo.order.orderId}">환불</button>
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
    <div>
    <%@ include file="/WEB-INF/views/includes/footer.jsp" %>
    
    </div>

</body>


	<script>
    $(document).ready(function() {
        $("#btnDeleteCheck").click(function() {
        	var csrfToken = $("#csrfToken").val();
            var checkedIds = [];
            var headers = {
		    		'Content-Type': 'application/json',
		    	     'X-CSRF-TOKEN': csrfToken
		    }
            $("input[type=checkbox]:checked").each(function() {
                checkedIds.push($(this).attr("id"));
            });
            $.ajax({
                type: "POST",
                url: "delete", 
                headers : headers,
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
    
    function refund(element){
    	var orderId = $(element).data('order-id');
		var csrfToken = $('#csrfToken').val();
    	var isConfirm = confirm('환불요청을 하시겠습니까?');
    	 var headers = {
		    		'Content-Type': 'application/json',
		    	     'X-CSRF-TOKEN': csrfToken
		    }
    	
    	if(!isConfirm){
    		return;
    	}
    	$.ajax({
    		type: "PUT",
    		url: '/mall/refund',
    		headers:headers,
    		data:JSON.stringify({
    			'orderId': orderId,
    			'orderState':'환불요청'		
    		}),
    		success:(result)=>{
    			if(result === 'success'){
    			alert('환불 요청 성공');
    			$('#state-' + orderId).text('환불요청');
    			$('#btn-' + orderId).remove();
    			}
    		}

    	})//end ajax
    }//end refund
    
    
    
    // 시간순 정렬
    $(document).ready(function() {
        // 페이지 로드시 기본적으로 시간순으로 정렬
        sortTableByDate();

        // 시간순 정렬 버튼 클릭 이벤트 처리
        $("#sortByDateBtn").click(function() {
            sortTableByDate();
        });
    });

    function sortTableByDate() {
        var table = $(".table");
        var rows = $("tbody tr", table).toArray().sort(compareRowsByDate);
        $("tbody", table).empty().append(rows);
    }

    function compareRowsByDate(a, b) {
        var dateA = new Date($(".orderDate", a).text());
        var dateB = new Date($(".orderDate", b).text());
        return dateA < dateB ? 1 : -1;
    }

</script>

</html>