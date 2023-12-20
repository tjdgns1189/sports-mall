<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ include file="/WEB-INF/views/includes/header.jsp" %>
<!DOCTYPE html>
<html>
<head>
<script src="https://code.jquery.com/jquery-3.7.1.js"></script>
<meta charset="UTF-8">
<title>글 작성 페이지</title>
</head>
<body>
<div class="container-fluid">
 <div class="row">
  <!-- 사이드바 메뉴 -->
  <jsp:include page="../includes/qna-sidebar.jsp" />
   <!-- 메인 콘텐츠 -->
   <div class="col-md-10">
   <h1>QNA자유게시판</h1>
	<h2>글 작성 페이지</h2>
	<form id="qnaUpdateForm" action="register" method="POST">
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
		<div>
			<p>제목 : </p>
			<input type="text" name="qnaBoardTitle" placeholder="제목 입력" required>
		</div>
		<div>
			<p>작성자 : </p>
			<input type="text" name="memberId" id="memberIdInput" readonly="readonly">
		</div>
		<div>
			<p>내용 : </p>
			<textarea rows="20" cols="120" name="qnaBoardContent" placeholder="비회원은 글 입력시 수정삭제가 불가능합니다."></textarea>
		</div>
		<div>
			<input type="submit" value="등록" onclick="validateForm()">
		</div>
	</form>
</div>
</div>
</div>


<script type="text/javascript">
function validateForm() {
    var content = $('#qnaUpdateForm textarea[name="qnaBoardContent"]').val();
    if (!content.trim()) {
        alert('내용을 입력하세요.');
        event.preventDefault(); // 폼 제출 막기
    }
    
    var memberId = $('#qnaUpdateForm input[name="memberId"]').val();
    if (!memberId.trim() || memberId.startsWith(' ')) {
        alert('작성자칸을 입력하세요.');
        event.preventDefault(); // 폼 제출 막기
    }
    
 	// 제목 글자 수 체크
    var title = $('#qnaUpdateForm input[name="qnaBoardTitle"]').val();
    if (!title.trim()) {
        alert('제목을 입력하세요.');
        event.preventDefault(); // 폼 제출 막기
    }
    
    // 제목 공백처리
    if (title.length > 20) {
        alert('제목이 20자를 초과했습니다.');
        event.preventDefault(); // 폼 제출 막기
    }

    // 내용 글자 수 체크
    if (content.length > 500) {
        alert('내용이 500자를 초과했습니다.');
        event.preventDefault(); // 폼 제출 막기
    }
    
 // 내용 글자 수 체크
    if (memberId.length > 50) {
        alert('아이디가 10자를 초과했습니다.');
        event.preventDefault(); // 폼 제출 막기
    }
}



//비회원을 위해 접속아이디없을때 작성자칸에 입력넣기
document.addEventListener("DOMContentLoaded", function () {
    var memberIdInput = document.getElementById("memberIdInput");
    var userPrincipalName = "${pageContext.request.userPrincipal.name}";
	
    // userPrincipalName이 값이 있으면 고정시키고, 없으면 편집 가능하도록 설정
    if (userPrincipalName) {
        memberIdInput.value = userPrincipalName;
    } else {
        memberIdInput.readOnly = false;
    }
});

</script>
</body>
</html>