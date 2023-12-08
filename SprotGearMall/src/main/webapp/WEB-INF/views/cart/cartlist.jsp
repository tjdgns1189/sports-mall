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
<title>장바구니</title>
</head>
<body>
<h2 align="center">장바구니</h2>

<input type="hidden" id="memberId" value="${pageContext.request.userPrincipal.name}">
<input type="hidden" id="csrfToken" name="${_csrf.parameterName}" value="${_csrf.token}"/>


<c:if test="${not empty list}">
<div class="container mt-5">


    <c:forEach var="vo" items="${list }">
        <div class="row mb-3" id="cartmenu_${vo.cart.cartId}">
            <div class="col-md-2">
                <img class="card-img-top"
                    src="https://storage.googleapis.com/edu-mall-img/${vo.product.productImgPath }" alt="이미지" />
            </div>
            <div class="col-md-7">
                <h5>상품 명 : ${vo.product.productName}</h5>
                <p>카테고리 : ${vo.product.productCategory }</p>
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                <input type="hidden" name="cartId" value="${vo.cart.cartId}" readonly>
                <input type="hidden" name="memberId" value="${pageContext.request.userPrincipal.name}" readonly>
                <input type="hidden" name="productId" value="${vo.product.productId}" readonly>
                
                <p>
                   가격 : <input type="number" name="productPriceOne" value="${vo.product.productPrice }" id="productPrice_${vo.cart.cartId}" readonly="readonly">
                </p>
                <p>재고 : ${vo.product.productStock }</p>
                <p>갯수 : 
                    <input type="number" name="productQuantity" id="productQuantity_${vo.cart.cartId}" value="1" oninput="calculateTotalPrice('${vo.cart.cartId}')" min="1"><br>             
                </p>
                <p>총 가격 : 
                    <input type="number" name="productPrice" id="totalPrice_${vo.cart.cartId}" value=${vo.product.productPrice } readonly="readonly">
                </p>                   
            </div>
            <div class="col-md-3">
                <button type="button" class="btn btn-danger btn-delete" data-cart-id="${vo.cart.cartId}">삭제</button>
            </div>
            <hr>
        </div>
    </c:forEach>
    <button type="button" id="btn-paymnet">결제하기</button>


    
    
</div>
</c:if>

<div style="text-align: center;">
<h1>
    <p>총 상품금액</p>
    <input type="number" name="allTotalPrice" id="allTotalPrice" readonly="readonly"> 원&nbsp;&nbsp;&nbsp;&nbsp;
</h1>
</div>


<script type="text/javascript">

updateAllTotalPrice();

function calculateTotalPrice(cartId) {
    const productQuantity = document.getElementById('productQuantity_' + cartId).value;
    const productPrice = document.getElementById('productPrice_' + cartId).value;
    const totalPrice = productQuantity * productPrice;
    document.getElementById('totalPrice_' + cartId).value = totalPrice;
    
    // 전체 상품 가격 다시 계산
    updateAllTotalPrice();
}

function updateAllTotalPrice() {
    let total = 0;

    // 각 상품의 총 가격을 더함
    const elements = document.querySelectorAll('[id^="totalPrice_"]');
    elements.forEach(element => {
        total += parseFloat(element.value) || 0;
    });

    // 결과를 allTotalPrice에 반영
    document.getElementById('allTotalPrice').value = total;
}


// 버튼 누를시 삭제
    $(".btn-delete").click(function () {
        var cartId = $(this).data("cart-id");
        var csrfToken = $("#csrfToken").val();
        console.log(cartId);
		
        $.ajax({
            type: "DELETE",
            url: "delete/" + cartId,
            headers : {
				'Content-Type' : 'application/json',
				'X-CSRF-TOKEN': csrfToken
			},
            data: { "cartId": cartId },
            success: function (result) {
            	if(result == 1){
                // 성공 시 처리, 예를 들어 카트 뷰 업데이트
                alert('장바구니삭제성공');
                location.reload();  // 페이지를 새로 고침하여 업데이트된 카트를 반영
            	}else{
            		alert('에러');
                    location.reload();
            	}
            }
        });
    });
    
    




$("#btn-paymnet").click(function () {
	const cartList = [];
	var csrfToken = $("#csrfToken").val();
  	
	var bringList = ${jsonList};
	$.each(bringList, function(index, vo) {
        const cartVO = {
            cartId: vo.cart.cartId,
            memberId: "${pageContext.request.userPrincipal.name}",
            productId: vo.product.productId,
            productPrice: $("#totalPrice_" + vo.cart.cartId).val(),
            productQuantity: $("#productQuantity_" + vo.cart.cartId).val()
        };
        console.log('cartVO 확인', cartVO);
        cartList.push(cartVO);
    });
	console.log('cartList 확인', cartList);
	
	// Ajax를 사용하여 서버로 cartList 전송
    $.ajax({
        type: "POST",
        url: "update/",
        headers: {
            'Content-Type': 'application/json',
            'X-CSRF-TOKEN': csrfToken
        },
        data: JSON.stringify(cartList),
        success: function (result) {
            // 성공 시 처리
            if (result >= 1) {
                alert('결제창으로 이동');
                window.location.href = '../product/payment';
            } else {
                alert('장바구니 수정 실패');
            }
        }
    });	
});



	


</script>

    

		
</body>
</html>