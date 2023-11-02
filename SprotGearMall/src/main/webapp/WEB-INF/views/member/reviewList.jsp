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

<c:if test="${not empty list }">
  <div class="accordion" id="accordionExample">
    <c:forEach var="vo" items="${list }" varStatus="status">
      <div class="card">
        <div class="card-header" id="heading${status.index}">
          <h5 class="mb-0">
            <button class="btn btn-link" type="button" data-bs-toggle="collapse" data-bs-target="#collapse${status.index}" aria-expanded="true" aria-controls="collapse${status.index}">
              상품명 : ${vo.product.productName} - ${vo.product.productPrice }원 - 별점 : ${vo.review.reviewRating }
            </button>
          </h5>
        </div>
        
        <div id="collapse${status.index}" class="collapse" aria-labelledby="heading${status.index}" data-bs-parent="#accordionExample">
          <div class="card-body">
            ${vo.review.reviewContent }
          </div>
        </div>
      </div>
    </c:forEach>
  </div>
</c:if>

</body>
</html>