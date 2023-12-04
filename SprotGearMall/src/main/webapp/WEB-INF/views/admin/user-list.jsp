<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="/WEB-INF/views/includes/header.jsp" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="container-fluid">
  <div class="row">
    <!-- 왼쪽 메뉴 -->
   	  <jsp:include page="../includes/admin-sidebar.jsp" />
   	

    <!-- 오른쪽 컨텐츠 -->
   <div id="contentDiv" class="col-md-9 container-fluid">
   	<table class="table">
   		<thead>
   			<tr>
   				<th>아이디</th>
   				<th>권한</th>
   				<th>탈퇴</th>
   			</tr>
   		</thead>
   		<tbody>
   		<c:forEach var="vo" items="${list }">
   		<tr id="tr-${vo.memberId}">
   			<td>${vo.memberId }</td>
   			<td>${vo.userGrade }</td>
   			<td><button class="btn btn-danger btn-delete" onclick="userDelete(this)" data-user-grade="${vo.userGrade }" data-member-id="${vo.memberId }">탈퇴</button></td>
   		</tr>
   		</c:forEach>
   	</tbody>
   	</table>
   
    </div>
  </div>
</div>
<script type="text/javascript">
	$(()=>{
		
	})//end document.ready
	
	function userDelete(element){
		var memberId = $(element).data('member-id');
		var memberGrade = $(element).data('user-grade');
		var csrfToken = $('#csrfToken').val();
		var isConfirm = confirm('해당 회원의 계정을 탈퇴하시겠습니까?');
		 var headers = {
		    		'Content-Type': 'application/json',
		    	     'X-CSRF-TOKEN': csrfToken
		    }
		if(memberGrade === 'ROLE_ADMIN'){
			alert('관리자 계정은 삭제할 수 없습니다');
			return;
		}
		
		if(!isConfirm){
			return;
		}
		
	    $(".btn-delete").prop('disabled', true);
	
	   
		
	    $.ajax({
	        url: '/mall/admin/deleteUser',  
	        type: 'Delete',
	        headers: headers,
	        data: JSON.stringify({ memberId: memberId }), 
	        success: function(result) {
	            console.log('reuslt:', result);
	            if(result == 'success'){
	            	alert('삭제 성공');
	            	$('#tr-' + memberId).remove();
	            }
	        },
	        error: function(error) {
	            console.log('Error:', error);
	            alert('삭제 실패');
	        },
	        complete: function() {
	            $(".btn-delete").prop('disabled', false);
	        }
	    });//end ajax
	}//end userDelete
</script>
</body>
</html>