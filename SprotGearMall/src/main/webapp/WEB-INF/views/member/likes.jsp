<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/includes/header.jsp"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<input type="hidden" id="memberId" value="${pageContext.request.userPrincipal.name}">
	<input type="hidden" id="csrfToken" name="${_csrf.parameterName}" value="${_csrf.token}"/>
	 <c:if test="${not empty list}">
	<div class="container mt-5">
		<c:forEach var="vo" items="${list }">
			<div class="row mb-3">
				<div class="col-md-2">
					<img src="<c:url value='/resources/img/product1.webp' />"
						class="img-fluid" alt="Product Image">
				</div>
				<div class="col-md-7">
					<h5>${vo.productName}</h5>
					<p>${vo.productMaker}</p>
					<p>${vo.productPrice}</p>
				</div>
				<div class="col-md-3">
					<a
						href="${pageContext.request.contextPath}/product/payment?productId=${vo.productId}">
						<button type="button" class="btn btn-success">구매</button>
					</a>
					<button type="button" class="btn btn-danger btn-delete" data-product-id="${vo.productId}">삭제</button>
				</div>
				<hr>
			</div>
		</c:forEach>
	</div>
	</c:if>
	<c:if test="${empty list}">
	
	<div class="container mt-5">
        <h2>찜 한 상품이 없어요</h2>
	</div>
	
	</c:if>

	<script type="text/javascript">
	$(() => {
	    $(".btn-delete").click(function() {
	    	console.log('삭제버튼 클릭했음')
	    	var productId = $(this).data('product-id'); 	    	 
	    	var memberId = $('#memberId').val();
	    	 var csrfToken = $("#csrfToken").val();
	    	 var headers = {
	    	        'Content-Type': 'application/json'
	    	    };
	    	    headers['X-CSRF-TOKEN'] = csrfToken;
	        $.ajax({
	            type: "DELETE",
	            url: '../product/likes',
	            headers: headers,
	            data: JSON.stringify({
	                "memberId": memberId,
	                "productId": productId,
	            }),
	            success: (result) => {
	                console.log(result);
	                if (result === 'success') {
	                	alert('삭제되었습니다');
	                	$(this).closest(".row").remove()
	                }
	            } 
	        }); // end ajax
	    }); // end btn click
	}); //end document.ready

</script>
</body>
</html>