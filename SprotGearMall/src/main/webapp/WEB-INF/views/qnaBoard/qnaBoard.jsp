<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/includes/header.jsp" %>
<!DOCTYPE html>
<html>
<head>

<style type="text/css">
table, th, td {
	border-style : solid;
	border-width : 1px;
	text-align : center;
}
#hi{
	width: 50%;

}	

ul {
	list-style-type : none;
}

li {
	display : inline-block;
}
</style>
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
	
	<input type="hidden" id="memberId" name="memberId" value="${memberId }">


	<a align="left" href="qnaRegister"><input type="button" value="글 작성"></a>
	<hr>
	<table id="hi" class="table table-striped table-hover" width="110">
		<thead>
			<tr>
				<th>게시글번호</th>
				<th>작성자</th>
				<th>게시글제목</th>
				<th>작성일</th>

			</tr>
		</thead>
		<tbody>
			<c:forEach var="vo" items="${list }">
				<tr>
					<td><a href="qnaDetail?qnaBoardId=${vo.qnaBoardId }&page=${pageMaker.criteria.page}&memberId=${memberId }">${vo.qnaBoardId }</a></td>
					<td>${vo.memberId }</td>
					<td><a href="qnaDetail?qnaBoardId=${vo.qnaBoardId }&page=${pageMaker.criteria.page}">${vo.qnaBoardTitle }</a></td>
					<fmt:formatDate value="${vo.qnaBoardCreatedDate }"
					pattern="yyyy-MM-dd HH:mm:ss" var="qnaBoardCreatedDate"/>
					<td>${qnaBoardCreatedDate }</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<nav id="nav">
	<ul class="pagination">
		<c:if test="${pageMaker.hasPrev }">
			<li  class="page-item"><a  class="page-link" href="qnaBoard?page=${pageMaker.startPageNo - 1 }">이전</a></li>
		</c:if>
		<c:forEach begin="${pageMaker.startPageNo }" end="${pageMaker.endPageNo }"
			var="num">
			<li class="page-item"><a class="page-link" href="qnaBoard?page=${num }">${num }</a></li>	
		</c:forEach>
		<c:if test="${pageMaker.hasNext }">
			<li class="page-item"><a class="page-link" href="qnaBoard?page=${pageMaker.endPageNo + 1 }">다음</a></li>
		</c:if>
	</ul>
	</nav>
</div>
</div>
</div>


<a href="chat"><button>채팅 문의</button></a>
</body>
</html>