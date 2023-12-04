<%@page import="edu.spring.mall.domain.ProductVO" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/views/includes/header.jsp" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>


<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>${product.productName}</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.0/font/bootstrap-icons.css" rel="stylesheet">
    <link href="<c:url value='/resources/css/detail.css' />" rel="stylesheet">
    <script src="<c:url value='/resources/js/detail.js' />"></script>
    <script>
        var isLiked = ${isLiked}; 
    </script>
    <style type="text/css">
   .small-column {
    width: 12%; 
   
}


.trsize {
    min-height: 50px; /* 원하는 최소 높이 */
    height: auto;
    overflow: hidden; /* 내용이 넘칠 경우 숨김 처리 */
    
    
}

.trsize td {
    padding: 10px; /* 원하는 패딩 값 */
}
.accordion-collapse {
    height: auto;
    overflow: hidden;
}

.table-head tr th{
text-align: center;

}
.my-column{
 white-space: nowrap;
    overflow: hidden;
    text-overflow: ellipsis;
}



.large-column {
    width: 52%;

}

.pre-line {
	height: auto;
    white-space: pre-line;
    overflow: hidden; /* 내용이 넘칠 경우 숨김 처리 */
    
}

.hidden-row {
    display: none;
}

.page-item.active .page-link {
    background-color: #007bff; 
    color: white; 
    font-weight: bold;
}
    </style>
