<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>

<html>
<head>
<meta charset="UTF-8">
	<title>Home</title>

</head>
<body>
<header>
<%@ include file="/WEB-INF/views/includes/header.jsp" %>
</header>
<h1>
	Hello world! 
</h1>

<P>  The time on the server is ${serverTime}. </P>

<sec:authorize access="isAuthenticated()">
    로그인한 사용자: <sec:authentication property="principal.username"/>
    
    <c:url var="logoutUrl" value="/logout"/>
      <form class="form-inline" action="${logoutUrl}" method="post">
          <input type="submit" value="Log out" />
          <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
      </form>
	<a href="member/mypage" >마이페이지</a>
</sec:authorize>
<sec:authorize access="isAnonymous()">
<a href="member/loginForm">로그인</a>
<a href="member/register">회원가입</a>
</sec:authorize>


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


<footer>
    <%@ include file="/WEB-INF/views/includes/footer.jsp" %>
</footer>
</body>
</html>
