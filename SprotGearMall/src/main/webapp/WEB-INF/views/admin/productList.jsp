<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="/WEB-INF/views/includes/header.jsp" %>
    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>등록 상품 리스트 관리</title>
<style type="text/css">
.right-align-button {
    text-align: right;
}
</style>
</head>
<body>
<div class="container-fluid">
  <div class="row">
    <!-- 왼쪽 메뉴 -->
   	  <jsp:include page="../includes/admin-sidebar.jsp" />
   	

    <!-- 오른쪽 컨텐츠 -->
   <div id="contentDiv" class="col-md-9 container-fluid">
   <input type="hidden" id="memberId" value="${pageContext.request.userPrincipal.name}">
	<input type="hidden" id="csrfToken" name="${_csrf.parameterName}" value="${_csrf.token}"/>
	 <c:if test="${not empty list}">
	<div class="container mt-5">
		<c:forEach var="vo" items="${list }">
			<div class="row mb-3" id="div-${vo.productId }">
				<div class="col-md-2">
					<img src="https://storage.googleapis.com/edu-mall-img/${vo.productImgPath }" class="img-fluid" alt="Product Image">
				</div>
				<div class="row" onclick="location.href='../product/detail?productId=${vo.productId}'">
				<div class="col-md-7">
					<h5>상품 명 : ${vo.productName}</h5>
					<p>제조사 : ${vo.productMaker}</p>
					<p>카테고리 : ${vo.productCategory }</p>
					<p>가격 : ${vo.productPrice}원</p>
					<p>재고 : ${vo.productStock }</p>
				</div>
				<div class="col-md-3 right-align-button">
					<button type="button" id="btn-delete-${vo.productId}" class="btn btn-danger btn-delete" onclick="productDelete(event,this)" data-product-id="${vo.productId}">삭제</button>
				</div>
				</div>
				
				<hr>
			</div>
			
		</c:forEach>
		<nav id="nav" aria-label="Page navigation example" class="mt-3 d-flex justify-content-start">
		<ul class="pagination">
			<c:if test="${pageMaker.hasPrev }">
				<li  class="page-item"><a  class="page-link" href="productList?page=${pageMaker.startPageNo - 1 }">이전</a></li>
			</c:if>
			<c:forEach begin="${pageMaker.startPageNo }" end="${pageMaker.endPageNo }" var="num">
				<li class="page-item"><a class="page-link" href="productList?page=${num }">${num }</a></li>	
			</c:forEach>
			<c:if test="${pageMaker.hasNext }">
				<li class="page-item"><a class="page-link" href="productList?page=${pageMaker.endPageNo + 1 }">다음</a></li>
			</c:if>
		</ul>
		</nav>
	</div>
	</c:if>
	
    </div>
    
  
  </div>
</div>
	<script type="text/javascript">
		function productDelete(event, element){
		    event.stopPropagation(); 
			var productId = $(element).data('product-id');
			console.log("productId", productId)
			$('#btn-delete'+productId).prop('disabled',true);
			var csrfToken = $('#csrfToken').val();
			var headers = {
					'Content-Type': 'application/json',
					'X-CSRF-TOKEN': csrfToken
			}
			var isConfirm = confirm('해당 상품을 삭제하겠습니까?');
			if(!isConfirm){
				return
			}
			$.ajax({
				type:'PUT',
				url:'softDelete',
				headers:headers,
				data:JSON.stringify({
					'productId':productId,
					'productIsDeleted':1
				}),
				success:(result)=>{
					if(result ==='success'){
						$('#div-'+productId).remove();
					}else{
						alert('삭제 실패');
						$('#btn-delete'+productId).prop('disabled',false);
					}
				}
			})//end ajax

		}
	</script>
	
</body>
</html>