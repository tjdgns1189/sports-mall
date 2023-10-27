<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
	<!DOCTYPE html>
	<html>

	<head>
		<meta charset="UTF-8">
		<title>상품 등록 페이지</title>
	</head>

	<body>
		<h2>상품 등록 페이지</h2>
		<!-- 일반적으로 "필드"와 "요소"라는 용어는 비슷한 의미로 사용되지만 
	약간의 차이가 있을 수 있습니다.
	
필드 (Field): "필드"는 주로 폼 (form) 요소를 가리키는 용어로 사용됩니다. 
폼 필드는 사용자로부터 정보를 입력받거나 선택하는 HTML 요소를 의미합니다. 
이러한 폼 필드에는 텍스트 입력 필드, 라디오 버튼, 체크박스, 드롭다운 목록 등이 포함됩니다. 
폼 필드의 값을 서버로 제출하거나 클라이언트 측 스크립트로 조작할 수 있습니다.

요소 (Element): "요소"는 HTML 문서 내의 모든 HTML 태그를 가리키는 더 일반적인 용어입니다. 
HTML 문서의 모든 요소는 태그로 시작하고 태그의 이름, 속성, 내용 등으로 구성됩니다. 
이러한 HTML 요소는 문서의 구조와 콘텐츠를 정의하며, 폼 필드뿐만 
아니라 헤더, 단락, 링크, 이미지, 스크립트 등을 포함합니다.

따라서 "필드"는 주로 폼과 관련이 있고 입력을 수신하는 특정 HTML 요소를 가리키며, 
"요소"는 HTML 문서 내의 모든 태그와 콘텐츠를 가리킵니다. -->

		<form action="register" method="POST" enctype="multipart/form-data">
			<div>
				<p>이름 : </p>
				<input type="text" name="productName" placeholder="상품 이름 입력" required>
			</div>

			<div>
				<p>가격 : </p>
				<input type="text" name="productPrice" value="판매자">
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
				<input type="file" name="productImgPath">
			</div>

			<div>
				<p>종류 :</p>
				<input type="text" name="productCategory">
			</div>

			<div>
				<input type="submit" value="등록">
			</div>

		</form>
	</body>

	</html>