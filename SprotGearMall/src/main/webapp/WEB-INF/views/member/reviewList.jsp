<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="/WEB-INF/views/includes/header.jsp" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>
	아코디언 테스트
	<div class="accordion" id="accordionExample">
	1div안
  	<div class="accordion-item">
  	2div안
    <h2 class="accordion-header">
    버튼위
      <button class="accordion-button" type="button" data-bs-toggle="collapse" data-bs-target="#collapseOne" aria-expanded="true" aria-controls="collapseOne">
      </button>
    </h2>
    <div id="collapseOne" class="accordion-collapse collapse show" data-bs-parent="#accordionExample">
      <div class="accordion-body">
        리뷰했던 텍스트 들어가는곳
      </div>
    </div>
  </div>
  </div>
</body>
</html>