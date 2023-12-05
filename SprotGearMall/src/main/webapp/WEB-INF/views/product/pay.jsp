<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="/WEB-INF/views/includes/header.jsp" %>
    
<!DOCTYPE html>
  <html>
  <meta charset="UTF-8">
 <head>
 <title>테스트 결제창</title>
<script type="text/javascript" src="https://code.jquery.com/jquery-1.12.4.min.js" ></script>
<script src="https://cdn.iamport.kr/v1/iamport.js"></script>
  
<script type="text/javascript">
var IMP = window.IMP; 
IMP.init("imp06818128"); 
var today = new Date();   
var hours = today.getHours(); // 시
var minutes = today.getMinutes();  // 분
var seconds = today.getSeconds();  // 초
var milliseconds = today.getMilliseconds();
var makeMerchantUid = hours +  minutes + seconds + milliseconds;


function requestPay() {
    IMP.request_pay({
        pg : 'kcp.TC0ONETIME',
        pay_method : 'card',
        merchant_uid: "IMP"+makeMerchantUid, 
        name : '당근 10kg',
        amount : 1004,
        buyer_email : 'Iamport@chai.finance',
        buyer_name : '아임포트 기술지원팀',
        buyer_tel : '010-1234-5678',
        buyer_addr : '서울특별시 강남구 삼성동',
        buyer_postcode : '123-456'
    }, function (rsp) { // callback
        if (rsp.success) {
            console.log(rsp);
        } else {
            console.log(rsp);
        }
    });
}
</script>
</head>
  <body>
  <button onclick="requestPay()">결제하기</button> <!-- 결제하기 버튼 생성 -->

  </body>
  </html>