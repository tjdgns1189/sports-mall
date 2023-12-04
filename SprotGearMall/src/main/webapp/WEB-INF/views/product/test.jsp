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

function iamport(){
	var IMP = window.IMP;
	var csrfToken = $("#csrfToken").val();
	IMP.init("imp61481183");
		console.log('이거 실행되나');
	    IMP.request_pay({
	        pg : 'kakaopay',
	        pay_method : 'card',
	        merchant_uid : '',
	        name : 'productName',
	        amount : '60',
	        buyer_name: 'memberId',
	        buyer_postcode: '임시주소',
	        productId : 'productId', 
	        productQuantity : '3',
	        url : 'http://localhost:8080/mall/product/result'
	    }, function (rsp) {
            $.ajax({
                type: 'POST',
                headers: {
		            'Content-Type': 'application/json',
		            'X-CSRF-TOKEN': csrfToken
		        },
                url: 'result/'
            }).done(function(data) {
                if(rsp.paid_amount === data.response.amount){
                    alert("결제 성공");
                    console.log('rsp', rsp);
                } else {
                    alert("결제 실패");
                }
            });
        });
    }
</script>

</body>
</html>