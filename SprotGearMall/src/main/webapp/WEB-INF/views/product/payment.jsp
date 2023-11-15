<%@page import="org.springframework.security.core.context.SecurityContextHolder"%>
<%@page import="edu.spring.mall.domain.ProductVO" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/views/includes/header.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>결제 창</title>
<style type="text/css">
table, th, td {
   border-style : solid;
   border-width : 0px;
   text-align : center;
}
th, td {
   border-top: 1px solid #ddd;
   border-bottom: 1px solid #ddd;
}

ul {
    list-style-type : none;
}

li {
    display : inline-block;
}

#addressTable td {
    border-collapse: collapse;
    border-top: 1px solid #ddd;
    border-bottom: 1px solid #ddd;
    padding: 8px;
    text-align: center;
}

#addressTable th {
	border-top: 1px solid #ddd;
    border-width : 0px;
}

#addressTable {
	border-top: 1px solid #ddd;
    border-bottom :1px solid #ddd;
    border-spacing: 0px;
    width: 50%;
}


#final {
	border-bottom : 1px solid #ddd;
    width: 50%;
    height: 400px;
}

#totalPrice1 {
	font-size: 30px; 
    text-align: center; 
}

</style>
</head>

<body>

<div class="container my-4">
    
    
    <c:if test="${not empty vo}">
    <div class="text-center mb-4">
        <h4>${pageContext.request.userPrincipal.name}님의 결제창</h4>
    </div>
    <div class="row justify-content-center">
        <div class="col-md-8">
            <table class="table">
                <thead>
                    <tr>
                        <th colspan="2">상품정보</th>
                        <th>상품금액</th>
                        <th>구매갯수</th>
                        <th>배송비</th>
                        <th>상품 총 가격</th>
                    </tr>    
                </thead>
                <tbody>
                    <tr class="align-middle">
                        <td>
                            <img class="img-thumbnail" src="https://storage.googleapis.com/edu-mall-img/${vo.productImgPath }" alt="Product Image" style="width: 150px; height: auto;" />
                        </td>
                        <td>
                            <input type="text" class="form-control-plaintext" name="productName" value="${vo.productName}" readonly>
                        </td>
                        <td>
                            <input type="text" class="form-control-plaintext text-center" name="productPrice" value="${vo.productPrice}" id="productPrice" readonly>
                        </td>
                        <td>
                            <input type="number" class="form-control" name="productQuantity" id="productQuantity" value="1" oninput="calculateTotalPrice()" min="1">
                        </td>
                        <td>
                            배송비 무료
                        </td>
                        <td>
                            <input type="text" class="form-control-plaintext text-center" name="totalPrice" id="totalPrice" value="${vo.productPrice}" readonly>
                        </td>
                    </tr>
                </tbody>
            </table>
        </div>
    </div>
    </c:if>
    
    
    
    
    <c:if test="${not empty list}">
    <div class="text-center mb-4">
        <h4>${vo.cart.memberId}님의 결제창</h4>
    </div>
    <div class="row justify-content-center">
        <div class="col-md-8">
            <table class="table">
                <thead>
                    <tr>
                        <th colspan="2">상품정보</th>
                        <th>상품금액</th>
                        <th>구매갯수</th>
                        <th>배송비</th>
                        <th>상품 총 가격</th>
                    </tr>    
                </thead>
                <tbody>
                <c:forEach var="vo" items="${list }">
                    <tr class="align-middle">
                        <td>
                            <img class="img-thumbnail" src="https://storage.googleapis.com/edu-mall-img/${vo.product.productImgPath }" alt="Product Image" style="width: 150px; height: auto;" />
                        </td>
                        <td>
                            <input type="text" class="form-control-plaintext" name="productName" value="${vo.product.productName}" readonly>
                        </td>
                        <td>
                            <input type="text" class="form-control-plaintext text-center" name="productPrice" value="${vo.product.productPrice}" id="productPrice" readonly>
                        </td>
                        <td>
                            <input type="number" class="form-control" name="productQuantity" id="productQuantity" value="1" oninput="calculateTotalPrice()" min="1">
                        </td>
                        <td>
                            배송비 무료
                        </td>
                        <td>
                            <input type="text" class="form-control-plaintext text-center" name="totalPrice" id="totalPrice" value="${vo.product.productPrice}" readonly>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
    </c:if>
    
    

    <div class="row justify-content-center my-5">
        <div class="col-md-8">
            <h4>배송지정보</h4>
            
            <table class="table">
                <tbody>
                    <tr>
                        <th scope="row">이름</th>
                        <td><sec:authentication property="principal.name" /></td>
                    </tr>
                    <tr>
                        <th scope="row">연락처</th>
                        <td><sec:authentication property="principal.phone" /></td>
                    </tr>
                    <tr>
                        <th scope="row">주소지</th>
                        <td><sec:authentication property="principal.address" /></td>
                    </tr>
                    <tr>
                        <th scope="row">배송 요청사항</th>
                        <td><input type="text" class="form-control" id="addressOpinion"></td>
                    </tr>
                </tbody>
            </table>
        </div>
    </div>

    <div class="row justify-content-center">
        <div class="col-md-8">
            <h4>약관 동의</h4>
            <table class="table">
                <tr>
                    <th>
                        <div class="form-check">
                            <input type="checkbox" class="form-check-input" id="checkAll" onclick="checkAll('checkAll', 'form-check-input')">
                            <label class="form-check-label" for="checkAll">전체 동의하기</label>
                        </div>
                    </th>
                    <td>
                        주문자이름 확인<br>
                        주소확인<br>
                        배송비확인
                    </td>            
                </tr>
                <tr>
                    <td>
                        <div class="form-check">
                            <input type="checkbox" class="form-check-input" id="check1">
                            <label class="form-check-label" for="check1">환불절대 안됨에 동의합니다. 상세보기</label><br>
                            <input type="checkbox" class="form-check-input" id="check2">
                            <label class="form-check-label" for="check2">리뷰 별5개에 동의합니다. 상세보기</label><br>
                            <input type="checkbox" class="form-check-input" id="check3">
                            <label class="form-check-label" for="check3">물건에 하자가 있을시 구매자책임에 동의합니다. 상세보기</label>
                        </div>
                    </td> 
                    <td>
                        총 상품금액<br>
                        <input type="text" class="form-control-plaintext" name="totalPrice" id="totalPrice1" readonly> 원 
                    </td>          
                </tr>
            </table>
        </div>
    </div>

    <div class="row justify-content-center mb-5">
        <div class="col-md-8 text-center">
            <form action="../orders/orderlist" method="POST">
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                <input type="hidden" name="memberId" value="${pageContext.request.userPrincipal.name}" readonly>
                <input type="hidden" name="productId" value="${vo.productId}" readonly>
                <input type="hidden" name="productQuantity" id="productQuantity1" readonly>
                <input type="hidden" name="productPrice" id="productPrice1" readonly>
                <button type="submit" class="btn btn-primary btn-lg">결제하기</button>
                <a href="/mall">
                	<button type="button" class="btn btn-secondary btn-lg">취소</button>  
                </a>
            </form>
        </div>
    </div>
</div>

	<script type="text/javascript">
	
		function calculateTotalPrice() {
			const productQuantity = document.getElementById('productQuantity').value;
			const productPrice = document.getElementById('productPrice').value;
			const totalPrice = productQuantity * productPrice;
			document.getElementById('totalPrice').value = totalPrice;
			document.getElementById('totalPrice1').value = totalPrice;
			
	        // 사용자가 입력한 수량 가져오기
	        var userEnteredQuantity = document.getElementById("productQuantity").value;
	        var userEnteredPrice = document.getElementById("productPrice").value;

	        // 숨겨진(hidden) 입력 필드에 복사
	        document.getElementById("productQuantity1").value = userEnteredQuantity;
	        document.getElementById("productPrice1").value = userEnteredPrice;
	        
	        
		}

		
		function checkAll(checkAllId, className) {
	        var checkAllCheckbox = document.getElementById(checkAllId);
	        var checkboxes = document.getElementsByClassName(className);

	        for (var i = 0; i < checkboxes.length; i++) {
	            checkboxes[i].checked = checkAllCheckbox.checked;
	        }
	    }
	</script>
 

</body>
</html>


