<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
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
<head>
<script src="https://code.jquery.com/jquery-3.7.1.js"></script>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<a href="../qnaBoard/qnaBoard">qna게시판</a>
<a href="register"><input type="button" value="상품 등록"></a>
<hr>
<h1>상품목록리스트</h1>
	<table>
    	<thead>
    		<tr>
    		<!--  상품 헤드 -->
    			<th style="width : 60px">번호</th>
    			<th style="width : 100px">이름</th>
    			<th style="width : 100px">가격</th>
    			<th style="width : 100px">재고</th>
    			<th style="width : 100px">제조사</th>
    			<th style="width : 100px">이미지</th>
    			<th style="width : 100px">분류</th>
    		</tr>
    	</thead>
    	<tbody>
    	
    	<!-- 상품 목록 -->
    		<c:forEach var="vo" items="${list }"> 
    			<tr>
    				<td>${vo.productId }</td>
    				<td>${vo.productPrice }</td>
    				 <!--  사이트이동하는데 데이터보내면서-->
    				 <!-- productName이 아니라 Id써야됨 -->
    				<td><a href="detail?productId=${vo.productId }">${vo.productName }</a></td>
    				<td>${vo.productStock }</td>
    				<td>${vo.productMaker }</td>
    				<td>${vo.productImgPath }</td>
    				<td>${vo.productCategory }</td>
    			</tr>
    		</c:forEach>
    	</tbody>
    </table>
	<ul>	
		<c:if test="${pageMaker.hasPrev }">
			<li><a href="list?page=${pageMaker.startPageNo - 1 }">이전</a></li>
		</c:if>
		<c:forEach begin="${pageMaker.startPageNo }" end="${pageMakger.endPageNo }" var="num">
		<li><a href="list?page=${num }">${num }</a></li>
		</c:forEach>
		<c:if test="${pageMaker.hasNext }">
			<li><a href="list?page=${pageMaker.endPageNo + 1 }">다음</a></li>
		</c:if>
	</ul>

	<input type="hidden" id="insertAlert" value="${insert_result }">

	<script type="text/javascript">
		var result = $('#insertAlert').val();
		if(result == 'success') {
			alert('새 상품 등록 성공!');
		}
	</script>
	
</body>
</html>