<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    
    
<!doctype html>
<script src="<c:url value="/resources/js/qna-sidebar.js" />"></script>
<div class="col-md-2">
    <div class="list-group">
    	<h2>고객 센터</h2><br>
    	<p><strong>고객상담</strong></p>
    	<a href="${pageContext.request.contextPath}/qnaBoard/qnaBoard?page=1" class="list-group-item list-group-item-action">문의 게시판</a>
		<a href="#" onclick="openChatPopup();" class="list-group-item list-group-item-action">채팅 상담</a>
    	<br><hr>
		<p><strong>나의 고객센터</strong></p>
		<a href="${pageContext.request.contextPath}/member/myqna" class="list-group-item list-group-item-action">상품 문의</a>
		
		<br><hr>
    	<p><strong>공지사항</strong></p>
        <a href="${pageContext.request.contextPath}/notice/noticeboard" class="list-group-item list-group-item-action">공지사항</a>
        <a href="${pageContext.request.contextPath}/event/eventboard" class="list-group-item list-group-item-action">이벤트</a>
        
    </div>
</div>

