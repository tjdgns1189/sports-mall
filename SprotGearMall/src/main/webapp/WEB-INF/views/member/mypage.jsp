<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
	<%@ include file="/WEB-INF/views/includes/headerTest.jsp" %>
	
	
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>마이페이지</title>
<script src="https://code.jquery.com/jquery-3.7.1.js"></script>
<meta name="viewport" content="width=device-width, initial-scale=1">
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
	<table class="mytmall_table def">
		<colgroup>
			<col width="159">
		</colgroup>
		<tbody>
			<tr>
				<th scope="row">이름</th>
				<td><sec:authentication property="principal.name" />
			</tr>
			<tr>
				<th scope="row">아이디</th>
				<td><sec:authentication property="principal.username" /></td>
			</tr>
			<tr>
				<th scope="row">비밀번호</th>
				<td><button type="button" data-bs-toggle="modal"data-bs-target="#myModal">비밀번호 변경</button></td>
			</tr>
			<tr>
				<th scope="row">주소</th>
				<td><sec:authentication property="principal.address" /></td>
			</tr>
			<tr>
				<th scope="row">연락처</th>
				<td class="contact">
					<dl class="info_list">
						<dt class="in_tit">전화 번호</dt>
						<dd><sec:authentication property="principal.phone" /></dd>
						<dt class="in_tit">이메일 주소</dt>
						<dd><sec:authentication property="principal.email" /></dd>
					</dl> <a href="update"><button type="button" ><span>변경하기</span></button> </a>
				</td>
				<sec:authorize access="hasRole('ROLE_USER')">
				<td><a href="delete"><button type="button">회원탈퇴</button></a></td>
				</sec:authorize>
			</tr>
		</tbody>
	</table>
	
	<div class="modal" id="myModal">
	<div class="modal-dialog">
		<div class="modal-content">
			<!-- Modal Header -->
			<div class="modal-header">
				<h4 class="modal-title">비밀번호 변경</h4>
				<button type="button" class="btn-close" data-bs-dismiss="modal"></button>
			</div>
			
			<!-- Modal body -->
			<div class="modal-body">
				<form action="updatePassword" id="form" method="POST">
				<input type="hidden" id="memberId" name="memberId" value="${pageContext.request.userPrincipal.name}">
				<input type="password" id="password" name="password" required placeholder="기존 비밀번호"><br>
				
				<input type="password" id="newPassword" name="newPassword" required placeholder="새 비밀번호"><br>
				<input type="password" id="newPasswordConfirm" name="newPasswordConfirm" required placeholder="새 비밀번호 확인"><br>
				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
				<hr><br>
				<div id="passwordText"></div>
				<input type="submit" id="btnSubmit" class="btn btn-primary" onclick="changePassword(event)"value="변경">
				<button type="button" class="btn btn-danger" 
				data-bs-dismiss="modal">취소</button>
				</form>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
	function changePassword(event){
		
		event.preventDefault();
		var memberId = $('#memberId').val();
		var password = $('#password').val();
		var newPassword = $('#newPassword').val();
		var newPasswordConfirm = $('#newPasswordConfirm').val();
		
		var regex = /^(?=.*[a-zA-Z])(?=.*[0-9])[a-zA-Z0-9]{8,20}$/;
	    if(!regex.test(password) ||
	    	!regex.test(newPassword)) {
	        $('#passwordText').text('비밀번호는 5~20자의 영문자와 숫자 조합이여야 합니다.');
	        return;
	    }
		
		
		  if (!password || !newPassword || !newPasswordConfirm) {
	            alert('모든 필드를 채워주세요.');
	            return;
	        }
		  
		  if (newPassword !== newPasswordConfirm) {
	            alert('새 비밀번호가 일치하지 않습니다.');
	            return;
	        }
		  
			$.ajax({
				type : 'POST',
				url : 'passwordCheck',
				headers : {'Content-Type' : 'application/json'},
			data : JSON.stringify({ 
				"memberId": memberId,
				"password":password
			}),
			success : function(result){
				console.log('에이젝스 success', result);
				if(result == 1){
					$('#form').submit();
				}else{
					alert('기존 비밀번호가 일치하지 않습니다.');
					return;
				}
			}//end success
			})//end ajax
		
	}
</script>
</body>
</html>