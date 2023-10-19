<%@page import="edu.spring.mall.persistence.ProductDAO"%>
<%@page import="edu.spring.mall.domain.ProductVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
</style>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<!-- ${vo.getOrderProductName() } -->
<!--  ${vo.product.productName }-->
	<h1>${sessionScope.memberId }의 구입내역출력</h1>

	
		<table>
    	<thead>
    		<tr>
    			<th style="width : 60px">주문번호</th>
    			<th style="width : 100px">상품이름</th>
    			<th style="width : 100px">상품구매수량</th>
    			<th style="width : 100px">상품 총 가격</th>
    			<th style="width : 100px">구매 날짜</th>
    		</tr>
    	</thead>
    	<tbody>
    		<c:forEach var="vo" items="${list }"> 
    			<tr>
    				<td>${vo.orderId }</td> 				
    				<td>
						테이블 조인예정
    				</td>
    				<td>${vo.productQuantity }</td>
    				<td>${vo.productQuantity * vo.productPrice }</td>
    				<td>${vo.orderCreatedDate }</td>
    			</tr>
    		</c:forEach>
    	</tbody>
    </table>
</body>
</html>