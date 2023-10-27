<%@page import="edu.spring.mall.domain.ProductVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<!DOCTYPE html>
<html>
<head>
<script src="https://code.jquery.com/jquery-3.7.1.js"></script>
<meta charset="UTF-8">
<title>${vo.productName }</title>
<link href="<c:url value="/resources/css/like.css" />" rel="stylesheet">

</head>
<body>
	<h2>상품 정보</h2>
	
	<div>
		<p>상품 번호 : ${vo.productId }</p>
	</div>
	<div>
		<p>상품 이름 : ${vo.productName } </p>
	</div>
	<div>
		<p>상품 가격 : ${vo.productPrice }</p>
	</div>
	<div>
		<p>상품 재고 : ${vo.productStock }</p>
	</div>
	<div>
		<p>상품 제조사 : ${vo.productMaker }</p>
	</div>
	
	<!-- 
	<div>
		<p>상품 이미지 : ${vo.productImgPath }</p>
	</div>
	 -->

	<div style="text-align">
		<button><a href="payment?productId=${vo.productId }">구매</button>
	</div>
	
	<a href="list?page=${page }"><input type="button" value="상품 목록"></a>
	<a href="update?productId=${vo.productId }&page=${page }"><input type="button" value="상품 수정"></a>
	<form action="delete" method="POST">
		<input type="hidden" id="productId" name="productId" value="${vo.productId }">
		<input type="hidden" id="memberId" name="memberId" value="${pageContext.request.userPrincipal.name}">

		<input type="submit" value="상품 삭제">
	</form>
	
	<button class="like-btn">
        <span class="heart"></span>
    </button>
	<script type="text/javascript">
	$(()=>{
		$('.like-btn').on('click',()=>{
			$isLike();
		})
	})
	function isLike() {
    var productId = $('#productId').val();
    var memberId = $('#memberId').val();

    if ($('.heart').hasClass('heart-filled')) {
        $.ajax({
            type: "DELETE", 
            url: '/likes',
            headers: { 'Content-Type': 'application/json' },
            data: JSON.stringify({
                "memberId": memberId,
                "productId": productId
            }),
            success: (result) => {
                console.log(result);
                if (result === 'success') {
                    $('.heart').toggleClass('heart-filled');
                }
            }
        });
    } else {
        $.ajax({
            type: "POST",
            url: '/likes',
            headers: { 'Content-Type': 'application/json' },
            data: JSON.stringify({
                "memberId": memberId,
                "productId": productId
            }),
            success: (result) => {
                console.log(result);
                if (result === 'success') {
                    $('.heart').toggleClass('heart-filled');
                }
            }
        });
    }
}
	</script>
</body>
	
</html>