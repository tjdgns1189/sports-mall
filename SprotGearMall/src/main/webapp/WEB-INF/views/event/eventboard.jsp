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
        <!-- 사이드바 메뉴 -->
       <jsp:include page="../includes/qna-sidebar.jsp" />
        <!-- 메인 콘텐츠 -->
        <div class="col-md-10">
       <a href="register"> <button class="btn btn-primary">등록버튼</button></a>
        <table class="table">
        <thead>
        <tr>
        	<th>번호</th>
        	<th>제목</th>
        	<th>작성자</th>
        	<th>날짜</th>
        	
        </tr>
        </thead>
        <tbody>
        	<c:forEach var="vo" items="${list }"> 
        	<tr>
        		<td>${vo.eventBoardId }</td>
        		<td><a href="detail?eventBoardId=${vo.eventBoardId }">${vo.eventBoardTitle }</a></td>
        		<td>${vo.memberId }</td>
        			<fmt:formatDate value="${vo.eventBoardCreatedDate}" pattern="yyyy-MM-dd" var="date"/>
        		<td>${date }</td>
        		
        	</tr>
        	</c:forEach>
        </tbody>
      
        </table>
        
        <nav id="nav" aria-label="Page navigation example">
	<ul class="pagination">
			<c:if test="${pageMaker.hasPrev }">
				<li  class="page-item"><a  class="page-link" href="eventboard?page=${pageMaker.startPageNo - 1 }">이전</a></li>
			</c:if>
			<c:forEach begin="${pageMaker.startPageNo }" end="${pageMaker.endPageNo }" var="num">
				<li class="page-item"><a class="page-link" href="eventboard?page=${num }">${num }</a></li>	
			</c:forEach>
			<c:if test="${pageMaker.hasNext }">
				<li class="page-item"><a class="page-link" href="eventboard?page=${pageMaker.endPageNo + 1 }">다음</a></li>
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
        
        <input type="hidden" id="insertAlert" value="${insert_result }">
        <script type="text/javascript">
		var result = $('#insertAlert').val();
		if (result == 'success') {
			alert('새 이벤트 글  등록 성공!');
		}
		</script>
</body>
</html>