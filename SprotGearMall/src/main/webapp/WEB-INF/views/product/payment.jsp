<%@page import="edu.spring.mall.domain.ProductVO" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE html>
<html>
<style type="text/css">
table, th, td {
   border-style : solid;
   border-width : 0px;
   text-align : center;

}

th, td {
   border-top: 1px solid black;
   border-bottom: 1px solid black;
}

ul {
    list-style-type : none;
}

li {
    display : inline-block;
}
</style>
<script src="https://code.jquery.com/jquery-3.7.1.js"
integrity="sha256-eKhayi8LEQwp4NKxN+CfCh+3qOVUtJn3QNZ0TciWLP4=" crossorigin="anonymous">
</script>

<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>

<body>

	<% ProductVO vo=(ProductVO) request.getAttribute("vo"); %>


	<hr>
	<h1>${pageContext.request.userPrincipal.name}님의 결제창입니다.</h1>

	<!--
	<div>
	<table>
	<thead>
		<tr>
			<th colspan="2">상품정보</th>
			<th>상품금액</th>
			<th>구매갯수</th>
			<th>배송비</th>
			<th>상품 총 가격</th>
		</tr>	
	</thead>
	<form action="../orders/orderlist" method="POST">
	<tbody>
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
		<input type="hidden" name="memberId" value="${pageContext.request.userPrincipal.name}"
		readonly="readonly">
		<input type="hidden" name="productId" value="${vo.productId }" readonly="readonly">
		<tr>
			<td>
				<img class="card-img-top"
        		src="<c:url value="/resources/img/product1.webp" />" alt="이미지"
        		style="width: 150px; height: 100px;" />
			</td>
			<td>
				<input type="text" name="productName" value="${vo.productName }" readonly="readonly">
			</td>
			<td>
				<input type="number" name="productPrice" value="${vo.productPrice }" id="productPrice"
				readonly="readonly">
			</td>
			<td>
				<input type="number" name="productQuantity" id="productQuantity"
				oninput="calculateTotalPrice()" min="1"><br>
			</td>
			<td>
				배송비 무료
			</td>
			<td>
				<input type="number" name="totalPrice" id="totalPrice" readonly="readonly">
			</td>
		</tr>
	</tbody>
		<input type="submit" value="구매">
	</form>
	</table>
	</div>
	-->
<div>
    <table>
        <thead>
            <tr>
                <th style="width : 250px" colspan="2">상품정보</th>
                <th style="width : 100px">상품금액</th>
                <th style="width : 80px">구매갯수</th>
                <th style="width : 100px">배송비</th>
                <th style="width : 150px">상품 총 가격</th>
            </tr>	
        </thead>
        <tbody>
            <tr>
                <td>
                    <img class="card-img-top"
                    src="<c:url value="/resources/img/product1.webp" />" alt="이미지"
                    style="width: 150px; height: 100px;" />
                </td>
                <td>
                    <input type="text" name="productName" value="${vo.productName }" readonly="readonly">
                </td>
                <td>
                    <input type="number" name="productPrice" value="${vo.productPrice }" id="productPrice" readonly="readonly">
                </td>
                <td>
                    <input type="number" name="productQuantity" id="productQuantity" oninput="calculateTotalPrice()" min="1"><br>
                </td>
                <td>
                    배송비 무료
                </td>
                <td>
                    <input type="number" name="totalPrice" id="totalPrice" readonly="readonly">
                </td>
            </tr>
        </tbody>
    </table>

    <form action="../orders/orderlist" method="POST">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
        <input type="hidden" name="memberId" value="${pageContext.request.userPrincipal.name}" readonly="readonly">
        <input type="hidden" name="productId" value="${vo.productId }" readonly="readonly">
        <input type="hidden" name="productQuantity" id="productQuantity1" readonly="readonly">
        <input type="submit" value="구매">
    </form>
</div>

	<script type="text/javascript">
		function calculateTotalPrice() {
		const productQuantity = document.getElementById('productQuantity').value;
		const productPrice = document.getElementById('productPrice').value;
		const totalPrice = productQuantity * productPrice;
		document.getElementById('totalPrice').value = totalPrice;
		}
		
		function calculateTotalPrice() {
	        // 사용자가 입력한 수량 가져오기
	        var userEnteredQuantity = document.getElementById("productQuantity").value;

	        // 숨겨진(hidden) 입력 필드에 복사
	        document.getElementById("productQuantity1").value = userEnteredQuantity;
	    }
	</script>
	
	
	
	
	
	<!--
	<div>
		<p>상품명 : </p>
		<input type="text" name="productName" value="${vo.productName }" readonly="readonly">
	</div>
	<hr>

	<form action="../orders/orderlist" method="POST">
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
						
	<div>
		<p>접속중인 아이디 : </p>
		<input type="text" name="memberId" value="${pageContext.request.userPrincipal.name}"
		readonly="readonly">
	</div>
	<div>
		<input type="hidden" name="productId" value="${vo.productId }" readonly="readonly">
	</div>
	<div>
		<p>상품1개당 가격 : </p>
		<input type="number" name="productPrice" value="${vo.productPrice }" id="productPrice"
		readonly="readonly">
	</div>
	<div>
		<p>상품갯수 : </p>
		<input type="number" name="productQuantity" id="productQuantity"
		oninput="calculateTotalPrice()" min="1"><br>
	</div>
	<div>
		<input type="submit" value="구매">
	</div>
	</form>
	<div>
		<p>상품 총 가격 : </p>
		<input type="number" name="totalPrice" id="totalPrice" readonly="readonly">
	</div>
	  -->





</body>
</html>