<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<style type="text/css">
table, th, td {
	border-style : solid;
	border-width : 1px;
	text-align : center;
}

ul {
	list-style-type : none;
}

li {
	display : inline-block;
}
</style>
<meta charset="UTF-8">
<script src="https://code.jquery.com/jquery-3.7.1.js" 
integrity="sha256-eKhayi8LEQwp4NKxN+CfCh+3qOVUtJn3QNZ0TciWLP4=" crossorigin="anonymous">
</script>
<title>Insert title here</title>
</head>
<body>
	<h1>QNA 게시판입니다</h1>

	<a href="qnaRegister"><input type="button" value="글 작성"></a>
	<hr>
	<table>
		<thead>
			<tr>
				<th style="width : 100px">게시글번호</th>
				<th style="width : 100px">작성자</th>
				<th style="width : 500px">게시글제목</th>
				<th style="width : 250px">작성일</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="vo" items="${list }">
				<tr>
					<td>${vo.qnaBoardId }</td>
					<td>${vo.memberId }</td>
					<td><a href="qnaDetail?qnaBoardId=${vo.qnaBoardId }&page=${pageMaker.criteria.page}">${vo.qnaBoardTitle }</a></td>
					<fmt:formatDate value="${vo.qnaBoardCreatedDate }"
					pattern="yyyy-MM-dd HH:mm:ss" var="qnaBoardCreatedDate"/>
					<td>${qnaBoardCreatedDate }</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<ul>
		<c:if test="${pageMaker.hasPrev }">
			<li><a href="qnaBoard?page=${pageMaker.startPageNo - 1 }">이전</a></li>
		</c:if>
		<c:forEach begin="${pageMaker.startPageNo }" end="${pageMaker.endPageNo }"
			var="num">
			<li><a href="qnaBoard?page=${num }">${num }</a></li>	
		</c:forEach>
		<c:if test="${pageMaker.hasNext }">
			<li><a href="qnaBoard?page=${pageMaker.endPageNo + 1 }">다음</a></li>
		</c:if>

	</ul>
</body>
</html>