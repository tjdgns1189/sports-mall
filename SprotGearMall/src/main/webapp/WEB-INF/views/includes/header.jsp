<%@page import="java.net.URLEncoder"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
<script src="https://code.jquery.com/jquery-3.7.1.js"></script>
<script src="<c:url value="/resources/js/headers.js" />"></script>
<link href="<c:url value="/resources/css/headers.css" />" rel="stylesheet">

	<main class="bg-secondary">
	<a href="${pageContext.request.contextPath}/"><img class="rounded shadow img-fluid logo" alt="메인 로고"
		src="${pageContext.request.contextPath}/resources/img/logo-removebg.png"></a>
	<!-- 네비게이션 바 기능 -->
	<div>
	</div>
	<div class="header-container bg-secondary ">
	<ul class="nav-list">
		<li><a href="${pageContext.request.contextPath}/product/list">메뉴</a></li>
		<li><a href="#">들어</a></li>
		<li><a href="#">갈곳</a></li>
		<li><a href="${pageContext.request.contextPath}/qnaBoard/qnaBoard?page=1">QNA</a></li>
	</ul>
	<div class="search-bar">
		<form action="#" method="get">
			<input type="text" placeholder="검색창">
			<button id="searchBtn" class="menu-button">
				<img src="<c:url value="/resources/img/search_icon.png" />" alt="검색">
			</button>
		</form>
	</div>
	<div class="menu">
		<button class="menu-button" type="button">
			<img src="<c:url value='/resources/img/user-logo.png' />" alt="메뉴아이콘" />
		</button>
	<div class="dropdown-menu dropdown-content dropdown-menu-right" aria-labelledby="dropdownMenuButton">
		<sec:authorize access="isAnonymous()">
			<a class="dropdown-item" href="#" onclick="targetURL()">로그인</a>
			<a class="dropdown-item" href="${pageContext.request.contextPath}/member/register">회원가입</a>
		</sec:authorize>
			<a class="dropdown-item" href="${pageContext.request.contextPath}/member/mypage">내 정보</a> 
			<a class="dropdown-item" href="${pageContext.request.contextPath}/orders/orderlist?memberId=${pageContext.request.userPrincipal.name}">주문내역</a>
			<a class="dropdown-item" href="${pageContext.request.contextPath}/member/likes">찜한 상품</a> 
			<a class="dropdown-item" href="#">고객센터</a>
		<sec:authorize access="hasRole('ROLE_ADMIN')">
			<a class="dropdown-item" href="${pageContext.request.contextPath}/admin/adminPage">관리자 페이지</a>
		</sec:authorize>
		<sec:authorize access="isAuthenticated()">
			<div class="dropdown-divider"></div>
			<form action="${pageContext.request.contextPath}/logout" method="post">
			&nbsp;&nbsp;<input type="submit" class="btn btn-danger btn-block" value="로그아웃" /> 
			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
		</form>
		</sec:authorize>


		</div>
	</div>
	</div>
</main>