</head>
<body>

	
    <div class="container">
        <!-- 이미지와 물건 정보 -->
        <div class="row">
            <!-- 이미지 들어가는곳 -->
            <div class="col-md-6">
                <div style="height: 400px;">
               
                    <img src="https://storage.googleapis.com/edu-mall-img/${product.productImgPath }" alt="Product Image" class="img-fluid h-100">
                </div>
            </div>
            <!-- 물건 정보 -->
            <div class="col-md-6">
                <h1>${product.productName}</h1>
                <p class="h6 my-2">제조사 : ${product.productMaker}</p>
                <hr>
                <p class="price h4 my-2">판매가 : <fmt:formatNumber value="${product.productPrice}" type="number" pattern="#,###"/>원</p>
                <hr>
                <p>리뷰수 <b>${reviewCount}</b><span class="dot"> 사용자 총 평점 <b>${avg}/5</b></span></p>
                <!-- 버튼 컨테이너 -->
                <div class="d-flex justify-content-between align-items-center mt-4 gx-2">
                    <!-- 구매하기 버튼 -->
                    <div class="col-6 px-0">
                        <a href="payment?productId=${product.productId}" class="btn btn-success btn-lg w-100">구매하기</a>
                    </div>
                    <!-- 좋아요 버튼 -->
                    <div class="col-3 px-1">
                        <button class="like-btn btn btn-outline-danger btn-lg w-100">
                            <span class="heart"></span>
                        </button>
                    </div>
                    <!-- 장바구니 버튼 -->
                    <div class="col-3 px-1">
                        <button class="btn btn-outline-primary btn-lg w-100" id="addToCart"><i class="bi bi-cart3"></i></button>
                    </div>
                </div>
            </div>
        </div>
        <!-- 상품 상세 정보 탭 -->
       <ul class="nav nav-tabs">
  			<li class="nav-item col-md-4 px-0">
    			<a class="nav-link active text-dark" data-bs-toggle="tab" href="#description">상품 상세설명</a>
  			</li>
  			<li class="nav-item col-md-4 px-0">
    			<a class="nav-link text-dark" data-bs-toggle="tab" href="#reviews">상품 리뷰</a>
  			</li>
  			<li class="nav-item col-md-4 px-0">
    			<a class="nav-link text-dark" data-bs-toggle="tab" href="#inquiry">상품 문의</a>
  			</li>
		</ul>


        <div class="tab-content">
            <!-- 상품 설명 -->
            <div class="tab-pane container active" id="description">
                ${product.productContent }
            </div>
            <!-- 리뷰 -->
            <div class="tab-pane container fade" id="reviews">
				별점 평균 : ${avg} <br>
                전체 리뷰(${reviewCount}) <br>
                <hr>
                <c:forEach var="reviewList" items="${review}">
                  	<div id="review-${reviewList.reviewId}">
                        <fmt:formatDate value="${reviewList.reviewCreatedDate}" pattern="yy.MM.dd" var="formattedDate"/>
                        ${reviewList.reviewRating} 점<br>
                        ${fn:substring(reviewList.memberId, 0, 3)}<c:forEach begin="1" end="${fn:length(reviewList.memberId) - 3}" var="i">*</c:forEach>
                        ${formattedDate}<br>
                        ${reviewList.reviewContent}<br>
       					<sec:authorize access="hasRole('ROLE_ADMIN')">
                    	<button class="btn btn-danger reviewDelete" data-review-id="${reviewList.reviewId }">삭제</button>
						</sec:authorize>                    
                    </div>
                    <hr>
                </c:forEach>
            </div>
            <!-- 상품 문의 -->
            <div class="tab-pane container fade my-column" id="inquiry">
            <table class="table">
            	<thead class= "table-head">
            	<tr>
            	<th class="small-column">문의유형</th>
            	<th class="small-column">답변상태</th>
            	<th class="large-column">문의/답변</th>
            	<th class="small-column">작성자</th>
            	<th class="small-column">작성일</th>
            	</tr>
            	</thead>
            	<tbody id="prdQnaBody">
            	<c:forEach var="qna" items="${qnaList }">
   					<tr id="accordion-${qna.qna.prdQnaId}" data-target="#accordion${qna.qna.prdQnaId}" class="accordion-toggle">
            			<td style="text-align: center;">${qna.qna.prdQnaCategory }</td>
            			<c:if test="${qna.qna.prdQnaState == 'Y'}" >
            			<td style="text-align: center;"><span class="state">답변완료</span></td>
            			</c:if>
            			<c:if test="${qna.qna.prdQnaState == 'N'}" >
            			<td style="text-align: center;"><span id="state-${qna.qna.prdQnaId}" class="state">미답변</span></td>
            			</c:if>
            			 <c:choose>
        					<c:when test="${qna.qna.prdQnaSecret == 0}">
           					 <td class="accordion-content">${qna.qna.prdQnaContent}</td>
        					</c:when>
  							<c:when test="${qna.qna.prdQnaSecret == 1 && (qna.qna.admin || qna.qna.author)}">
            				<td><i class="fa-solid fa-lock-open accordion-content"></i>${qna.qna.prdQnaContent}</td>
        					</c:when>
       						 <c:otherwise>
            				<td class="no-click accordion-content"><i class="fa-solid fa-lock"></i>비밀글입니다</td>
        					</c:otherwise>
    					</c:choose>
    					<td>${qna.qna.memberId }</td>
            			<fmt:formatDate value="${qna.qna.prdQnaCreatedDate }" pattern="yy.MM.dd" var="qnaDate"/>
            			<td style="text-align: center;">${qnaDate }</td>
            		</tr>
            		<!-- 아코디언 -->
    				<tr id="accordionContent-${qna.qna.prdQnaId }"class="hidden-row trsize">
       				 	<td colspan="5">
            			<div id="accordion${qna.qna.prdQnaId}" class="accordion-collapse collapse">
               			<div class="accordion-body pre-line">
                    <!-- Q&A 상세 내용 -->        
                   <c:if test="${qna.qna.prdQnaSecret == 0 || qna.qna.admin || qna.qna.author}">
                     ${qna.qna.prdQnaContent}    
                   <!-- 대댓글 기능 -->
                   	<div class="d-flex justify-content-end">
                   	
                   	<!-- 관리자일때 답변 -->
                    <sec:authorize access="hasRole('ROLE_ADMIN')">
						<button class="btn btn-secondary" onclick="answerArea(${qna.qna.prdQnaId}, '${qna.qna.memberId}')">답변</button>
					</sec:authorize>
					<!-- 작성자이면 수정 답변완료 상태일시 비활성화-->
					<c:if test="${qna.qna.author && qna.qna.prdQnaState == 'N'}">
						<button class="btn btn-outline-secondary" onclick="updatePopup(this.getAttribute('data-prdQnaId'))" data-prdQnaId="${qna.qna.prdQnaId }">수정</button>
					</c:if>
					<!-- 관리자거나 작성자이면 삭제 -->
					<c:if test="${qna.qna.admin || qna.qna.author}">
                    	<button class="btn btn-danger" onclick="qnaDelete(this)" data-qna-id="${qna.qna.prdQnaId }">삭제</button>
                    </c:if>
                    	</div>
                    </c:if>
                    <!-- 대댓글 시작점-->
                    <div id="replyContent-${qna.qna.prdQnaId}">
                    <c:if test="${qna.qna.prdQnaState == 'Y' }">
                    <hr>
                    <!-- 답변들어가는곳 -->
                    <div id="reply-${qna.reply.pqrId}" class="pre-line">
                    ${qna.reply.pqrContent }
                    <c:if test="${qna.qna.admin }">
                    <div class="d-flex justify-content-end">
						<button class="btn btn-outline-secondary" onclick="answerUpdate(${qna.reply.pqrId}, '${qna.reply.pqrContent}')">수정</button>
                    	<button class="btn btn-outline-danger" onclick="replyDelete(this)" data-qna-id="${qna.qna.prdQnaId }" data-pqr-id="${qna.reply.pqrId }">삭제</button>
                    </div>
                    </c:if>
                    
                    </div>
                    </c:if>
					</div>
                </div>
            </div>
            <!-- 답변 다는 공간 -->
        <div id="answer-${qna.qna.prdQnaId}" class="answer">
        </div>
        <div id="answer-update-${qna.reply.pqrId}" class="answer" >
        </div>
        </td>
    </tr>
           </c:forEach>
          </tbody>
         </table>
            <div style="text-align : right;">
           		<button class="btn btn-outline-primary" onclick="location.href='/mall/qnaBoard/qnaBoard'">고객센터 문의하기</button>
           		<button class="btn btn-primary" onclick="openPrdQnaPopup()">상품 문의하기</button>
           	</div>
           	<!-- 버튼 배치 -->
           	<nav id="nav">
				<ul class="pagination justify-content-center">
				 <c:if test="${pageMaker.hasPrev }">
					<li class="page-item"><a class="page-link" href="javascript:void(0);" data-page="${pageMaker.startPageNo - 1 }">이전</a></li>
					</c:if>
					<c:forEach begin="${pageMaker.startPageNo }" end="${pageMaker.endPageNo }" var="num">
					<li class="page-item ${num == pageMaker.criteria.page ? 'active' : ''}">
					<a class="page-link" href="javascript:void(0);" data-page="${num }">${num }</a></li>
					</c:forEach>
				<c:if test="${pageMaker.hasNext }">
					<li class="page-item"><a class="page-link" href="javascript:void(0);" data-page="${pageMaker.endPageNo + 1 }">다음</a></li>
				</c:if>
					
				</ul>
			</nav>
            </div>
        </div>
    </div>

    <a href="update?productId=${product.productId}&page=${page}"><input type="button" value="상품 수정"></a>
    <form action="delete" method="POST">
    	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
        <input type="hidden" id="productId" name="productId" value="${product.productId}">
        <input type="hidden" id="memberId" name="memberId" value="${pageContext.request.userPrincipal.name}">
        <input type="submit" value="상품 삭제">
    </form>
    

    
    
    
    
