<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
    
    <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="register">
	<input type="text" name="memberId" id = "memberId" required="required" placeholder="아이디"><br>
	<input type="password" name="password" id = "password" required="required" placeholder="비밀번호"><br>
	<input type="text" name="name" required="required" placeholder="이름"><br>
	<input type="text" name="phone" required="required" placeholder="연락처"><br>
	<input type="email" name=email required="required" placeholder="이메일"><br>
	<div>
	<input type="text" id="postcode" required="required" placeholder="우편번호">
	<input type="button" onclick="searchPost()" value="우편번호 찾기" class="post_btn"><br>
	<input type="text" id="Address" class="d_form std" placeholder="주소">
	<input type="text" id="detailAddress" class="d_form std" placeholder="상세주소">
	<input type="hidden" name="userGrade" value="user">
	</div>
	<input type="submit" value="회원 가입">
	</form>
	
<script>
	$(()=>{
		
		
	})//end document.ready

	function searchPost() {
	    new daum.Postcode({
	        oncomplete: (data) => {
	            console.log(data);
	            $('#postcode').val(data.zonecode);
	            $('#Address').val(data.roadAddress);
	        },//end oncomplete
	        onclose: (state) => {
	            console.log(state);
	            if (state === 'COMPLETE_CLOSE') {
	                $('#postcode').attr('readonly', true);
	                $('#Address').attr('readonly', true);
	            }
	        }//end onclose
	    }).open();//end Postcode
	}//end searchPost
</script>
</body>
</html>