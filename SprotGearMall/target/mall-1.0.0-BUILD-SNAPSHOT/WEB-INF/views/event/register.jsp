<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/includes/header.jsp" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.css" rel="stylesheet">
<!-- jQuery 추가 -->
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.js"></script>

<title>이벤트 게시글 작성</title>
</head>
<body>
<div class="container">
   <c:if test="${empty vo }">
  <form action="register" method="POST" class="row justify-content-center">
    <div class="col-md-8 mb-3">
      <label for="title" class="form-label">제목 :</label>
      <input type="text" class="form-control" name="eventBoardTitle" id="title">
    </div>
    <input type="hidden" name="${_csrf.parameterName}" id ="csrfToken" value="${_csrf.token}">
    <div class="col-md-8 mb-3">
      <label for="eventBoardContent" class="form-label">이벤트 글 작성 :</label>
      <textarea name="eventBoardContent" class="form-control summernote" id="eventBoardContent" rows="10">
      </textarea>
    </div>
    <div class="col-md-6">
      <input type="submit" class="btn btn-primary" value="작성">
    </div>
  </form>
 	</c:if>
 	
	 	<c:if test="${not empty vo }">
	     <form action="/mall/event/update" method="POST" class="row justify-content-center">
	    <div class="col-md-8 mb-3">
	      <label for="title" class="form-label">제목 :</label>
	      <input type="text" class="form-control" name="eventBoardTitle" id="title" value="${vo.eventBoardTitle }">
	    </div>
	    <input type="hidden" name="${_csrf.parameterName}" id ="csrfToken" value="${_csrf.token}">
	    <input type="hidden" name="eventBoardId" id ="eventBoardId" value="${vo.eventBoardId}">
	    <div class="col-md-8 mb-3">
	      <label for="eventBoardContent" class="form-label">이벤트 글 작성 :</label>
	      <textarea name="eventBoardContent" class="form-control summernote" id="eventBoardContent" rows="10">
	      ${vo.eventBoardContent }
	      </textarea>
	    </div>
	    <div class="col-md-6">
	      <input type="submit" class="btn btn-primary" value="수정">
	    </div>
	  </form>
    
    </c:if>
 	
 	
 	
 	
 	</div>
 	
 	<script type="text/javascript">
 	$(document).ready(function() {
 		  $('#eventBoardContent').summernote({
 		    height: 400,
 		    minHeight: null,
 		    maxHeight: null
 		    })
 		    })
 	</script>
 	
</body>
</html>