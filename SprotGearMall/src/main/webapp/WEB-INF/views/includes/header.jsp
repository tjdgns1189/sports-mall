<%@ page language="java" contentType="text/html; charset=UTF-8"pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>


<c:url var="logoutUrl" value="/logout"/>
<style>
    color: #666;
    font-family: "Noto Sans KR", "Helvetica Neue", "Apple SD Gothic Neo", "맑은 고딕", "Malgun Gothic", "돋움", dotum, sans-serif;
    font-size: 14px;
    line-height: 1.5;
    letter-spacing: 0;
    margin: 0;
    padding: 0;
    position: relative;
    background: #fff;
    z-index: 100;
</style>
<c:url var="logoutUrl" value="/logout"/>

<div class="">
	<div class="inner">
		<h1 class="" data-log-actionid-area="header_menu">
			<span class=""><a href="index">스포츠몰</a></span>
		</h1>
		<div class="" id="tSearch">
			<form>
				<fieldset>
					<input type="text" class="" title="통합검색">
					<button type="submit" class="search_button">검색</button>
				</fieldset>
			</form>
		</div>
		
		<div class="c_gnb_usermenu">
			<h2 class="skip">나의 메뉴</h2>
			<ul>
				<li class="my" ><a href="member/mypage" class="menu">마이페이지</a></li>
				<li class="order"><a href=""class="menu">주문내역</a></li>
				<li class="cart"><a href="">장바구니</a></li>
				<li class="lately"><button class="menu" >최근 본 상품</button></li>
				<li class="lately"><a href="${logoutURL }"><button class="menu" >로그아웃</button></a></li>

			</ul>
		</div>
	</div>
</div>