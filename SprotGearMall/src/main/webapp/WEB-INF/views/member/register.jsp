<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
    <script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
    
    <script src="https://code.jquery.com/jquery-3.7.1.js"></script>
    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="/css/register.css"> 
<title>Insert title here</title>
</head>
<body>
 <div class="signup-form">
	<form action="register" method="POST">
	<input type="text" name="memberId" id ="memberId" required="required" placeholder="아이디"><br>
	<div id="checkedId"></div>
	<input type="password" name="password" id="mainPassword" required="required" placeholder="비밀번호"><br>
	<input type="password"  id="confirmPassword" required="required" placeholder="비밀번호 확인"><br>
	<div id="pwConfirm"></div>
	<input type="text" name="name" required="required" placeholder="이름"><br>
	<input type="text" name="phone" required="required" placeholder="연락처"><br>
	<input type="email" name=email required="required" placeholder="이메일"><br>
	<div>
	<input type="text" id="postcode" name="postcode" required="required" placeholder="우편번호">
	<input type="button" onclick="searchPost()" value="우편번호 찾기" class="post_btn"><br>
	<input type="text" id="Address" name="address" class="d_form std" placeholder="주소">
	<input type="text" id="detailAddress" name="detailAddress" class="d_form std" placeholder="상세주소">
	<input type="hidden" name="userGrade" value="user">
	</div>
	<input type="submit" id="submitId" value="회원 가입">
	</form>
	</div>
	
	
<script>
	$(()=>{
		var idChecked = false;
		var passwordCheck = false;
		$('#memberId').on('blur', function(){
			var memberId = $(this).val();
			$.ajax({
				type : 'POST',
				url : 'checkid',
				headers : {
					'Content-Type' : 'application/json'
				},
				data : JSON.stringify({ "memberId": memberId }),
				success : function(result){
					console.log(result);
					if(result == 0){
						$('#checkedId').text('사용 가능한 아이디 입니다');
						idChecked = true;
					}else{
						$('#checkedId').text('중복 아이디 입니다');
						idChecked = false;
					}
				}//end success
			});//end ajax
		})//end memberId.on
		
		$('#confirmPassword').on('blur', function(){
			var pass1 = $('#mainPassword').val();
			var pass2 = $(this).val();
			console.log(pass1);
			console.log(pass2);
			
			if(pass1 === pass2){
				passwordCheck = true;
				$('#pwConfirm').text('');
			}else{
				passwordCheck = false;
				$('#pwConfirm').text('비밀번호가 일치하지 않습니다');
			}
		});//end confirmPassword.on
		
		$('#mainPassword').on('input', function(){
			var pass1 = $(this).val();
			if(passwordCheck== true){
				var pass2 = $('#confirmPassword').val();
				if(pass1 !== pass2){
					$('#pwConfirm').text('비밀번호가 일치하지 않습니다');
					passwordCheck = false;
				}
			}
		});//end mainPassword.on
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