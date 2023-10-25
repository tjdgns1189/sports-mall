<%@page import="edu.spring.mall.domain.ProductVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<script src="https://code.jquery.com/jquery-3.7.1.js" 
integrity="sha256-eKhayi8LEQwp4NKxN+CfCh+3qOVUtJn3QNZ0TciWLP4=" crossorigin="anonymous">
</script>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<%
        ProductVO vo = (ProductVO) request.getAttribute("vo");
    %>
	
    <c:if test="${empty sessionScope.memberId }">
    	<p>로그인다시하기</p>
    </c:if>
	<c:if test="${not empty sessionScope.memberId }">
        <p>회원아이디 : ${sessionScope.memberId }</p>
    </c:if>
	
	<hr>
	<h1>결제창입니다.</h1>
		<div>
			<p>상품명 : </p>
			<input type="text" name="productName" value="${vo.productName }" readonly="readonly">
		</div>
		<hr>
	
	<form action="orderlist" method="POST">
		<div>
			<p>접속중인 아이디 : </p>
			<input type="text" name="memberId" value="${sessionScope.memberId }" readonly="readonly">
		</div>
		<div>
			<input type="hidden" name="productId" value="${vo.productId }" readonly="readonly">
		</div>		
		<div>
			<p>상품1개당 가격 : </p>
			<input type="number" name="productPrice" value="${vo.productPrice }" id="productPrice" readonly="readonly">
		</div>
		<div>
			<p>상품갯수 : </p>
			<input type="number" name="productQuantity" id="productQuantity" oninput="calculateTotalPrice()" min="1"><br>
		</div>
		<div>
			<input type="submit" value="구매">
		</div>
	</form>
		<div>
			<p>상품 총 가격 : </p>
			<input type="number" name="totalPrice" id="totalPrice" readonly="readonly">
		</div>
		
	
	<script type="text/javascript">
		function calculateTotalPrice() {
			const productQuantity = document.getElementById('productQuantity').value;
			const productPrice = document.getElementById('productPrice').value;
			const totalPrice = productQuantity * productPrice;
			document.getElementById('totalPrice').value = totalPrice;
		}
	</script>
	
	
</body>
</html>