<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/includes/header.jsp" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div class="container-fluid">
  <div class="row">
    <!-- 왼쪽 메뉴 -->
    <div class="col-md-1 bg-light">
      <h4 class="bg-secondary">상품 관리</h4>
      <ul class="nav flex-column">

        <li class="nav-item">
          <a href="#" class="nav-link" onclick="loadContent('productList', 'contentDiv')">상품 목록</a>
        </li>
        <li class="nav-item">
          <a href="#" class="nav-link" onclick="loadContent('../product/register', 'contentDiv')">상품 등록</a>
        </li>
        
      </ul>
      <hr>
      <h4 class="bg-secondary">멤버 관리</h4>
      <ul class="nav flex-column">
        <li class="nav-item">
          <a href="#" class="nav-link" onclick="loadContent('memberList', 'contentDiv')">멤버 목록</a>
        </li>
        
        <li class="nav-item">
          <a href="#" class="nav-link" onclick="loadContent('orderManage', 'contentDiv')">주문 관리</a>
        </li>
        
        <li class="nav-item">
          <a href="/mall/admin/chat-list" class="nav-link">채팅 문의 확인</a>
        </li>
      </ul>
    </div>

    <!-- 오른쪽 컨텐츠 -->
   <div id="contentDiv" class="col-md-9 container-fluid">
    </div>
  </div>
</div>
	<script type="text/javascript">
	function loadContent(url, targetId) {
		console.log('loadContent 호출')
	    $.ajax({
	        url: url,
	        type: "GET",
	        success: function(result) {
	            $("#" + targetId).html(result);
	        },
	    });
	}
	</script>
</body>

</html>