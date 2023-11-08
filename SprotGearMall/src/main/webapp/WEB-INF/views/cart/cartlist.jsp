<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/includes/header.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<style type="text/css">
 #final {
	border-bottom : 1px solid #ddd;
    width: 50%;
    height: 400px;
}
</style>
<head>
<meta charset="UTF-8">
<script src="https://code.jquery.com/jquery-3.7.1.js"></script>
<title>Insert title here</title>
</head>
<body>
<h2 align="center">장바구니만드는중</h3>

<input type="hidden" id="memberId" value="${pageContext.request.userPrincipal.name}">
<input type="hidden" id="csrfToken" name="${_csrf.parameterName}" value="${_csrf.token}"/>
<c:if test="${not empty list}">
<div class="container mt-5">
    <c:forEach var="vo" items="${list }">
        <div class="row mb-3">
            <div class="col-md-2">
                <img class="card-img-top"
                    src="https://storage.googleapis.com/edu-mall-img/${vo.product.productImgPath }" alt="이미지" />
            </div>
            <div class="col-md-7">
                <h5>상품 명 : ${vo.product.productName}</h5>
                <p>카테고리 : ${vo.product.productCategory }</p>
                <p id="productPrice_${vo.cart.cartId}">가격 : ${vo.product.productPrice}원</p>
                <p>재고 : ${vo.product.productStock }</p>
                <p>갯수 : 
                    <input type="number" name="productQuantity" id="productQuantity_${vo.cart.cartId}" oninput="calculateTotalPrice('${vo.cart.cartId}')"
                        min="1"><br>
                </p>
                <p>총 가격 : 
                    <input type="number" name="totalPrice" id="totalPrice_${vo.cart.cartId}" readonly="readonly">
                </p>                   
            </div>
            <div class="col-md-3">
                <button type="button" class="btn btn-danger btn-delete" data-product-id="${vo.product.productId}">삭제</button>
            </div>
            <hr>
        </div>
    </c:forEach>
</div>
</c:if>

<div style="text-align: center;">
<h1>
    <p>총 상품금액</p>
    <input type="number" name="allTotalPrice" id="allTotalPrice" readonly="readonly"> 원
</h1>
</div>

<script type="text/javascript">
function calculateTotalPrice(cartId) {
    const productQuantity = document.getElementById(`productQuantity_${cartId}`).value;
    const productPrice = document.getElementById(`productPrice_${cartId}`).innerHTML;
    const priceValue = parseFloat(productPrice.replace("원", "").replace(",", ""));
    const totalPrice = productQuantity * priceValue;
    document.getElementById(`totalPrice_${cartId}`).value = totalPrice;
    updateTotalPrice();
}

function updateTotalPrice() {
    let totalPriceSum = 0;
    <c:forEach var="vo" items="${list}">
        const cartId = "${vo.cart.cartId}";
        const totalPrice = parseFloat(document.getElementById(`totalPrice_${cartId}`).value);
        totalPriceSum += isNaN(totalPrice) ? 0 : totalPrice;
    </c:forEach>
    document.getElementById('allTotalPrice').value = totalPriceSum;
}

updateTotalPrice(); // 페이지가 로드될 때 총 상품금액 업데이트
</script>
    

		
</body>
</html>