<script type="text/javascript">


$(()=>{
	document.getElementById('addToCart').addEventListener('click', function () {
	    // 필요한 데이터 가져오기
	    var memberId = "${pageContext.request.userPrincipal.name}";
	    var productId = "${product.productId}";
	    var productPrice = "${product.productPrice}";
	    var productQuantity = '1';
	    var csrfToken = $("#csrfToken").val();

	    // 서버로 보낼 데이터 객체 생성
	    var obj = {
	      'memberId' : memberId,
	      'productId' : productId,
	      'productPrice' : productPrice,
	      'productQuantity' : productQuantity
	    };
	    console.log(obj);

	    // 제품을 장바구니에 추가하기 위해 서버로 AJAX 요청 보내기
	    $.ajax({
	      type: 'POST',
	      url: '../cart/cartlists', // 서버 엔드포인트와 일치하도록 URL 업데이트
	      headers : {
				'Content-Type' : 'application/json',
				'X-CSRF-TOKEN': csrfToken
			},
	      data: JSON.stringify(obj),
	      contentType: 'application/json',
	      success: function (result) {
	    	console.log(result);
	    	if(result == 1) {
	        // 서버에서의 응답 처리 (예: 성공 메시지 표시)
	        alert('제품이 성공적으로 장바구니에 추가되었습니다.');
	    	} else {
	    	alert('에러');
	    	}
	      }
	    });
	  });
	
	$(document).on('click', '.page-link', function(e) {
	    e.preventDefault(); // 기본 동작(링크 이동) 방지
	    var pageNum = $(this).data('page');
	    var productId = $('#productId').val();
	    console.log("pageNum",pageNum);
	    loadPageContent(productId, pageNum);
	});

})//end document.ready
  
    function openPrdQnaPopup() {
    	var productId = $('#productId').val();
        var url = "/mall/product/prdQna?productId="+ productId;
        var windowName = "상품 문의하기";
        var windowSize = "width=800, height=600";
        console.log("productId :", productId );
        window.open(url, windowName, windowSize);
    }//end openPopup
    

