<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/includes/header.jsp" %>
<!DOCTYPE html>
<html>
<head>
<%--아임포트 라이브러리--%>
<script src="https://code.jquery.com/jquery-3.7.1.js"></script>
<script type="text/javascript" src="https://cdn.iamport.kr/js/iamport.payment-1.2.0.js"></script>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<button onclick="iamport()" id="button">결제</button>

<script type="text/javascript">

function clickbutton(){
	
}

function iamport(){
    var IMP = window.IMP;
    var csrfToken = $("#csrfToken").val();
    IMP.init("imp61481183");
    console.log('이거 실행되나');
    IMP.request_pay({
        pg : 'kakaopay',
        pay_method : 'card',
        merchant_uid : 'hi' + new Date().getTime(), // merchant_uid를 적절히 설정
        name : 'productName',
        amount : '60',
        buyer_name: 'memberId',
        buyer_postcode: '임시주소',
        m_redirect_url : 'http://naver.com'
    }, function (rsp) {
        // 결제 완료 후의 처리
        var ordersVO = {
            memberId: rsp.buyer_name,
            productId: '1',
            productPrice: '60', 
            productQuantity: '3'
        };
        console.log('rsp', rsp);
        console.log('ordersVO' + ordersVO);

        $.ajax({
            type: 'POST',
            headers: {
                'Content-Type': 'application/json',
                'X-CSRF-TOKEN': csrfToken
            },
            url: 'paymentAPI',
            data: JSON.stringify(ordersVO),
            success: function (productPrice) {
                if (rsp.paid_amount === productPrice) {
                	
                    alert("결제 성공");
                    console.log('productPric' + productPrice);
                    console.log('rsp.paid_amount' + rsp.paid_amount);
                    window.location.href = '../orders/orderlist';
                } else {
                    alert("결제 실패");
                    console.log('실패');
                }
            },
            error: function () {
                alert("서버 오류");
            }
        });
    });
}
</script>

</body>
</html>