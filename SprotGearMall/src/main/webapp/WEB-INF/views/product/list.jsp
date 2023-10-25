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
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<a href="../qnaBoard/qnaBoard">qna게시판</a>
<h1>상품목록리스트</h1>

	<table>
    	<thead>
    		<tr>
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
    		<c:forEach var="vo" items="${list }"> <!-- 서블릿에서 넘어온 list데이터. 어트리뷰트로. -->
    			<tr>
    				<td>${vo.productId }</td>
    				<td><a href="detail?productId=${vo.productId }">${vo.productName }</a></td> <!--  사이트이동하는데 데이터보내면서-->
    				<td>${vo.productPrice }</td>
    				<td>${vo.productStock }</td>
    				<td>${vo.productMaker }</td>
    				<td>${vo.productImgPath }</td>
    				<td>${vo.productCategory }</td>
    			</tr>
    		</c:forEach>
    	</tbody>
    </table>

</body>
</html>