$(document).on('click', '.accordion-toggle', function(event) {
    if ($(event.target).hasClass('no-click')) {
        event.preventDefault(); 
        return; 
    }
    var targetId = $(this).attr('data-target');
    var accordion = $(targetId);
    var accordionRow = accordion.closest('tr');

    // 현재 클릭된 아코디언이 닫혀있는 경우에만 실행
    if (!accordion.hasClass('show')) {
        // 모든 아코디언 닫기
        $('.accordion-collapse').removeClass('show');
        $('.accordion-collapse').closest('tr').addClass('hidden-row');

        // 클릭된 아코디언 열기
        accordion.addClass('show');
        accordionRow.removeClass('hidden-row');
    } else {
        // 클릭된 아코디언이 이미 열려있는 경우, 닫기
        accordion.removeClass('show');
        accordionRow.addClass('hidden-row');
    }
})//end accodion.on

//답변 취소
$(document).on('click', '.cancel-answer', function() {
    var answerDivId = $(this).data('answer-div-id');
    $('#' + answerDivId).empty(); 
});
    
    //문의 삭제
	   function qnaDelete(element) {
	        var prdQnaId = parseInt($(element).data('qna-id'),10);
	        var csrfToken = $('#csrfToken').val();
	        
	        var headers =  {
				'Content-Type' : 'application/json',
				'X-CSRF-TOKEN': csrfToken
			}
	        console.log('prdQnaId',prdQnaId);
	        console.log(typeof(prdQnaId));
	        confirm("문의를 삭제하겠습니까?");
	        if(!confirm){
	        	return;
	        }
	        $.ajax({
	            type: 'delete',
	            url: 'deleteQna',
	            headers: headers,
	            data: JSON.stringify({
	                "prdQnaId": prdQnaId
	            }),
	            statusCode:{
	                200: (result)=>{
	                    alert("삭제성공");
	                    $('#accordion-' + prdQnaId).closest('tr').remove();
	                    $('#accordionContent-' + prdQnaId).remove();
	                    $('#replyContent-' + prdQnaId).remove();
	                    },
	                400: (result)=>{
	                    alert("삭제 실패")
	                }
	            },
	            error: function(xhr, status, error) {
	            	
	        console.error('오류 발생:', error);
	    }
	        })//end ajax
	    }//end qnaDelete
	    
	    function loadPageContent(productId,pageNum){
	        var csrfToken = $('#csrfToken').val();
	        var url = 'prdQna?page=' + pageNum;
	        var headers={
	            'Content-Type' : 'application/json',
	    		'X-CSRF-TOKEN': csrfToken
	        }
	        $.ajax({
	            type: 'GET',
	            url: 'prdQnaPaging?page=' + pageNum + '&productId=' + productId,
	            headers : headers,
	            success: (result) =>{
	            	console.log("result", result)
	            	var qnaList = result.qnaList;
	            	var pageMaker = result.pageMaker;
	            	var isAdmin = result.isAdmin;
	                updateTableBody(qnaList, isAdmin);
	                updatePageItem(pageMaker);
	            },
	            error:(error) =>{
	                alert("에러 발생")
	            }

	        })//end ajax 
	      }//end loadPagecOntent
 
	function updateTableBody(qnaList, isAdmin){
		 var newTbodyContent = '';
		 qnaList.forEach(function(list) {

			 	console.log("QnAId:", list.qna.prdQnaId);
			    console.log("Reply:", list.reply);
		        newTbodyContent += '<tr id="accordion-' + list.qna.prdQnaId + '" data-target="#accordion' + list.qna.prdQnaId + '" class="accordion-toggle">';
		        newTbodyContent += '<td style="text-align: center;">' + list.qna.prdQnaCategory + '</td>';
		        
		        // 답변 상태에 따른 처리
		        newTbodyContent += '<td style="text-align: center;"><span class="' + (list.qna.prdQnaState == 'Y' ? 'state">답변완료' : 'state">미답변') + '</span></td>';

		        // 비밀글
		         if (list.qna.prdQnaSecret == 0) {
        				newTbodyContent += '<td class="accordion-content">' + list.qna.prdQnaContent + '</td>';
    			} else if (list.qna.prdQnaSecret == 1 &&(isAdmin ||list.qna.author)) {
        				newTbodyContent += '<td><i class="fa-solid fa-lock-open accordion-content"></i>' + list.qna.prdQnaContent + '</td>';
    			} else {
        			newTbodyContent += '<td class="no-click accordion-content"><i class="fa-solid fa-lock"></i>비밀글입니다</td>';
   				 }

		        // 작성자 정보 처리
		     	 newTbodyContent += '<td>' + list.qna.memberId +  '</td>';
		       
		        // 작성일 처리
		        newTbodyContent += '<td style="text-align: center;">' + formatDate(list.qna.prdQnaCreatedDate) + '</td>';
		        newTbodyContent += '</tr>';
		        
		        // 아코디언 내용
		        newTbodyContent += '<tr id="accordionContent-' + list.qna.prdQnaId + '" class="hidden-row trsize">';
		        newTbodyContent += '<td colspan="5">';
		        newTbodyContent += '<div id="accordion' + list.qna.prdQnaId + '" class="accordion-collapse collapse">';
		        newTbodyContent += '<div class="accordion-body pre-line">';
		     	
		        // 권한에 따라 비활성화 해야함
		        if(isAdmin ||list.qna.author || list.qna.prdQnaSecret == 0){
		        	newTbodyContent += list.qna.prdQnaContent + '<br><br>';
		        }
		     	
	        	newTbodyContent += '<div class="d-flex justify-content-end">'

		        // 버튼들 권한별로 배치
		        // 수정 버튼
		        if (list.qna.author && list.qna.prdQnaState == 'N') {
           			 newTbodyContent += '<button class="btn btn-outline-secondary" onclick="updatePopup(' + list.qna.prdQnaId + ')">수정</button>';
        			}
	        	//답변버튼
	        	if (isAdmin) {
	                newTbodyContent += '<button class="btn btn-secondary" onclick="answerArea(' + list.qna.prdQnaId + ', \'' + list.qna.memberId + '\')">답변</button>';
	             	}
	        	//삭제버튼
	        	if (isAdmin || list.qna.author) {
	                newTbodyContent += '<button class="btn btn-danger" onclick="qnaDelete(this)" data-qna-id="' + list.qna.prdQnaId + '">삭제</button>';
	            	}
		      
		            newTbodyContent += '</div>'
		     		newTbodyContent += '<hr><div id="replyContent-' + list.qna.prdQnaId + '" class="pre-line">';
		        //답변완료시
		     	if(list.qna.prdQnaState == 'Y'){
		              newTbodyContent += '<div id="reply-' + list.reply.pqrId + '" class="pre-line">';
		              newTbodyContent += list.reply.pqrContent;
					if(isAdmin){
		              newTbodyContent += '<div class="d-flex justify-content-end">';
		              newTbodyContent += '<button class="btn btn-outline-secondary" onclick="answerUpdate(' + list.reply.pqrId + ', \'' + list.reply.pqrContent + '\')">수정</button>';
		              newTbodyContent += '<button class="btn btn-outline-danger" onclick="replyDelete(this)" data-qna-id="' + list.qna.prdQnaId + '" data-pqr-id="' + list.reply.pqrId + '">삭제</button>';
		              newTbodyContent += '</div></div>';
					}
		     	}
		     	 newTbodyContent +='</div>'

		        //답변 입력창
		        newTbodyContent += '<div id="answer-' + list.qna.prdQnaId + '" class="answer"></div>';
		        newTbodyContent += '<div id="answer-update-' + list.reply.pqrId + '" class="answer"></div>';
		        newTbodyContent += '</td></tr>';
		    });
		    $('#prdQnaBody').html(newTbodyContent);
	      }//end updateTableBody
	      
	function formatDate(timestamp) {
	    var date = new Date(timestamp);
	    var year = date.getFullYear().toString().substr(-2); // 년도의 마지막 두 자리
	    var month = ('0' + (date.getMonth() + 1)).slice(-2); // 월 (0부터 시작하므로 +1 필요)
	    var day = ('0' + date.getDate()).slice(-2); // 일

	    return year + '.' + month + '.' + day;
	}//end formatDate
	
	function updatePageItem(pageMaker){
		var newPageItem = "";
	    // 이전 버튼
	    if (pageMaker.hasPrev) {
	        newPageItem += '<li class="page-item" id="prevBtn"><a class="page-link" href="javascript:void(0);" data-page="' + (pageMaker.startPageNo - 1) + '">이전</a></li>';
	    }
	    // 페이지 번호
	    for (var num = pageMaker.startPageNo; num <= pageMaker.endPageNo; num++) {
	        var isActive = num === pageMaker.criteria.page ? 'active' : '';
	        newPageItem += '<li class="page-item ' + isActive + '"><a class="page-link" href="javascript:void(0);" data-page="' + num + '">' + num + '</a></li>';
	    }
	    // 다음 버튼
	    if (pageMaker.hasNext) {
	        newPageItem += '<li class="page-item" id="nextBtn" ><a class="page-link" href="javascript:void(0);" data-page="' + (pageMaker.endPageNo + 1) + '">다음</a></li>';
	    }
	   // 추가
	    $('#nav .pagination').html(newPageItem);
	}//end UpdatePageItem
	
	//문의 답변
	$(document).on('click', '.submit-answer', function() {
	    var qnaId = $(this).data('qna-id');
	    var authorId = $(this).data('author');
	    submitAnswer(qnaId, authorId);
	});//end submit.on
	
	//문의 수정
	$(document).on('click', '.update-qna', function(){
	    var qnaId = $(this).data('qna-id');
		qnaUpdate(qnaId);
	})//end update-answer.on
	
	//답변 수정
	$(document).on('click', '.update-answer', function() {
	    var pqrId = $(this).data('pqr-id');
	   submitUpdate(pqrId);
	});//end submit.on
	
	
	//답변 작성
	function answerArea(qnaId, authorId){
		var answerDivId = "answer-" + qnaId;
		console.log('answerArea qnaId', qnaId);
		var answerBody = 
			'<div class="answer-input-area">' +
        	'<textarea class="form-control" id="answerText-' + qnaId + '" rows="3" placeholder="답변"></textarea>' +
        	'<button class="btn btn-primary mt-2 submit-answer" data-author="' + authorId + '" data-qna-id="' + qnaId + '">답변하기</button>'
    		'</div>';
		$('#' + answerDivId).html(answerBody);
	}//end answerArea

	//답변 수정
	function answerUpdate(pqrId, replyContent){
	    var answerDivId = "answer-update-" + pqrId;
	    var answerBody = 
	        '<div class="answer-input-area">' +
	        '<textarea class="form-control" id="updateText-' + pqrId + '" rows="3">' + replyContent + '</textarea>' +
	        '<button class="btn btn-primary mt-2 update-answer" data-pqr-id="' + pqrId + '">수정</button>' +
	        '<button class="btn btn--outline-secondary mt-2 cancel-answer" data-answer-div-id="' + answerDivId + '">취소</button>' +
	        '</div>';
	    $('#' + answerDivId).html(answerBody);
	}
	
	//문의 수정
	function updatePopup(prdQnaId){
	    var url = "/mall/product/prdQnaupdate?prdQnaId=" + prdQnaId;
	    var windowName = "상품 문의하기";
	    var windowSize = "width=800, height=600";
	    window.open(url, windowName, windowSize);
}
	

	// 최근 본 상품을 위한 쿠키등록
	  function setCookie(name, value, days) {
	    var expires = "";
	    if (days) {
	      var date = new Date();
	      date.setTime(date.getTime() + days * 24 * 60 * 60 * 1000);
	      expires = "; expires=" + date.toUTCString();
	    }
	    document.cookie = name + "=" + value + expires + "; path=/";
	  }

	  // 쿠키에서 이름에 해당하는 값을 가져오는 함수
	  function getCookie(name) {
	    var nameEQ = name + "=";
	    var ca = document.cookie.split(';');
	    for (var i = 0; i < ca.length; i++) {
	      var c = ca[i];
	      while (c.charAt(0) === ' ') c = c.substring(1, c.length);
	      if (c.indexOf(nameEQ) === 0) return c.substring(nameEQ.length, c.length);
	    }
	    return null;
	  }
	  

	  var productId = "${product.productId}";

	  // 이전에 저장된 쿠키에서 최근 본 상품 목록을 가져옵니다.
	  var recentProducts = getCookie("recentProducts");

	  // 쿠키에 최근 본 상품 목록이 없다면 새로운 배열을 생성합니다.
	  var productIdList = recentProducts ? recentProducts.split(',') : [];

	  // 새로운 상품 ID를 배열에 추가합니다.
	  if (!productIdList.includes(productId)) {
  		productIdList.unshift(productId);

	  // 배열을 문자열로 변환하여 쿠키저장. 1일
	  setCookie("recentProducts", productIdList.join(','), 1 / 48);
	  }

	  

	//문의 답변
	$(document).on('click', '.submit-answer', function() {
	    var qnaId = $(this).data('qna-id');
	    submitAnswer(qnaId);
	});//end submit.on
	
	//답변 수정
	$(document).on('click', '.update-answer', function() {
	    var pqrId = $(this).data('pqr-id');
	   submitUpdate(pqrId);
	});//end submit.on
	


	function submitAnswer(qnaId, authorId){
	    var pqrContent = $('#answerText-' + qnaId).val();
	    var csrfToken = $('#csrfToken').val();
	    var productId = $("#productId").val(); 
	    var headers = {
	        'Content-Type': 'application/json',
	        'X-CSRF-TOKEN': csrfToken
	    };

	    $.ajax({
	        type: 'POST',
	        url: 'qnaAnswer/' + authorId,
	        headers: headers,
	        data: JSON.stringify({
	            'pqrContent': pqrContent,
	            'prdQnaId': qnaId,
	            'productId': productId
	        }),
	        statusCode: {
	            200: (result) => {
	                alert('답변 등록성공');
	                $('#replyContent-' + qnaId).html(pqrContent);
	                $('#state-' + qnaId).text('답변완료');
	            },
	            400: (result) => {
	                alert('중복 답변입니다');
	            }
	        },
	        error: () => {
	            alert('답변 실패');
	        }
	    });
	}//end submitAnswer
	
	//답변 삭제
	function replyDelete(element) {
	    var pqrId = $(element).data('pqr-id');
	    var csrfToken = $('#csrfToken').val();
		var qnaId = $(element).data('qna-id');
	    var headers = {
	        'Content-Type': 'application/json',
	        'X-CSRF-TOKEN': csrfToken
	    };

	    console.log('pqrId', pqrId);
	    console.log('qnaId', qnaId);

	    // confirm 함수의 결과를 변수에 저장
	    var isConfirmed = confirm("답변을 삭제하겠습니까?");
	    if (!isConfirmed) {
	        return;
	    }
	    $.ajax({
	        type: 'delete',
	        url: 'deleteReply',
	        headers: headers,
	        data: JSON.stringify({
	            "pqrId": pqrId
	        }),
	        statusCode: {
	            200: (result) => {
	                alert("삭제성공");
	                $('#replyContent-' + qnaId).remove(); 
	                $('#state-' + qnaId).text('미답변');

	               
	            },
	            500: (result) => {
	                alert("삭제 실패");
	            }
	        },
	        error: function(xhr, status, error) {
	            console.error('오류 발생:', error);
	        }
	    }); // end ajax
	} // end replyDelete
	
	//답변 수정
	function submitUpdate(pqrId){
		 var pqrContent = $('#updateText-' + pqrId).val();
		    var csrfToken = $('#csrfToken').val();
		    var productId = $("#productId").val(); 
		    
		    var headers = {
		        'Content-Type': 'application/json',
		        'X-CSRF-TOKEN': csrfToken
		    };
		    $.ajax({
		        type: 'PUT',
		        url: 'updateAnswer',
		        headers: headers,
		        data: JSON.stringify({
		            'pqrContent': pqrContent,
		            'pqrId': pqrId,
		        }),
		        statusCode: {
		            200: (result) => {
		                alert('답변 수정성공');
		                $('#reply-' + pqrId).text(pqrContent);
		            },
		        },
		        error: () => {
		            alert('수정 실패');
		        }
		    });
	}//end submitUpdate
	
	
	// 최근 본 상품을 위한 쿠키등록
	  function setCookie(name, value, days) {
	    var expires = "";
	    if (days) {
	      var date = new Date();
	      date.setTime(date.getTime() + days * 24 * 60 * 60 * 1000);
	      expires = "; expires=" + date.toUTCString();
	    }
	    document.cookie = name + "=" + value + expires + "; path=/";
	  }

	  // 쿠키에서 이름에 해당하는 값을 가져오는 함수
	  function getCookie(name) {
	    var nameEQ = name + "=";
	    var ca = document.cookie.split(';');
	    for (var i = 0; i < ca.length; i++) {
	      var c = ca[i];
	      while (c.charAt(0) === ' ') c = c.substring(1, c.length);
	      if (c.indexOf(nameEQ) === 0) return c.substring(nameEQ.length, c.length);
	    }
	    return null;
	  }
	  

	  var productId = "${product.productId}";

	  // 이전에 저장된 쿠키에서 최근 본 상품 목록을 가져옵니다.
	  var recentProducts = getCookie("recentProducts");

	  // 쿠키에 최근 본 상품 목록이 없다면 새로운 배열을 생성합니다.
	  var productIdList = recentProducts ? recentProducts.split(',') : [];

	  // 새로운 상품 ID를 배열에 추가합니다.
	  if (!productIdList.includes(productId)) {
		productIdList.unshift(productId);

	  // 배열을 문자열로 변환하여 쿠키저장. 1일
	  setCookie("recentProducts", productIdList.join(','), 1 / 48);
	  }// end 최근 본 상품 쿠키
	

</script>
</body>
</html>