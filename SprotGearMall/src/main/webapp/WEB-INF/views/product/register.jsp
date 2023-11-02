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
			<input type="text" name="productCategory" required> 
		</div>
	      	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"><br>
		<div>
			<input type="submit" value="등록">
		</div>
	</form>
	
	<script>
	$(document).ready(function() {
	    /* 이미지 업로드 */
	    $("input[type='file']").on("change", function(e){
	    	
	    	let formData = new FormData();
	    	/* 파일에 접근하기 */
	        let fileInput = $('input[name="productImgPath"]');
			let fileList = fileInput[0].files;
			let fileObj = fileList[0];
			
			console.log("fileList : " + fileList);
			console.log("fileObj : " + fileObj);
			console.log("fileName : " + fileObj.name);
			console.log("fileSize : " + fileObj.size);
			console.log("fileType(MimeType) : " + fileObj.type);
			
			if(!fileCheck(fileObj.name, fileObj.size)){
				return false;
			}
			/* FormData 객체에 데이터를 추가 */
			formData.append("productImgPath", fileObj);
			
			/*  AJAX를 사용하여 준비된 데이터를 서버로 전송하는 코드
			
				 url : 서버로 요청을 보낼 url
				 processData : 서버로 전송할 데이터를 queryStirng 형태로 변환할지 여부
				 contentType : 서버로 전송되는 데이터의 content-type
				 data : 서버로 전송할 데이터
				 type : 서보 요청 타입(GET, POST)
				 dataType : 서버로부터 반환받을 데이터 타입
			*/
			$.ajax({
				url: '/mall/product/register',
		    	processData : false,
		    	contentType : false,
		    	data : formData,
		    	type : 'POST',
		    	dataType : 'json'
			});
			
	    });
	    
	    /*뷰(View) 단계에서 사용자가 선택 한 파일이 개발자가 허용하는 파일이 아닐 시에 
	    경고창과 함께 <input> change 이벤트 메서드에서 벗어나도록 구현
	    jpg, png 파일만 허용 & 파일의 크기는 1048576byte(1MB)의 크기만 허용 */
	    
	    let regex = new RegExp("(.*?)\.(jpg|png)$");
		let maxSize = 1048576; //1MB	
		
		function fileCheck(fileName, fileSize){

			if(fileSize >= maxSize){
				alert("파일 사이즈 초과");
				return false;
			}
				  
			if(!regex.test(fileName)){
				alert("해당 종류의 파일은 업로드할 수 없습니다.");
				return false;
			}
			
			return true;		
			
		}
	    

	});
	</script>
	
</body>
</html>

