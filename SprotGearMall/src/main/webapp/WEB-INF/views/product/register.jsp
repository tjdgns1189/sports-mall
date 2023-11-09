<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상품 등록 페이지</title>
<script src="https://code.jquery.com/jquery-3.7.1.js"></script>
</head>
<body>
	<h2>상품 등록 페이지</h2>
	
	
	<form action="${pageContext.request.contextPath}/product/register" method="POST" enctype="multipart/form-data">
		<div>
			<p>이름 : </p>
			<input type="text" name="productName" placeholder="상품 이름 입력" required>  
		</div>
		<div>
			<p>가격 : </p>
			<input type="text" name="productPrice" required>
		</div>
		<div>
			<p>재고 : </p>
			<input type="text" name="productStock" required>
		</div>
		<div>
			<p>제조사 : </p>
			<input type="text" name="productMaker" required>
		</div>
		<div>
			<p>이미지 :</p>
			<input type="file" id="fileItem" name="productImgPath">
		</div>
		<div>
			<p>종류 :</p>
			<input type="text" name="productCategory" required><br>
		</div>
			상품 설명 : <input type="text" name="productContent">
	      	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"><br>
		<div>
			<input type="submit" value="등록">
		</div>
	</form>
	
</body>
</html>

