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
  <div class="container mt-5">
    <h2 class="mb-4">상품 등록 페이지</h2>
    
    <form action="${pageContext.request.contextPath}/product/register" method="POST" enctype="multipart/form-data">
      <div class="form-group">
        <label for="productName">이름:</label>
        <input type="text" class="form-control" id="productName" name="productName" placeholder="상품 이름 입력" required>
      </div>
      <div class="form-group">
        <label for="productPrice">가격:</label>
        <input type="text" class="form-control" id="productPrice" name="productPrice" required>
      </div>
      <div class="form-group">
        <label for="productStock">재고:</label>
        <input type="text" class="form-control" id="productStock" name="productStock" required>
      </div>
      <div class="form-group">
        <label for="productMaker">제조사:</label>
        <input type="text" class="form-control" id="productMaker" name="productMaker" required>
      </div>
      <div class="form-group mb-3">
        <label for="fileItem" class="form-label">이미지:</label>
        <input type="file" class="form-control" id="fileItem" name="productImgPath">
      </div>
      
      <div class="form-group">
        <label for="productCategory">종류:</label>
        <input type="text" class="form-control" id="productCategory" name="productCategory" required>
      </div>
      <div class="form-group">
        <label for="productContent">상품 설명:</label>
        <textarea class="form-control" id="productContent" name="productContent"></textarea>
      </div>
      <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
      <button type="submit" class="btn btn-primary">등록</button>
    </form>
  </div>
</body>
</html>

