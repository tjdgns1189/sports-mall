<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-/Y6pD6FV/Vv2HJnA6t+vslU6fwYXjCFtcEpHbNJ0lyAFsXTsjBbfaDjzALeQsN6M" crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.7.1.js"></script>
<link href="https://getbootstrap.com/docs/4.0/examples/signin/signin.css" rel="stylesheet" crossorigin="anonymous">
<link href="<c:url value="/resources/css/headers.css" />" rel="stylesheet">
<script src="<c:url value="/resources/js/headers.js" />"></script>


 <c:url var="logoutUrl" value="/logout"/>
	<main>
		<h1><a href="${pageContext.request.contextPath}/index">스포츠용품 쇼핑몰</a></h1>
	<div class="header-container">
    	<ul class="nav-list">
        	<li><a href="${pageContext.request.contextPath}/product/list">메뉴</a></li>
       		<li><a href="#">들어</a></li>
        	<li><a href="#">갈곳</a></li>
        	<li><a href="${pageContext.request.contextPath}/qnaBoard/qnaBoard">QNA</a></li>
    	</ul>
    
    <div class="search-bar">
		<form action="${pageContext.request.contextPath}/search" method="get">
        <input type="text" placeholder="검색창">
        <button id="searchBtn" class="menu-button">
        	<img src="<c:url value="/resources/img/search_icon.png" />" alt="검색" >
        </button>
	</form>
    </div>
    
  	<div class="menu">
    <button class="menu-button">
  		<img src="<c:url value="/resources/img/user-logo.png" />" alt="메뉴아이콘" />
  		</button>
    		<div class="dropdown">
        		<ul>
            	<sec:authorize access="isAnonymous()">
        			<li><a href="${pageContext.request.contextPath}/member/loginForm">로그인</a></li>
        			<li><a href="${pageContext.request.contextPath}/member/register">회원가입</a></li>
           		</sec:authorize>
          			<li><a href="#">주문내역</a></li>
        			<li><a href="${pageContext.request.contextPath}/member/mypage">내 정보</a></li>
        			<li><a href="#">고객센터</a></li>
        		<sec:authorize access="isAuthenticated()">
           			<li>
           			<form class="form-inline" action="${logoutUrl}" method="post">
          			<input type="submit" value="로그아웃" />
          			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
      				</form>
      				</li>
        		</sec:authorize>
        		</ul>
    		</div>
		</div>
	</div>
</main>

