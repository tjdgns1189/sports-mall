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
   	  <jsp:include page="../includes/admin-sidebar.jsp" />
   	

    <!-- 오른쪽 컨텐츠 -->
   <div id="contentDiv" class="col-md-9 container-fluid">
   	<table class="table">
   		<thead>
   			<tr>
   				<th>아이디</th>
   				<th>권한</th>
   			</tr>
   		</thead>
   		<tbody>
   		<c:forEach var="vo" items=${list }>
   		<tr>
   			<td>${vo.memberId }</td>
   			<td>${vo.userGrade }</td>
   		</tr>
   		</c:forEach>
   	</tbody>
   	</table>
   
    </div>
  </div>
</div>
</body>
</html>