<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <c:set var="path" value="${pageContext.request.contextPath}"/>
    <%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
	<%@ include file="/WEB-INF/views/includes/headerTest.jsp" %>

    
    <script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
    
    <script src="https://code.jquery.com/jquery-3.7.1.js"></script>
    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
   
<title>Insert title here</title>

<style type="text/css">


body {
    font-family: 'Noto Sans KR', sans-serif; 
    background-color: #eee;
    display: flex;
    justify-content: center;
    align-items: center;
    height: 100vh;
}

.signup-form {
    background-color: #ffffff;
    padding: 20px;
    border-radius: 5px;
    box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
    width: 350px;
}

.signup-form input {
    width: 100%;
    padding: 10px;
    margin-bottom: 10px;
    border: 1px solid #e0e0e0;
    border-radius: 5px;
    font-size: 14px;
}

.signup-form button {
    width: 100%;
    padding: 10px;
    border: none;
    border-radius: 5px;
    background-color: #007bff;
    color: #ffffff;
    font-size: 16px;
    cursor: pointer;
}

.signup-form button:hover {
    background-color: #0056b3;
}
.btn {
    display: inline-block;
    font-weight: 400;
    text-align: center;
    white-space: nowrap;
    vertical-align: middle;
    -webkit-user-select: none;
    -moz-user-select: none;
    -ms-user-select: none;
    user-select: none;
    border: 1px solid transparent;
    padding: .375rem .75rem;
    font-size: 5rem;
    line-height: 1.5;
    border-radius: .25rem;
    transition: color .15s ease-in-out, background-color .15s ease-in-out, border-color .15s ease-in-out, box-shadow .15s ease-in-out;
}

.btn-lg {
    padding: .5rem 1rem;
    font-size: 2rem; ;
    line-height: 1.5;
    border-radius: .3rem;
}

.btn-primary {
    color: #fff;
    background-color: #007bff;
    border-color: #007bff;
}

.btn-primary:hover {
    color: #fff;
    background-color: #0056b3;
    border-color: #004085;
}

.btn-block {
    display: block;
    width: 100%;
}
</style>
</head>
<body>
 <div class="signup-form">
	<form action="update" method="POST">
	 <c:if test="${not empty error}"> 
       <div class="alert alert-danger" role="alert">수정 실패</div>
    </c:if>
	<input type="hidden" name="memberId" id ="memberId" value="${pageContext.request.userPrincipal.name}" required="required" placeholder="아이디"><br>
	<div id="checkedId"></div>
	<input type="text" name="name" required="required" value="${name }" placeholder="이름" readonly><br>
	<input type="text" id = "phone"name="phone" value="${phone}"required="required" placeholder="연락처"><br>
	<div id="phoneConfirm"></div>
	<input type="email" id="email" name=email value="${email }"required="required" placeholder="이메일"><br>
	<div>
	<input type="button" onclick="searchPost()" value="우편번호 찾기" class="post_btn"> <br>
	<input type="text" id="postcode" name="postcode" value="${postcode }" required="required" placeholder="우편번호">
	<input type="text" id="Address" name="address" value="${address }"class="d_form std" placeholder="주소">
	<input type="text" id="detailAddress" name="detailAddress" value = "${detailAddress }" class="d_form std" placeholder="상세주소">
	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
	</div>
	<input type="submit" onclick="checkAllConditions()" class="btn btn-lg btn-primary btn-block" value="정보수정">
	</form>
	</div>
	
	
<script>
	$(()=>{
		$('#phone').on('blur', function(){
			var phone = $('this').val();
			var regex = /^010(-?\d{4})(-?\d{4})$/;
			if(!regex.test(phone)){
				$('#phoneConfirm').text('연락처를 정확히 입력해주세요');
			}else{
				phoneCheck = true;
			}
		});//end phone.on

		function checkAllConditions() {
	    	var checkedUserContact = $('#phone').val();
	    	var checkedUserpostcode = $('#postcode').val();
	    	var checkedUserAddress = $('#Address').val();
	    	var checkedUserdetailAddress = $('#detailAddress').val();
	    	console.log(checkedUserContact);
	    	console.log(checkedUserpostcode);
	    	console.log(checkedUserAddress);
	    	console.log(checkedUserdetailAddress);
	    	
	    	if (!checkedUserContact == null|| 
	    		    !checkedUserpostcode == null || 
	    		    !checkedUserAddress == null || 
	    		    !checkedUserdetailAddress ==null) {
	    		    alert("정보를 다시 입력해주세요.");
	    		    event.preventDefault();
	    		}
		}//end checkAllConditions

		
	})//end document.ready
	function searchPost() {
	    new daum.Postcode({
	        oncomplete: (data) => {
	            console.log(data);
	            $('#postcode').val(data.zonecode);
	            $('#Address').val(data.roadAddress);
	            $('#detailAddress').val('');
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