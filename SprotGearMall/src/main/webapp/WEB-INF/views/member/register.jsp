<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script src="https://code.jquery.com/jquery-3.7.1.js"></script>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
<title>회원가입</title>
<link href="<c:url value="/resources/css/register.css" />" rel="stylesheet"> 
</head>

<body>
<div>
	<h1 id="registerLogo"><a href="${pageContext.request.contextPath}/index">스포츠용품 쇼핑몰</a></h1>
	</div>
	<div class="container">
	    <div class="row justify-content-center">
	 <div class="col-md-6">
	<div class="signup-form .row">
		<form action="register" method="POST">
		    <input type="hidden" name="${_csrf.parameterName}" id ="csrfToken" value="${_csrf.token}">
			<input type="text" name="memberId" id="memberId" required="required"
				placeholder="아이디"><br>
			<div id="checkedId"></div>
			<input type="password" name="password" id="mainPassword"
				required="required" placeholder="비밀번호"><br> 
			<input type="password" id="confirmPassword" required="required"placeholder="비밀번호 확인"><br>
			<div id="pwConfirm"></div>
			<input type="text" name="name" required="required" placeholder="이름"><br>
			<input type="text" name="phone" id="phone" required="required" placeholder="연락처"><br>
			<div id="phoneConfirm"></div>
			<input type="email" name=email required="required" placeholder="이메일"><br>
			
			<div>
				<input type="button" onclick="searchPost()" value="우편번호 찾기" class="post_btn">
				<input type="text" id="postcode"name="postcode" required="required" placeholder="우편번호"> 
				<input type="text" id="Address" name="address" class="d_form std"placeholder="주소"> 
				<input type="text" id="detailAddress"name="detailAddress" class="d_form std" placeholder="상세주소">
				<input type="hidden" name="userGrade" value="ROLE_USER">

			</div>
			<input type="submit" class="btn btn-lg btn-primary btn-block"
				value="회원 가입">
			
		</form>
	</div>
	</div>
	</div>
	</div>


	<script>
	$(()=>{
		var idChecked = false;
		var passwordCheck = false;
		var phoneCheck = false;
		var csrfToken = $("#csrfToken").val();
	    var headers = {
	        'Content-Type': 'application/json'
	    };
	    headers['X-CSRF-TOKEN'] = csrfToken;
		$('#memberId').on('blur', function(){
			
			console.log("아이디체크");
			var memberId = $(this).val();
			
			var regex = /^[a-z\d]{5,20}$/;  // 5~20자 영문소문자, 숫자 조합
			if(!regex.test(memberId)) {
			    $('#checkedId').text('아이디는 5~20자의 영문소문자와 숫자 조합만 가능합니다.');
			    $('#checkedId').css("color", "red");
			    idChecked = false;
			    $('#memberId').css("border", "1px solid red");
			    return;
			}//end if()!regex.test(memberId)
			
			$.ajax({
				type : 'POST',
				url : 'checkid',
				headers : headers,
				data : JSON.stringify({ "memberId": memberId }),
				success : function(result){
					console.log(result);
					if(result == 0){
						$('#checkedId').text('사용 가능한 아이디 입니다');
					    $('#checkedId').css("color", "black");
						$('#memberId').css("border", "1px solid #e0e0e0");
						idChecked = true;
						checkAllConditions();
					}else{
						$('#checkedId').text('중복 아이디 입니다');
					    $('#checkedId').css("color", "red");
					    $('#memberId').css("border", "1px solid red");
						idChecked = false;
						checkAllConditions();
					}
				}//end success
				
			});//end ajax
		})//end memberId.on
		
		$('#confirmPassword').on('blur', function(){
			var pass1 = $('#mainPassword').val();
			var pass2 = $(this).val();

			if(pass1 === pass2){
				passwordCheck = true;
				$('#pwConfirm').text('');
				checkAllConditions();
			}else{
				passwordCheck = false;
				$('#pwConfirm').text('비밀번호가 일치하지 않습니다');
				
			}
		});//end confirmPassword.on
		
		$('#mainPassword').on('input', function(){
			var pass1 = $(this).val();
			var regex = /^(?=.*[a-zA-Z])(?=.*[0-9])[a-zA-Z0-9]{8,20}$/;
		    if(!regex.test(pass1)) {
		        $('#pwConfirm').text('비밀번호는 5~20자의 영문자와 숫자 조합이여야 합니다.');
				passwordCheck = false;
		        return;
		    }
			if(passwordCheck== true){
				var pass2 = $('#confirmPassword').val();
				if(pass1 !== pass2){
					$('#pwConfirm').text('비밀번호가 일치하지 않습니다');
					passwordCheck = false;
				}
			}
		});//end mainPassword.on
		
		$('#phone').on('blur', function(){
			var phone = $('this').val();
			var regex = /^010(-?\d{4})(-?\d{4})$/;
			if(!regex.test(phone)){
				$('#phoneConfirm').text('연락처를 정확히 입력해주세요');
				phoneCheck = false;
			}else{
				$('#phoneConfirm').text('');
				phoneCheck = true;
				checkAllConditions();
			}
		});//end phone.on

	
	function checkAllConditions() {
	    if (idChecked && passwordCheck && phoneCheck) {
	        $('#submitButton').prop('disabled', false); // 버튼 활성화
	    } else {
	        $('#submitButton').prop('disabled', true); // 버튼 비활성화
	    }
	}//end checkAllConditions
		
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