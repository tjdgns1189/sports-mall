<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/includes/header.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>구매 결과</title>
<style type="text/css">

table, th, td, input{
   text-align : center;
   vertical-align: middle;
}

table {
    border-top: 2px solid #000; /* 두꺼운 선의 색상 및 두께를 설정하세요 */
}

</style>
</head>
<body>
<div align="center">
<h2>주문해주셔서 감사합니다</h2>
<h5>저희가 안전하게 배송해 드릴게요</h5>
<div class="container my-4">
<div class="row justify-content-center my-5">
        <div class="col-md-8">
            <h4 align="left">주문정보</h4>
            
            <table class="table">
                <tbody>
                    <tr>
                        <th scope="row">총 결제금액</th>
                        <td>
                        <input type="text" class="form-control-plaintext" 
                            name="productPrice" value="<c:out value='${vo.productQuantity * vo.productPrice}' /> 원" readonly>
                        </td>
                    </tr>
                    <tr>
                        <th scope="row" rowspan="3">배송지정보</th>
                        <td><sec:authentication property="principal.name" /></td>
                    </tr>
                    <tr>
                    	<td><sec:authentication property="principal.phone" /></td>
                    </tr>
                    <tr>                
                        <td><sec:authentication property="principal.address" /></td>
                    </tr>
                </tbody>
            </table>
        </div>
    </div>
    
    <br />
    <br />
    <br />

    
    <div class="row justify-content-center my-5">
        <div class="col-md-8">
            <h4 align="left">주문성공</h4>
            
            <table class="table">
                <tbody>
                    <tr>
                        <th scope="row">${productVO.productName }</th>
                        <td>
                        <img class="img-thumbnail" 
                            src="https://storage.googleapis.com/edu-mall-img/${productVO.productImgPath }"
                            alt="Product Image" style="width: 150px; height: auto;" />
                        </td>
                        <td>
                        <input type="text" class="form-control-plaintext" 
                            name="productPrice" value="${productVO.productPrice } 원" readonly>
                        </td>
                        <td>
                        <input type="text" class="form-control-plaintext" 
                            name="productQuantity" value="${vo.productQuantity } 개" readonly>
                        </td>
                    </tr>
                    
                </tbody>
            </table>
        </div>
    </div>
    


<a href="../orders/orderlist">
	<button type="button" class="btn btn-danger btn-lg">주문내역 가기</button>  
</a>
<a href="/mall">
	<button type="button" class="btn btn-secondary btn-lg">메인으로 가기</button>  
</a>
</div>
</div>
</body>
</html>