<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <!-- 왼쪽 메뉴 -->
    <div class="col-md-2 bg-light">
      <h4 class="bg-secondary">상품 관리</h4>
      <ul class="nav flex-column">
        <li class="nav-item">
          <a href="${pageContext.request.contextPath}/admin/productList" class="nav-link">상품 목록</a>
        </li>
        <li class="nav-item">
          <a href="${pageContext.request.contextPath}/product/register" class="nav-link">상품 등록</a>
        </li>
      </ul>
      <h4 class="bg-secondary">멤버 관리</h4>
      <ul class="nav flex-column">
        <li class="nav-item">
          <a href="${pageContext.request.contextPath}/admin/user-list" class="nav-link">멤버 목록</a>
        </li>
        <li class="nav-item">
          <a href="${pageContext.request.contextPath}/admin/orderManage" class="nav-link" >주문 관리</a>
        </li>
        <li class="nav-item">
          <a href="${pageContext.request.contextPath}/admin/chat-list" class="nav-link">1:1 채팅문의</a>
        </li>
      </ul>
    </div>
