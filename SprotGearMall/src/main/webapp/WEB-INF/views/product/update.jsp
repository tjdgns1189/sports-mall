<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${vo.productName }</title>
</head>
<body>
	<h2>상품 수정 페이지</h2>
	
	<form action="update" method="POST" enctype="multipart/form-data">
		<input type="hidden" name="page" value="${page }">
		<div>
			<p> 상품 번호 : ${vo.productId }</p>
			<input type="hidden" name="productId" value="${vo.productId }">
		</div>
		<div>
			<p>이름 : </p>
			<input type="text" name="productName" value="${vo.productName }">
		</div>
		<div>
			<p>가격 : </p>
			<input type="text" name="productPrice" value="${vo.productPrice }"> 
		</div>
		<div>
			<p>재고 : </p>
			<input type="text" name="productStock" value="${vo.productStock }">	
		</div>
		<div>
			<p>제조사 : </p>
			<input type="text" name="productMaker" value="${vo.productMaker }">
		</div>
		<div>
			<p>이미지 : </p>
			<img src="${vo.productImgPath }" alt="수정 전 이미지">
			<input type="file" name="productImgPath">
		</div>
		<div>
			<p>종류 : </p>
			<input type="text" name="productCategory">			
		</div>
		
		
	</form>
</body>
</html>