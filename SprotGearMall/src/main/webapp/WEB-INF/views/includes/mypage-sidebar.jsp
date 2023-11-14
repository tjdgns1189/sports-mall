<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="col-md-2">
    <div class="list-group">
        <a href="${pageContext.request.contextPath}/orders/orderlist" class="list-group-item list-group-item-action">구매 목록</a>
        <a href="${pageContext.request.contextPath}/member/likes" class="list-group-item list-group-item-action">찜한 상품</a>
        <a href="${pageContext.request.contextPath}/member/reviewList" class="list-group-item list-group-item-action">리뷰 관리</a>
        <a href="${pageContext.request.contextPath}/member/mypage" class="list-group-item list-group-item-action">내 정보</a>
    </div>
</div>