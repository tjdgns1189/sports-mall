<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/includes/header.jsp"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>나의 문의</title>
<style type="text/css">
.btn-group {
    display: flex;
    align-items: center;
}

.form-inline {
    display: flex;
    margin-left: 5px; /* 필요한 경우 마진 조정 */
}
</style>
</head>
<body>
	<div class="container-fluid">
	  <div class="row">
        <!-- 사이드바 메뉴 -->
       <jsp:include page="../includes/qna-sidebar.jsp" />
        <!-- 메인 콘텐츠 -->
        <div class="col-md-10">
	
	
	<c:forEach var="qna" items="${list }">
		문의 번호 : ${qna.prdQnaId }<br>
		문의 상태 : ${qna.prdQnaState }<br>
		문의 작성자 : ${qna.memberId }<br>
		문의 시간 : ${qna.prdQnaCreatedDate }<br>
		문의 내용 : ${qna.prdQnaContent }<br>
		상품 번호 : ${qna.productId}<br>
		비밀글 여부 : ${qna.prdQnaSecret }<br>
    	<button class="btn btn-outline-secondary" onclick="updatePopup(this.getAttribute('data-prdQnaId'))" data-prdQnaId="${qna.prdQnaId }">수정</button>
    	<form action="deleteMyQna" method="POST" class="form-inline">
        	<input type="hidden" name="prdQnaId" value="${qna.prdQnaId }">
        	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
        	<input type="submit" class="btn btn-danger" value="삭제">
   		</form>
		<hr>
	</c:forEach>
	</div>
	</div>
	</div>
</body>
<script type="text/javascript">

	function updatePopup(prdQnaId){
		    var url = "/mall/product/prdQnaupdate?prdQnaId=" + prdQnaId;
		    var windowName = "상품 문의하기";
		    var windowSize = "width=800, height=600";
		    window.open(url, windowName, windowSize);
	}
</script>
</html>