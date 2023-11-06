<%@page import="org.springframework.security.core.context.SecurityContextHolder"%>
<%@page import="edu.spring.mall.domain.ProductVO" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<%@ include file="/WEB-INF/views/includes/header.jsp" %>
<!DOCTYPE html>
<html>
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

#final {
	border-bottom : 1px solid #ddd;
    width: 50%;
    height: 400px;
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

<div align="center">
	<br>
	<h4>${pageContext.request.userPrincipal.name}님의 결제창</h4>
	<br>
	
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
    
    <br>
    <br>
    <br>
    <br>
    <h4>배송지정보</h4>
    
    
    
    <div>
    <table id="addressTable">
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
                <td><input type="text" id="addressOpinion" style="width: 300px;"></td>
            </tr>
        </tbody>
    </table>
    </div>

    <br>
    <br>
    <br>
    <br>
    
    <h4>약관 동의</h4>
    <table id="final">
    	<tr>
    		<th><input type="checkbox">전체 동의하기</th>
    		<th rowspan="2">
    		주문자이름 확인<br>
    		주소확인<br>
			배송비확인
			</th>    		
    	</tr>
    	<tr>
    		<th rowspan="2">
    		<input type="checkbox">개인정보 제3자 제공에 동의합니다. 상세보기<br>
    		<input type="checkbox">결제대행서비스 이용약관에 동의합니다. 상세보기<br>
    		<input type="checkbox">주문할 상품설명에 명시된 내용과 사용조건을 확인하였으며, 취소. 환불규정에 동의합니다.
    		</th> 		
    	</tr>
    	<tr>
    		<th>
    			총 상품금액<br>
    			<input type="number" name="totalPrice" id="totalPrice1" readonly="readonly"> 원 
    		</th>    		
    	</tr>
    </table>
    
    <br>

    <form action="../orders/orderlist" method="POST">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
        <input type="hidden" name="memberId" value="${pageContext.request.userPrincipal.name}" readonly="readonly">
        <input type="hidden" name="productId" value="${vo.productId }" readonly="readonly">
        <input type="hidden" name="productQuantity" id="productQuantity1" readonly="readonly">
        <input type="hidden" name="productPrice" id="productPrice1" readonly="readonly">
        <div style="text-align: center;">
        	<input type="submit" value="결제하기" style="background-color: white; font-size: 24px;">
    		<button style="background-color: white; width: 110px; font-size: 24px;">취소</button>  
        </div>
    </form>
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
	</script>
 


				</body>

				</html>


</div>
</body>
</html>

