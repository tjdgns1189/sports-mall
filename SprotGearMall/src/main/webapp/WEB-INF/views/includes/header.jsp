<%@page import="java.net.URLEncoder"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<link href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-icons/1.4.0/font/bootstrap-icons.min.css" rel="stylesheet">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
<script src="https://code.jquery.com/jquery-3.7.1.js"></script>
<script src="<c:url value="/resources/js/headers.js" />"></script>
<link href="<c:url value="/resources/css/headers.css" />" rel="stylesheet">
<input type="hidden" id="csrfToken" name="${_csrf.parameterName}" value="${_csrf.token}">

<main>
<!-- 헤더 첫번재줄 -->
<div class="bg-light py-2">
	<div class="container">
		<div class="row align-items-center">
			<!-- 로고 -->
			<div class="col">
				<a href="${pageContext.request.contextPath}/"> 
				<img src="${pageContext.request.contextPath}/resources/img/main-logo.png" alt="로고" class="logo">
				</a>
			</div>
		
			<!-- 검색창 -->
			<div class="col">
				<div class="search-bar d-flex">
					<form action="${pageContext.request.contextPath}/product/search" method="get">
						<input type="text" name="searchtext" placeholder="검색창">
						<button type="submit" id="searchBtn" class="menu-button">
							<img src="<c:url value="/resources/img/search_icon.png" />" alt="검색">
						</button>
					</form>
				</div>
			</div>
			<!-- 아이콘 -->
			<div class="col-auto">
				<div class="menu-container">
					<!-- 사용자 아이콘 -->
					<div class="menu ">
						<button id="btn-user" class="btn btn-icon" type="button">
							<i class="bi bi-person"></i>
						</button>
						<div class="dropdown-menu dropdown-content dropdown-menu-right width-120" aria-labelledby="dropdownMenuButton">
							<sec:authorize access="isAnonymous()">
							<a class="dropdown-item" href="#" onclick="targetURL()">로그인</a>
							<a class="dropdown-item" href="${pageContext.request.contextPath}/member/register">회원가입</a>
							</sec:authorize>
							<a class="dropdown-item" href="${pageContext.request.contextPath}/member/mypage">마이페이지</a>
							<a class="dropdown-item" href="${pageContext.request.contextPath}/orders/orderlist">주문내역</a>
							<a class="dropdown-item" href="${pageContext.request.contextPath}/qnaBoard/qnaBoard?page=1">고객센터</a>
							<sec:authorize access="hasRole('ROLE_ADMIN')">
								<a class="dropdown-item" href="${pageContext.request.contextPath}/admin/adminPage">관리자페이지</a>
							</sec:authorize>

							<sec:authorize access="isAuthenticated()">
								<div class="dropdown-divider"></div>
								<form action="${pageContext.request.contextPath}/logout" method="post">
								&nbsp;&nbsp;<input type="submit"class="btn btn-danger btn-block" value="로그아웃" /> 
								<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
								</form>
							</sec:authorize>
						</div>
					</div>

					<!-- 구매 관련 메뉴 -->
					<div class="menu width-120">
						<button id="btn-cart" class="btn btn-icon" type="button">
							<i class="bi bi-cart"></i>
						</button>
						<div class="dropdown-menu dropdown-content dropdown-menu-right width-120">
							<!-- 구매 관련 옵션들 -->
							<a class="dropdown-item" href="${pageContext.request.contextPath}/cart/cartlist">장바구니</a>
							<a class="dropdown-item" href="${pageContext.request.contextPath}/member/likes">찜한 상품</a>
							<a class="dropdown-item" href="${pageContext.request.contextPath}/product/recent">최근 본 상품</a>

						</div>
					</div>

					<!-- 알림 관련 메뉴 -->
					<div class="menu">
						<button id="btn-notification" class="btn btn-icon" type="button">
							<i class="bi bi-bell"></i> <span id="notificationBadge" class="position-absolute top-30 start-60 translate-middle p-2 bg-danger border border-light rounded-circle visually-hidden">
							</span>
						</button>
						<div id="drop-notification" class="dropdown-menu dropdown-content dropdown-menu-right dropdown-menu-notice">
							<!-- 알림 텍스트 들어갈곳 -->
							알림이 없습니다
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>


<!-- 네비바 두번재줄 -->
<div class="header-container header-color">
<ul class="nav-list">

		<li>
			<div class="navi_bar_area">
			
				<div class="p-dropdown">
				    <button class="dropbtn">축구 
				      <i class="fa fa-caret-down"></i>
				    </button>
				    <div class="p-dropdown-content">
				    	<a href="${pageContext.request.contextPath}/product/search?searchtext=축구공">축구공</a>
				    	<a href="${pageContext.request.contextPath}/product/search?searchtext=미니축구골대">미니축구골대</a>
				    	<a href="${pageContext.request.contextPath}/product/search?searchtext=골키퍼장갑">골키퍼장갑</a>
				    	<a href="${pageContext.request.contextPath}/product/search?searchtext=축구양말">축구양말</a>
				    	<a href="${pageContext.request.contextPath}/product/search?searchtext=축구유니폼">축구유니폼</a>	      		      		      
				    </div>			
				</div>
				
				<div class="p-dropdown">
				    <button class="dropbtn">농구 
				      <i class="fa fa-caret-down"></i>
				    </button>
				    <div class="p-dropdown-content">
				    	<a href="${pageContext.request.contextPath}/product/search?searchtext=농구공">농구공</a>
				    	<a href="${pageContext.request.contextPath}/product/search?searchtext=농구림">농구림</a>
				    	<a href="${pageContext.request.contextPath}/product/search?searchtext=농구골망">농구골망</a>
				    	<a href="${pageContext.request.contextPath}/product/search?searchtext=농구유니폼">농구유니폼</a>
				    </div>			
				</div>
				
				<div class="p-dropdown">
				    <button class="dropbtn">야구 
				      <i class="fa fa-caret-down"></i>
				    </button>
				    <div class="p-dropdown-content">
				    	<a href="${pageContext.request.contextPath}/product/search?searchtext=야구공">야구공</a>
				    	<a href="${pageContext.request.contextPath}/product/search?searchtext=야구방망이">야구방망이</a>
				    	<a href="${pageContext.request.contextPath}/product/search?searchtext=글러브">글러브</a>
				    	<a href="${pageContext.request.contextPath}/product/search?searchtext=베이스">베이스</a>
				    	<a href="${pageContext.request.contextPath}/product/search?searchtext=포수보호대">포수보호대</a>
				    	<a href="${pageContext.request.contextPath}/product/search?searchtext=야구모자">야구모자</a>
				    	<a href="${pageContext.request.contextPath}/product/search?searchtext=야구유니폼">야구유니폼</a>	      		      		      
				    </div>			
				</div>
				
			</div>
		</li>
		
	</ul>
</div>

</main>