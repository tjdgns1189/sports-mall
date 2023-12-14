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

<title>공지사항 작성</title>
</head>
<body>

<div class="container">
   <c:if test="${empty vo }">
  <form action="register" method="POST" class="row justify-content-center">
    <div class="col-md-8 mb-3">
      <label for="title" class="form-label">제목 :</label>
      <input type="text" class="form-control" name="noticeTitle" id="title" required>
    </div>
    <input type="hidden" name="${_csrf.parameterName}" id ="csrfToken" value="${_csrf.token}">
    <div class="col-md-8 mb-3">
      <label for="noticeContent" class="form-label">공지사항 작성 :</label>
      <textarea name="noticeContent" class="form-control summernote" id="noticeContent" rows="10">
      </textarea>
    </div>
    <div class="col-md-6">
      <input type="submit" class="btn btn-primary" value="작성">
    </div>
  </form>
    </c:if>
    
    <c:if test="${not empty vo }">
     <form action="/mall/notice/update" method="POST" class="row justify-content-center">
    <div class="col-md-8 mb-3">
      <label for="title" class="form-label">제목 :</label>
      <input type="text" class="form-control" name="noticeTitle" id="title" value="${vo.noticeTitle }">
    </div>
    <input type="hidden" name="${_csrf.parameterName}" id ="csrfToken" value="${_csrf.token}">
    <input type="hidden" name="noticeId" id ="noticeId" value="${vo.noticeId}">
    <div class="col-md-8 mb-3">
      <label for="noticeContent" class="form-label">공지사항 작성 :</label>
      <textarea name="noticeContent" class="form-control summernote" id="noticeContent" rows="10">
      ${vo.noticeContent }
      </textarea>
    </div>
    <div class="col-md-6">
      <input type="submit" class="btn btn-primary" value="수정">
    </div>
  </form>
    
    </c:if>
</div>

<script>
$(document).ready(function() {
  $('#noticeContent').summernote({
    height: 400,
    minHeight: null,
    maxHeight: null,
    fontNames: ['맑은 고딕', 'Arial', 'Arial Black', 'Comic Sans MS', 'Courier New'],
    fontNamesIgnoreCheck: ['맑은 고딕'],
    defaultFontName: '맑은 고딕',
    callbacks: {
      onImageUpload: function(files) {
        var formData = new FormData();
        formData.append("upload", files[0]);

        $.ajax({
          url: '/mall/notice/uploadImg',
          method: 'POST',
          data: formData,
          contentType: false,
          processData: false,
          success: function(data) {
            if (data.url) {
              $('#noticeContent').summernote('insertImage', data.url);
            } else if (data.error) {
              alert(data.error); // 오류 메시지 표시
            }
          },
          error: function(jqXHR, textStatus, errorThrown) {
            alert("이미지 업로드 중 오류가 발생했습니다: " + textStatus);
          }
        });
      }
    }
  });
  
  $('form').submit(function (event) {
      var boardContent = $('#noticeContent').summernote('code').trim();
      if (!boardContent) {
          alert("본문을 입력해 주세요");
          event.preventDefault(); 
      }
  });	  
});//end document.ready
</script>
</body>
</html>