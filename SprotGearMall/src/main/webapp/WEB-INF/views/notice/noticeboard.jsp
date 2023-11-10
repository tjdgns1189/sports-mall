<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="/WEB-INF/views/includes/header.jsp" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항</title>

<style type="text/css">
tr th td{
	align:center;
}
</style>
</head>
<body>
<div class="container" style="max-width: 1000px;">
  <table class="table">
    <thead>
      <tr>
        <th class="text-center" style="width:80%">제목</th>
        <th class="text-center" style="width:20%">작성일</th>
      </tr>
    </thead>
    <tbody>
    <c:forEach var="vo" items="${list }">
      <tr style="height:60px">
        <td class="align-middle" style="margin-left:10px"><a href="detail?noticeId=${vo.noticeId }">${vo.noticeTitle }</a></td>
        <td class="text-center align-middle">${vo.noticeCreatedDate }</td>
         </tr>
      </c:forEach>
    </tbody>
  </table>
</div>
<a href="register"><button class="btn btn-primary">글작성</button></a>
</body>
</html>