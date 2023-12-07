<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ include file="/WEB-INF/views/includes/header.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>마이페이지</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<style type="text/css">
	.mytmall_table.def {
  width: 100%; 
  border-collapse: collapse; 
}

.mytmall_table.def th,
.mytmall_table.def td {
  border: 1px solid #000; 
  padding: 8px; 
  text-align: left; 
}

.mytmall_table.def th {
  background-color: #f2f2f2; 
}
</style>
</head>
<body>
<div class="container-fluid">
	<input type="hidden" id="isOauthLogin" value="${user.isOauthLogin }">
	
    <div class="row">
        <!-- 사이드바 메뉴 -->
       <jsp:include page="../includes/mypage-sidebar.jsp" />
        <!-- 메인 콘텐츠 -->
        <div class="col-md-10">
            <h2>마이 페이지</h2>
            <table class="table table-bordered">
        <tbody>
            <tr>
                <th scope="row">이름</th>
                <td><sec:authentication property="principal.name" /></td>
            </tr>
            <tr>
                <th scope="row">아이디</th>
                <td><sec:authentication property="principal.username" /></td>
            </tr>
            <tr>
                <th scope="row">비밀번호</th>
                <td>
                <c:if test="${user.isOauthLogin == 'N' }">			
				<button type="button" class ="btn btn-link modal-dialog-centered" data-bs-toggle="modal" data-bs-target="#myModal">비밀번호 변경</button>
                </c:if>
                </td>
            </tr>
            <tr>
                <th scope="row">주소</th>
                <td><sec:authentication property="principal.address" /></td>
            </tr>
            <tr>
                <th scope="row">연락처</th>
                <td>
                    전화 번호: <sec:authentication property="principal.phone" /><br>
                    이메일 주소: <sec:authentication property="principal.email" />
                </td>
            </tr>
        </tbody>
    </table>
    <a href="update" class="btn btn-primary ">정보 수정</a>
    <sec:authorize access="hasRole('ROLE_USER')">
  	<c:set var="username" value="${principal.username}" />
  	
	<td><a href="delete">
	<c:if test="${user.isOauthLogin == 'N' }">			
	<button type="button" class="btn btn-danger">회원탈퇴</button>
	</c:if>
	</a>
	
	<c:if test="${user.isOauthLogin == 'Y' }">
	<button type="button" class="btn btn-danger" onclick="userDelete(this)" data-member-id="${user.memberId}">회원탈퇴</button>
	</c:if>		
	
	</td>
	</sec:authorize>
       </div>
    </div>
</div>
<!-- 모달 -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="passwordModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
        <!-- 모달 헤더 -->
            <div class="modal-header">
                <h5 class="modal-title" id="passwordModalLabel">비밀번호 변경</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <!-- 모달 바디 -->
            <div class="modal-body">
				<form action="updatePassword" id="form" method="POST">
                        <input type="hidden" name="memberId" id="memberId" value="${pageContext.request.userPrincipal.name}">
                    <div class="form-group">
                        <label for="current-password">현재 비밀번호</label>
                        <input type="password" class="form-control" name="password" id="password" required>
                    </div>
                    <div class="form-group">
                        <label for="new-password">새 비밀번호</label>
                        <input type="password" class="form-control"name="newPassword" id="newPassword" required>
                    </div>
                    <div class="form-group">
                        <label for="confirm-password">새 비밀번호 확인</label>
                        <input type="password" class="form-control" name ="newPasswordConfirm" id="newPasswordConfirm" required>
                    </div>
                   	<hr>
                   	<div id="passwordText">
                   	</div>
                    <button type="submit" onclick="changePassword(event)" class="btn btn-primary">비밀번호 변경</button>
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
	    var csrfToken = $("#csrfToken").val();
		var headers = {
			        'Content-Type': 'application/json'
			    };
		headers['X-CSRF-TOKEN'] = csrfToken;
		var regex = /^(?=.*[a-zA-Z])(?=.*[0-9])[a-zA-Z0-9]{5,20}$/;
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
				headers : headers,
				data : JSON.stringify({ 
				"memberId": memberId,
				"password":password
			}),
			success : function(result){
				console.log('에이젝스 success', result);
				if(result == 1){
					alert('비밀번호 변경 성공.');
					$('#form').submit();
					$('#myModal').modal('hide');

				}else{
					alert('기존 비밀번호가 일치하지 않습니다.');
			        $('#passwordText').text('');
				}
			}//end success
			})//end ajax
	}
	
	
	function userDelete(element){
		$(this).prop('disabled', true);
		var memberId = $(element).data('member-id');
		var csrfToken = $('#csrfToken').val();
		var headers = {
				'Content-Type': 'application/json',
				'X-CSRF-TOKEN': csrfToken
		}
		
		var isDelete = confirm('정말 회원탈퇴를 하시겠습니까?');
		if(!isDelete){
			return
		}
		
		$.ajax({
			type:'DELETE',
			url: 'userDelete',
			headers:headers,
			data:JSON.stringify({
				"memberId":memberId,
			}),
			success: (result)=>{
				if(result === 'success'){
					alert("회원탈퇴에 성공했습니다");
					window.location.href="/mall";
				}else{
					alert('서버에 문제가 발생했습니다 다시 시도해주세요');
					$(this).prop('disabled', false);
				}
			},     
			 error: function (xhr, status, error) {
            alert("에러 발생: " + error);
        },
        complete: function () {
            button.prop('disabled', false); 
        }
    }); // end ajax
			
	}//end userDelete
</script>

<footer>
    <%@ include file="/WEB-INF/views/includes/footer.jsp" %>
</footer>
</body>
</html>