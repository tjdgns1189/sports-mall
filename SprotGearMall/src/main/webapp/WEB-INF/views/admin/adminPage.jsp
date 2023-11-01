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
    <div class="col-md-1 text-bg-secondary p-3 ">
      <h4 class="bg-secondary">상품 관리</h4>
      <ul class="nav flex-column">
        <li class="nav-item"><a href="#" class="nav-link">상품 목록</a></li>
        <li class="nav-item"><a href="#" class="nav-link">상품 등록</a></li>
        <li class="nav-item"><a href="#" class="nav-link">상품 삭제</a></li>
      </ul>
     	<hr>
      <h4 class="bg-secondary">멤버 관리</h4>
      <ul class="nav flex-column">
        <li class="nav-item"><a href="#" class="nav-link">멤버 목록</a></li>
        <li class="nav-item"><a href="#" class="nav-link">주문 관리</a></li>
      </ul>
    </div>

    <!-- 오른쪽 컨텐츠 -->
    <div class="col-md-9">
      <h1>테스트</h1>
    </div>
  </div>
</div>
	
</body>
</html>