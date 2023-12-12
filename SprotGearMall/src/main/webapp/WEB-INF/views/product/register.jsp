<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/includes/header.jsp" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상품 등록 페이지</title>
<link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.js"></script>

<style type="text/css">
	#result_card img{
		max-width: 100%;
	    height: auto;
	    display: block;
	    padding: 5px;
	    margin-top: 10px;
	    margin: auto;	
	}
	#result_card {
		position: relative;
	}
	.imgDeleteBtn{
	    position: absolute;
	    top: 0;
	    right: 5%;
	    background-color: #ef7d7d;
	    color: wheat;
	    font-weight: 900;
	    width: 30px;
	    height: 30px;
	    border-radius: 50%;
	    line-height: 26px;
	    text-align: center;
	    border: none;
	    display: block;
	    cursor: pointer;	
	}
	
.category-dropbtn {
    background-color: #808080;
    color: white;
    padding: 16px;
    font-size: 16px;
    border: none;
    
    
}
.category-dropdown {
    position: relative;
    display: inline-block;
}
.category-dropdown-content {
    display: none;
    position: relative;
    background-color: #f9f9f9;
    min-width: 160px;
    box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);
    z-index: 1;
}
.category-dropdown-content a {
    color: black;
    padding: 12px 16px;
    text-decoration: none;
    display: block;
}
.category-dropdown-content a:hover {
    background-color: #f1f1f1
}
.category-dropdown:hover .category-dropdown-content {
    display: block;
    position: absolute;
    top: 100%;
    left: 0;
    z-index: 1;
}
.category-dropdown:hover .category-dropbtn {
    background-color: #3e8e41;
}

.sub-links {
    display: none;
    position: absolute;
    background-color: #f9f9f9;
    min-width: 160px;
    box-shadow: 0px 8px 16px 0px rgba(0, 0, 0, 0.2);
    z-index: 1;
    top: 0;
    left: 100%;
}

.category-dropdown-content a:hover + .sub-links,
.sub-links:hover {
    display: block;
}

.sub-links a {
    position: relative;
}

.sub-links a:first-child::before {
    content: '';
    position: absolute;
    top: 0;
    left: 0;
    right: 0;
    height: 3px; /* Adjust this value based on the desired spacing */
    background-color: #f9f9f9;
    z-index: -1;   
}
	
.form-control {
    margin-top: 20px; /* 적절한 간격 값으로 변경해보세요 */
  }	
	
</style>
</head>
<body>
<div class="container-fluid">
  <div class="row">
    <!-- 왼쪽 메뉴 -->
   	  <jsp:include page="../includes/admin-sidebar.jsp" />
   	
    <!-- 오른쪽 컨텐츠 -->
  <div class="col-md-9 container-fluid">
    <h2 class="mb-4">상품 등록 페이지</h2>
    
    <form action="${pageContext.request.contextPath}/product/register" method="POST" enctype="multipart/form-data">
      <div class="form-group">
        <label for="productName">이름:</label>
        <input type="text" class="form-control" id="productName" name="productName">
      </div>
      <div class="form-group">
        <label for="productPrice">가격:</label>
        <input type="text" class="form-control" id="productPrice" name="productPrice">
      </div>
      <div class="form-group">
        <label for="productStock">재고:</label>
        <input type="text" class="form-control" id="productStock" name="productStock">
      </div>
      <div class="form-group">
        <label for="productMaker">제조사:</label>
        <input type="text" class="form-control" id="productMaker" name="productMaker">
      </div>
      <div class="form-group mb-3">
        <label for="fileItem" class="form-label">이미지:</label>
        <input type="file" class="form-control" id="fileItem" name="productImgPath" accept="image/png, image/jpeg, image/webp">
      </div>
      
      <div class="form-group">
    <label for="productCategory">종류:</label>
    <div class="category-dropdown">
        <span class="category-dropbtn">선택</span>
        <div class="category-dropdown-content">
            <a href="#" onclick="eventPreventDefault()">축구</a>
            <div class="sub-links">
                <a href="#" onclick="updateProductCategory('축구공')">축구공</a>
                <a href="#" onclick="updateProductCategory('축구골대')">축구골대</a>
                <a href="#" onclick="updateProductCategory('골키퍼장갑')">골키퍼장갑</a>
                <a href="#" onclick="updateProductCategory('축구양말')">축구양말</a>
                <a href="#" onclick="updateProductCategory('축구유니폼')">축구유니폼</a>
            </div>
            <a href="#" onclick="eventPreventDefault()">농구</a>
            <div class="sub-links">
                <a href="#" onclick="updateProductCategory('농구공')">농구공</a>
                <a href="#" onclick="updateProductCategory('농구화')">농구화</a>
                <a href="#" onclick="updateProductCategory('농구림')">농구림</a>
                <a href="#" onclick="updateProductCategory('농구골망')">농구골망</a>
                <a href="#" onclick="updateProductCategory('농구유니폼')">농구유니폼</a>
            </div>
            <a href="#" onclick="eventPreventDefault()">야구</a>
            <div class="sub-links">
                <a href="#" onclick="updateProductCategory('야구방망이')">야구방망이</a>
                <a href="#" onclick="updateProductCategory('글러브')">글러브</a>
                <a href="#" onclick="updateProductCategory('베이스')">베이스</a>
                <a href="#" onclick="updateProductCategory('포수보호대')">포수보호대</a>
                <a href="#" onclick="updateProductCategory('야구모자')">야구모자</a>
                <a href="#" onclick="updateProductCategory('야구유니폼')">야구유니폼</a>
                <a href="#" onclick="updateProductCategory('야구공')">야구공</a>
            </div>
        </div>
    </div>
    <input type="text" class="form-control" id="productCategory" name="productCategory" readonly>
</div>
      <div class="form-group">
        <label for="productContent">상품 설명:</label>
        <textarea class="form-control summernote" id="productContent" name="productContent" rows="10"></textarea>  
      </div>
      <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
      <button type="submit" class="btn btn-primary">등록</button>
    </form>
  </div>
   </div>
</div>

</body>

<script type="text/javascript">
$(()=>{
	$('#productContent').summernote({
    	height: 400,
    	minHeight: null,
   	 	maxHeight: null,
	})
	
	 // 등록 버튼 클릭 시 폼 검증
    $('form').submit(function (event) {
        // 필수 입력 필드의 값을 확인
        var productName = $('#productName').val();
        var productPrice = $('#productPrice').val();
        var productStock = $('#productStock').val();
        var productMaker = $('#productMaker').val();
        var productImgPath = $('#fileItem').val();
        var productCategory = $('#productCategory').val();
        var productContent = $('#productContent').summernote('code');

        // 필수 입력 필드 중 하나라도 비어있으면 경고 표시
        if (!productName || !productPrice || !productStock || !productMaker || !productImgPath || !productCategory || !productContent) {
            alert("빠진 입력 사항이 없는 지 확인해주세요.");
            event.preventDefault(); // 기본 동작을 막음
        }
    });
	
	
})

 function updateProductCategory(category) {
		event.preventDefault(); // 기본 동작을 막음
        document.getElementById('productCategory').value = category;
    }
    
function eventPreventDefault() {
	event.preventDefault(); // 기본 동작을 막음
}  


</script>
</html>

