<%@ page language="java" contentType="text/html; charset=UTF-8"pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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

<div class="b_header_gnb">
	<div class="inner">
		<h1 class="c_gnb_logo" data-log-actionid-area="header_menu">
			<span class="bi_11st"><a href="index">스포츠몰</a></span>
		</h1>
		<div class="c_gnb_search c_gnb_search_tab" id="tSearch">
			<form>
				<fieldset>
					<div class="c_gnb_search_listbox ">
						<button type="button" class="listbox_button" aria-haspopup="listbox" aria-labelledby="ar-listbox-label 통합검색" id="ar-listbox-button" aria-expanded="false"data-log-actionid-area="searchbox" data-log-actionid-label="vertical" data-log-body="{&quot;btn_name&quot;:&quot;통합검색&quot;,&quot;check_value&quot;:&quot;open&quot;}">통합검색</button>
					</div>
					<input type="text" class="search_text search_text_ad" title="통합검색">
					<button type="submit" class="search_button">검색</button>
				</fieldset>
			</form>
		</div>
		
		<div class="c_gnb_usermenu">
			<h2 class="skip">나의 메뉴</h2>
			<ul>
				<li class="my" data-log-actionid-area="header_util" data-log-actionid-label="my11st"><a href="member/mypage" class="menu">마이페이지</a></li>
				<li class="order"><a href=""data-log-actionid-area="header_util" data-log-actionid-label="orderdlv_inquiry" class="menu">주문</a></li>
				<li class="cart"><a href="https://buy.11st.co.kr/cart/CartAction.tmall?method=getCartList" class="menu" data-log-actionid-area="header_util"data-log-actionid-label="cart">장바구니</a></li>
				<li class="lately"><button class="menu" aria-haspopup="dialog" aria-controls="gnbInventory">최근 본 상품</button></li>
				<li class="lately"><a href="/logout"><button class="menu" >로그아웃</button></a></li>
				
			</ul>
		</div>

	</div>
</div>