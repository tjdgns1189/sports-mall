<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/includes/header.jsp" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.7.1.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.18/summernote-bs4.min.js"></script>
</head>
<body>
<div class="container-fluid">
  <div class="row">
    <!-- 왼쪽 메뉴 -->
   	  <jsp:include page="../includes/admin-sidebar.jsp" />
   	

    <!-- 오른쪽 컨텐츠 -->
   <div id="contentDiv" class="col-md-9 container-fluid">
    </div>
  </div>
</div>

</body>

</html>