
<%@page import="edu.spring.mall.domain.ProductVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<script src="https://code.jquery.com/jquery-3.7.1.js"></script>
<meta charset="UTF-8">
<title>${vo.productName }</title>
</head>
<body>
	<h2>상품 정보</h2>
	
	<div>
		<p>상품 번호 : ${vo.productId }</p>
	</div>
	
	<div>
		<p>상품 이름 : ${vo.productName } </p>
	</div>
	
	<div>
		<p>상품 가격 : ${vo.productPrice }</p>
	</div>
	
	<div>
		<p>상품 재고 : ${vo.productStock }</p>
	</div>
	
	<div>
		<p>상품 제조사 : ${vo.productMaker }</p>
	</div>
	
	<!-- 
	<div>
		<p>상품 이미지 : ${vo.productImgPath }</p>
	</div>
	 -->

	<div style="text-align">
		<button><a href="payment?productId=${vo.productId }">구매</button>
	</div>
	
	<a href="list?page=${page }"><input type="button" value="상품 목록"></a>
	<a href="update?productName=${vo.productName }&page=${page }"><input type="button" value="상품 수정"></a>
	<form action="delete" method="POST">
		<input type="hidden" id="productName" name="productName" value="${vo.productName }">
		<!-- name 속성: name 속성은 HTML 요소의 이름 또는 키를 정의합니다. 
			 이 속성은 주로 서버로 데이터를 전송할 때 사용되며, 
			 여러 요소에서 동일한 이름을 가질 수 있습니다. 
			 주로 폼 요소에서 사용되며 서버 측에서 데이터를 구별하고 처리하는 데 사용됩니다.
		
			 id 속성: id 속성은 요소의 고유한 식별자를 나타냅니다. 
			 이 식별자는 주로 JavaScript 및 CSS에서 사용됩니다. 
			 각 요소는 동일한 문서 내에서 고유해야 합니다. 
			 id를 사용하여 요소에 대한 스타일을 적용하거나 
			 JavaScript에서 해당 요소를 선택하고 조작하는 데 사용됩니다. -->
		
		<!-- 이 태그는 "productName"이라는 이름의 숨김 필드를 생성하고, 
		이 필드의 값은 ${vo.productName}(예를들면 A상품)로 설정됩니다.
		이러한 숨김 필드는 주로 서버로 데이터를 전송하거나 클라이언트 측 JavaScript로 
		데이터를 전달할 때 사용됩니다. 사용자는 이 필드를 화면에서 볼 수 없으며, 
		데이터는 HTTP 요청을 통해 서버로 전송됩니다.
		 서버에서는 productName 키에 매핑된 값인 A를 사용하여 어떤 상품을 삭제할 것인지를 식별합니다. 이 값은 서버에서 상품을 데이터베이스에서 찾는 데 사용됩니다.
		따라서 서버는 productName 키가 A 값으로 설정되었기 때문에 데이터베이스에서 A 상품을 삭제하는 작업을 수행합니다.
		이런 식으로 사용자가 선택한 상품을 서버로 전달하고, 서버는 이 정보를 기반으로 데이터베이스 작업을 수행하여 해당 상품을 삭제합니다.  -->
		<input type="submit" value="상품 삭제">
	</form>
	
</body>

</html>