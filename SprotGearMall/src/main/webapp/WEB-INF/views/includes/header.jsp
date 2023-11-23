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
		
	<button id="bookmarkButton">즐겨찾기에 추가</button>
	<!-- <button class="favorite" onclick="favorite()">즐겨찾기에 추가</button> -->
	
		
	<!-- 네비게이션 바 기능 -->
	<div>
	</div>
	<div class="header-container bg-secondary ">
	<ul class="nav-list">
		<li><a href="${pageContext.request.contextPath}/product/list">메뉴</a></li>
		<li><a href="${pageContext.request.contextPath}/notice/noticeboard?page=1">임시 공지사항</a></li>
		<li><a href="${pageContext.request.contextPath}/cart/cartlist">장바구니</a></li>
		<li><a href="${pageContext.request.contextPath}/qnaBoard/qnaBoard?page=1">QNA</a></li>
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
	<div class="search-bar">
		<form action="${pageContext.request.contextPath}/product/search" method="get">
        <input type="text" name="searchtext" placeholder="검색창">
        <button type="submit" id="searchBtn" class="menu-button">
        	<img src="<c:url value="/resources/img/search_icon.png" />" alt="검색" >
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
        <a class="dropdown-item" href="${pageContext.request.contextPath}/orders/orderlist">주문내역</a>
        <a class="dropdown-item" href="${pageContext.request.contextPath}/member/likes">찜한 상품</a>
        
        <a class="dropdown-item" href="#">고객센터</a>

        <sec:authorize access="hasRole('ROLE_ADMIN')">
       <a class="dropdown-item" href="${pageContext.request.contextPath}/admin/adminPage">관리자페이지</a>
        </sec:authorize>

        <sec:authorize access="isAuthenticated()">
            <div class="dropdown-divider"></div>
            <form action="${pageContext.request.contextPath}/logout" method="post">
                &nbsp;&nbsp;<input type="submit" class="btn btn-danger btn-block" value="로그아웃" />
                <input type="hidden" id="csrfToken"name="${_csrf.parameterName}" value="${_csrf.token}"/>
            </form>
        </sec:authorize>
    </div>
</div>
</div>

<script>
function addToFavorites() {
    var pageTitle = document.title; // 현재 페이지의 제목
    var pageURL = window.location.href; // 현재 페이지의 URL

    if (window.chrome && chrome.bookmarks) {
      chrome.bookmarks.create({
        title: pageTitle,
        url: pageURL
      }, function() {
        alert('즐겨찾기에 추가되었습니다.');
      });
    } else {
      alert('크롬 브라우저에서만 지원됩니다. 수동으로 추가해주세요.');
    }
  }

  document.getElementById('bookmarkButton').addEventListener('click', addToFavorites);
  
  /* function favorite(){
	         // 즐겨찾기 (모든 브라우저 호환)
	    $('.favorite').on('click', function(e) {
	        var bookmarkURL = window.location.href;
	        var bookmarkTitle = document.title;
	        var triggerDefault = false;
	        
	        if (window.sidebar && window.sidebar.addPanel) {
	            // Firefox version &lt; 23
	            window.sidebar.addPanel(bookmarkTitle, bookmarkURL, '');
	        } else if ((window.sidebar && (navigator.userAgent.toLowerCase().indexOf('firefox') < -1)) || (window.opera && window.print)) {
	            // Firefox version &gt;= 23 and Opera Hotlist
	            var $this = $(this);
	            $this.attr('href', bookmarkURL);
	            $this.attr('title', bookmarkTitle);
	            $this.attr('rel', 'sidebar');
	            $this.off(e);
	            triggerDefault = true;
	        } else if (window.external && ('AddFavorite' in window.external)) {
	            // IE Favorite
	            window.external.AddFavorite(bookmarkURL, bookmarkTitle);
	        } else {
	            // WebKit - Safari/Chrome
	            alert((navigator.userAgent.toLowerCase().indexOf('mac') != -1 ? 'Cmd' : 'Ctrl') + '+D 를 이용해 이 페이지를 즐겨찾기에 추가할 수 있습니다.');
	        }
	        return triggerDefault;
	    });
	} */
  
  
  </script>

</main>