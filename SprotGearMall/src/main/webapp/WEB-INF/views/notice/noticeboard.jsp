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



<div class="container-fluid">
    <div class="row">
        <!-- 사이드바 메뉴 -->
       <jsp:include page="../includes/qna-sidebar.jsp" />
        <!-- 메인 콘텐츠 -->
        <div class="col-md-10">
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
            <fmt:formatDate value="${vo.noticeCreatedDate}" pattern="yyyy-MM-dd" var="date"/>
        <td class="text-center align-middle">${date }</td>
         </tr>
      </c:forEach>
    </tbody>
  </table>
  
 
  
	<nav id="nav" aria-label="Page navigation example">
	<ul class="pagination">
		<c:if test="${pageMaker.hasPrev }">
			<li  class="page-item"><a  class="page-link" href="noticeboard?page=${pageMaker.startPageNo - 1 }">이전</a></li>
		</c:if>
		<c:forEach begin="${pageMaker.startPageNo }" end="${pageMaker.endPageNo }" var="num">
			<li class="page-item"><a class="page-link" href="noticeboard?page=${num }">${num }</a></li>	
		</c:forEach>
		<c:if test="${pageMaker.hasNext }">
			<li class="page-item"><a class="page-link" href="noticeboard?page=${pageMaker.endPageNo + 1 }">다음</a></li>
		</c:if>
	</ul>
	</nav>
	 <div style="text-align: right;">
    <sec:authorize access="hasRole('ROLE_ADMIN')">
      <a href="register"><button class="btn btn-primary">글작성</button></a>
    </sec:authorize>
  </div>
</div>
</div>
</div>

</body>
</html>