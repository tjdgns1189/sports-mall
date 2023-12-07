<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="/WEB-INF/views/includes/header.jsp" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="container-fluid">
  <div class="row">
    <!-- 왼쪽 메뉴 -->
   	  <jsp:include page="../includes/admin-sidebar.jsp" />
   	

    <!-- 오른쪽 컨텐츠 -->
   <div id="contentDiv" class="col-md-9 container-fluid">
   	<table class="table">
   		<thead>
   		<tr>
   		<th>주문번호</th>
   		<th>상품번호</th>
		<th>구매 회원 아이디</th>
   		<th>수량</th>
   		<th>가격</th>
   		<th>구매날짜</th>
   		<th>상태</th>
   		<th>환불 승인</th>
   		</tr>
   		</thead>
   		<tbody>
   		<c:if test="${not empty list }">
   		<c:forEach var="vo" items="${list }">
   		<tr id="tr-${vo.orderId }">
   			<td>${vo.orderId }</td>
   			<td>${vo.productId }</td>
   			<td>${vo.memberId }</td>
   			<td>${vo.productQuantity }</td>
   			<td>${vo.productPrice }</td>
   			<td>${vo.orderCreatedDate }</td>
   			<td>${vo.orderState }</td>
   			<td>
   			<button class="btn btn-outline-primary" onclick="refundApproval(this)" data-order-id="${vo.orderId }">환불 승인</button>
   			</td>
   		</tr>
   		</c:forEach>
   		</c:if>
   		</tbody>

   	</table>
   
    </div>
  </div>
</div>
<script type="text/javascript">
	function refundApproval(element){
		var csrfToken= $('#csrfToken').val();
		var orderId = $(element).data('order-id');
		var headers = {
				'Content-Type': 'application/json',
				'X-CSRF-TOKEN': csrfToken
		}
		$.ajax({
			type:'PUT',
			url:'/mall/refund',
			headers:headers,
			data:JSON.stringify({
				"orderId":orderId,
				"orderState":'환불완료'
			}),
			success:(result)=>{
				if(result === 'success'){
					$('#tr-'+orderId).remove();
				}
			}
			
		})//end ajax
		
	}
</script>

</body>
</html>