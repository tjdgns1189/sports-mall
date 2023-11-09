<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/includes/header.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="https://code.jquery.com/jquery-3.7.1.js"></script>
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
    		</tr>
    	</thead>
    	<tbody>
    		<c:forEach var="vo" items="${productVOList }"> 
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
    
    
    
	<!-- 처음 잘되는 코드 하나만.
    <script type="text/javascript">
    // 쿠키 값을 가져오는 함수
    function getCookie(name) {
        var value = "; " + document.cookie;
        var parts = value.split("; " + name + "=");
        if (parts.length == 2) return parts.pop().split(";").shift();
    }
    
    // 페이지 로드 시 실행할 함수
    window.onload = function() {
        // 쿠키에서 cartItems 값을 가져옴
        var cartItemsValue = getCookie("cartItems");
		console.log("cart에서 받았을때 몇개 : " + cartItemsValue);
        if (cartItemsValue) {
            // 쿠키에서 가져온 productId 문자열을 배열로 변환
            var productIds = cartItemsValue.split(",");

            // productId를 서버로 전송하여 ProductVO 목록을 가져옴
            var productVOList = [];

            for (var i = 0; i < productIds.length; i++) {
                var productId = parseInt(productIds[i]);
                // AJAX 요청을 사용하여 productId를 서버로 전송하고 ProductVO를 가져옴
                // 아래는 예시 코드, jQuery를 사용하는 경우
                $.ajax({
                    type: "GET",
                    url: "/cart", // 실제 서버 엔드포인트에 맞게 변경해야 함
                    data: { productId: productId },
                    success: function(data) {
                        if (data) {
                            productVOList.push(data);
                        }
                    }
                });
            }

            // productVOList를 이용하여 원하는 작업을 수행
        }
    }
    </script>
	-->

<!-- 두번째 잘되는 코드. 이것도 하나만
	<script type="text/javascript">
    // 쿠키 값을 가져오는 함수
    function getCookie(name) {
        var value = "; " + document.cookie;
        var parts = value.split("; " + name + "=");
        if (parts.length == 2) return parts.pop().split(";").shift();
    }

    // 페이지 로드 시 실행할 함수
    window.onload = function() {
        // 쿠키에서 cartItems 값을 가져옴
        var cartItemsValue = getCookie("cartItems");
        console.log("cart에서 받았을때 몇개 : " + cartItemsValue);
        if (cartItemsValue) {
            // 쿠키에서 가져온 productId 문자열을 배열로 변환
            var productIds = cartItemsValue.split(",");

            // Promise 배열 생성
            var promises = productIds.map(function(productId) {
                return new Promise(function(resolve, reject) {
                    // AJAX 요청을 사용하여 productId를 서버로 전송하고 ProductVO를 가져옴
                    // 아래는 예시 코드, jQuery를 사용하는 경우
                    $.ajax({
                        type: "GET",
                        url: "/cart", // 실제 서버 엔드포인트에 맞게 변경해야 함
                        data: { productId: productId },
                        success: function(data) {
                            if (data) {
                                resolve(data); // Promise를 성공 상태로 처리
                            } else {
                                reject("Error"); // Promise를 실패 상태로 처리
                            }
                        }
                    });
                });
            });

            // 모든 Promise를 병렬로 실행
            Promise.all(promises)
                .then(function(results) {
                    // 모든 Promise가 성공하면 results에는 ProductVO 목록이 들어 있음
                    var productVOList = results;
                    // productVOList를 이용하여 원하는 작업을 수행
                })
                .catch(function(error) {
                    console.error("Error: " + error);
                });
        }
    }
</script>
 -->

<!-- POST형식으로 시도
<script type="text/javascript">
    // 쿠키 값을 가져오는 함수
    function getCookie(name) {
        var value = "; " + document.cookie;
        var parts = value.split("; " + name + "=");
        if (parts.length == 2) return parts.pop().split(";").shift();
    }

    // 페이지 로드 시 실행할 함수
    window.onload = function() {
        // 쿠키에서 cartItems 값을 가져옴
        // cart.jsp에서 cartItems를 가져와 쿠키에서 cartItems 값을 가져옴
var cartItemsValue = getCookie("cartItems");

if (cartItemsValue) {
    // 쿠키에서 가져온 productId 문자열을 배열로 변환
    var productIds = cartItemsValue.split(",");

    // AJAX 요청을 사용하여 한 번에 모든 productIds를 서버로 전송
    $.ajax({
        type: "POST", // POST 방식으로 변경
        url: "/cart", // 실제 서버 엔드포인트에 맞게 변경해야 함
        data: { productIds: productIds },
        success: function(data) {
            if (data) {
                // 서버에서 반환한 데이터를 사용
                var productVOList = data;
                // productVOList를 이용하여 원하는 작업을 수행
            }
        }
    });


            // 모든 Promise를 병렬로 실행
            Promise.all(promises)
                .then(function(results) {
                    // 모든 Promise가 성공하면 results에는 ProductVO 목록이 들어 있음
                    var productVOList = results;
                    // productVOList를 이용하여 원하는 작업을 수행
                })
                .catch(function(error) {
                    console.error("Error: " + error);
                });
        }
    }
</script>
 -->
    
    
 
		
		
</body>
</html>