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
장바구니 ${vo.productId}


<!-- 
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
 -->

<table class="table">
    	<thead>
    		<tr>
    			<th>삭제</th>
    			<th>주문번호</th>
    			<th>상품이름</th>
    			<th>상품구매수량</th>
    			<th>상품 총 가격</th>
    			<th>구매 날짜</th>
    			<th>버튼 들어갈곳</th>
    		</tr>
    	</thead>
    	<tbody>
    		<c:forEach var="vo" items="${productList }"> 
    			<tr>
    				<td>${vo.productId }</td> 
    				<td>${vo.productName }</td> 				
    				<td>
						${vo.productPrice }
    				</td>
    				<td>${vo.productName }</td>
    				<td>${vo.productName }</td>
    				<td>${vo.productName }</td>

    			</tr>
    		</c:forEach>
    	</tbody>
    </table>
		
		
</body>
</html>