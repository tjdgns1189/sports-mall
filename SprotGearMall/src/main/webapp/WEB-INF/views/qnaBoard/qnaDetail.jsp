<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/WEB-INF/views/includes/header.jsp" %>
<!DOCTYPE html>
<html>
<head>
<script src="https://code.jquery.com/jquery-3.7.1.js"></script>
<meta charset="UTF-8">
<title>${vo.qnaBoardTitle }</title>
</head>
<body>

	<h2>글 보기</h2>
	<div>
		<p>글 번호 : ${vo.qnaBoardId }</p>
	</div>
	<div>
		<p>제목 : </p>
		<p>${vo.qnaBoardTitle }</p>
	</div>
	<div>
		<p>작성자 : ${vo.memberId }</p>
		<p>작성일 : ${vo.qnaBoardCreatedDate }</p>
	</div>
	<div>
		<textarea rows="20" cols="120" readonly>${vo.qnaBoardContent }</textarea>
	</div>
	
	<a href="qnaBoard?page=${page }"><input type="button" value="글 목록"></a>
	

		<a href="qnaUpdate?qnaBoardId=${vo.qnaBoardId }&page=${page }"><input type="button" value="글 수정"></a>
		<form action="delete" method="POST">
			<input type="hidden" id="qnaBoardId" name="qnaBoardId" value="${vo.qnaBoardId }">
			<input type="submit" value="글 삭제"> 
		</form>
		
		
		
		
	<input type="hidden" id="qnaBoardId" name="qnaBoardId" value="${vo.qnaBoardId }">	
	
		
	<div style="text-align: center;">
		<input type="text" id="memberId" value="${pageContext.request.userPrincipal.name}" readonly="readonly">
		<input type="text" id="qnaReplyContent">
		<button id="btnAdd">작성</button>
	</div>
	
	<hr>
	<div style="text-align: center;">
		<div id="replies"></div>
	</div>
	
	<div>
		<br><br><br><br><br><br><br><br><br><br>
	</div>
	
	<script type="text/javascript">
		$(document).ready(function(){
			getAllReplies();
			
			
			$('#btnAdd').click(function(){
				var qnaBoardId = $('#qnaBoardId').val(); // 게시판 번호 데이터
				var memberId = $('#memberId').val(); // 작성자 데이터
				var qnaReplyContent = $('#qnaReplyContent').val(); // 댓글 내용
				var csrfToken = $("#csrfToken").val();
				var obj = {
						'qnaBoardId' : qnaBoardId, 
						'memberId' : memberId,
						'qnaReplyContent' : qnaReplyContent
				};
				console.log(obj);
				
				$.ajax({
					type : 'POST', 
					url : 'replies',
					headers : {
						'Content-Type' : 'application/json',
						'X-CSRF-TOKEN': csrfToken
					},
					data : JSON.stringify(obj), // JSON으로 변환
					success : function(result){
						console.log(result);
						if(result == 1) {
							alert('댓글 입력 성공');
							getAllReplies();
						}else{
							alert('에러');
							getAllReplies();
						}
					}
				}); // end ajax()
			}); // end btnAdd.click()

			function getAllReplies() {
				var qnaBoardId = $('#qnaBoardId').val();
				
				var url = 'replies/all/' + qnaBoardId;
				$.getJSON(
					url, 
					function(data) {
						// data : 서버에서 전송받은 list 데이터가 저장되어 있음.
						// getJSON()에서 json 데이터는
						// javascript object로 자동 parsing됨.
						console.log(data);
					
						var memberId = $('#memberId').val();
						var list =''; // 댓글 데이터를 HTML에 표현할 문자열 변수	
						
						// $(컬렉션).each() : 컬렉션 데이터를 반복문으로 꺼내는 함수
						$(data).each(function(){
							// this : 컬렉션의 각 인덱스 데이터를 의미
							console.log(this);
							
							var qnaReplyCreatedDate = new Date(this.qnaReplyCreatedDate);
							var disabled = 'disabled';
							var readonly = 'readonly';
							
							if(memberId == this.memberId) {
								disabled = '';
								readonly = '';
							}
							

							list += '<div class="reply_item">'
					            + '<pre>'
					            + '<input type="hidden" id="qnaReplyId" value="' + this.qnaReplyId + '">'
					            + '<button class="repliesbtn_' + this.qnaReplyId + '" >' + this.memberId + '</button>'
					            + '&nbsp;&nbsp;' // 공백
					            + '<input type="text" id="qnaReplyContent" value="' + this.qnaReplyContent + '">'
					            + '&nbsp;&nbsp;' // 공백
					            + qnaReplyCreatedDate
					            + '&nbsp;&nbsp;' // 공백
					            + '<button class="btn_update">수정</button>'
					            + '<button class="btn_delete">삭제</button>'
					            + '</pre>'
					            + '<div id="repliesForm_' + this.qnaReplyId + '"></div>' 
					            + '<div id="replies_' + this.qnaReplyId + '"></div>' 
					            + '</div>';
					            
						}); // end each()

						$('#replies').html(list);
					}
				); // end getJSON()
			} // end getAllReplies()
			

			
			$('#replies').on('click', '.reply_item button[class^="repliesbtn_"]', function () {
			    var qnaReplyId = $(this).closest('.reply_item').find('#qnaReplyId').val();
			    var replyRepliesContainer = $('#repliesForm_' + qnaReplyId);
			    var replyForm = '';
			    
				console.log(qnaReplyId);

			    // 대댓글 목록을 토글(보이기/숨기기)
			    replyRepliesContainer.toggle();

			    var replyForm = '<div id="replyRepliesContainer_' + qnaReplyId + '">'
			        + '<input type="text" id="memberId" value="${pageContext.request.userPrincipal.name}" readonly="readonly">'
			        + '<input type="text" id="replyReplyContent">'
			        + '<button class="btnAddReplyReply">작성</button>'
			        + '</div>'
			        + '</br>'

			    $('#repliesForm_' + qnaReplyId).html(replyForm);
			});
			
			
			
			$('#replies').on('click', '.btnAddReplyReply', function () {
			    var qnaReplyId = $(this).closest('.reply_item').find('#qnaReplyId').val();
			    var memberId = $('#memberId').val();
			    var replyReplyContent = $('#replyReplyContent').val();
			    var csrfToken = $("#csrfToken").val();
			    
			    
			    var obj = {
			        'qnaReplyId': qnaReplyId,
			        'memberId': memberId,
			        'replyReplyContent': replyReplyContent
			    };
			    console.log(obj);

			    $.ajax({
			        type: 'POST',
			        url: 'replyReplies',
			        headers: {
			            'Content-Type': 'application/json',
			            'X-CSRF-TOKEN': csrfToken
			        },
			        data: JSON.stringify(obj),
			        success: function (result) {
			        	console.log(result);
			            if (result == 1) {
			                alert('댓글 입력 성공');

			                // 대댓글을 생성하여 화면에 추가
			                var replyItem = '<div class="reply_item" style="margin-right: 1.3cm;">' +
			                    '<pre>' +
			                    '<input type="hidden" id="qnaReplyId" value="' + qnaReplyId + '">' +
			                    '<button class="repliesbtn_' + qnaReplyId + '" >' + memberId + '</button>' +
			                    '&nbsp;&nbsp;' +
			                    '<input type="text" id="replyReplyContent" value="' + replyReplyContent + '">' +
			                    '&nbsp;&nbsp;' +
			                    '<button class="btn_update">수정</button>' +
			                    '<button class="btn_delete">삭제</button>' +
			                    '</pre>' +
			                    '</div>';


			                // 대댓글을 화면에 추가
			                $('#replies_' + qnaReplyId).append(replyItem);
			                // 알람안떠서 에러로 대체
			            }else{
			            	alert('에러');
			            	
			            	var replyItem = '<div class="reply_item" style="margin-right: 1.3cm;">' +
		                    '<pre>' +
		                    '<input type="hidden" id="qnaReplyId" value="' + qnaReplyId + '">' +
		                    '<button class="repliesbtn_' + qnaReplyId + '" >' + memberId + '</button>' +
		                    '&nbsp;&nbsp;' +
		                    '<input type="text" id="replyReplyContent" value="' + replyReplyContent + '">' +
		                    '&nbsp;&nbsp;' +
		                    '<button class="btn_update">수정</button>' +
		                    '<button class="btn_delete">삭제</button>' +
		                    '</pre>' +
		                    '</div>';
		                    
			            	$('#replies_' + qnaReplyId).append(replyItem);
			            }
			        }
			    });
			});
			
			
			/*
			$('#replies').on('click','.btnAddReplyReply', function () {
				var qnaReplyId = $(this).closest('.reply_item').find('#qnaReplyId').val();
			    var memberId = $('#memberId').val();
			    var replyReplyContent = $('#replyReplyContent').val();
				var obj = {
						'qnaReplyId' : qnaReplyId, 
						'memberId' : memberId,
						'replyReplyContent' : replyReplyContent
				};
				console.log(obj);
				
				$.ajax({
					type : 'POST', 
					url : 'replyReplies',
					headers : {
						'Content-Type' : 'application/json'
					},
					data : JSON.stringify(obj), // JSON으로 변환
					success : function(result){
						console.log(result);
						if(result == 1) {
							alert('댓글 입력 성공');
						}
					}
				}); // end ajax()
			});
			*/
			
			
			/*
			$('#replies').on('click', '.reply_item button[class^="repliesbtn_"]', function () {
			    // Get the qnaReplyId from the clicked button's class
			    var qnaReplyId = $(this).closest('.reply_item').find('#qnaReplyId').val();

			    var replyRepliesContainer = $('#replies_' + qnaReplyId);
			    var replyForm = '';
			    
			    console.log(qnaReplyId);

			    // 대댓글 목록을 토글(보이기/숨기기)
			    replyRepliesContainer.toggle();

			    var replyForm = '<div id="replyRepliesContainer_' + qnaReplyId + '">'
			        + '<input type="text" id="memberId" value="${pageContext.request.userPrincipal.name}" readonly="readonly">'
			        + '<input type="text" id="replyReplyContent">'
			        + '<button class="btnAddReplyReply">작성</button>'
			        + '</div>'
			        + '</br>'

			    $('#replies_' + qnaReplyId).html(replyForm);
			    
			    // Now, call the function to get reply replies for this qnaReplyId
			    getReplyReplies(qnaReplyId);
			});
			*/
			/*
			function getAllReplies() {
				var qnaReplyId = $(this).closest('.reply_item').find('#qnaReplyId').val();
				
				var url = 'replyReplies/all/' + qnaReplyId;
				$.getJSON(
					url, 
					function(data) {
						// data : 서버에서 전송받은 list 데이터가 저장되어 있음.
						// getJSON()에서 json 데이터는
						// javascript object로 자동 parsing됨.
						console.log(data);
					
						var memberId = $('#memberId').val();
						var list =''; // 댓글 데이터를 HTML에 표현할 문자열 변수	
						
						// $(컬렉션).each() : 컬렉션 데이터를 반복문으로 꺼내는 함수
						$(data).each(function(){
							// this : 컬렉션의 각 인덱스 데이터를 의미
							console.log(this);
							
							var replyReplyCreatedDate = new Date(this.replyReplyCreatedDate);
							var disabled = 'disabled';
							var readonly = 'readonly';
							
							if(memberId == this.memberId) {
								disabled = '';
								readonly = '';
							}
							

							list += '<div class="reply_item">'
					            + '<pre>'
					            + '<input type="hidden" id="qnaReplyId" value="' + this.qnaReplyId + '">'
					            + '<button class="repliesbtn_' + this.qnaReplyId + '" >' + this.memberId + '</button>'
					            + '&nbsp;&nbsp;' // 공백
					            + '<input type="text" id="replyReplyContent" value="' + this.replyReplyContent + '">'
					            + '&nbsp;&nbsp;' // 공백
					            + replyReplyCreatedDate
					            + '&nbsp;&nbsp;' // 공백
					            + '<button class="btn_update">수정</button>'
					            + '<button class="btn_delete">삭제</button>'
					            + '</pre>'
					            + '</div>';
					            
						}); // end each()

						$('#replies_' + qnaReplyId).html(list);
					}
				); // end getJSON()
			} // end getAllReplies()
			*/
		
			$('#replies').on('click', '.reply_item button[class^="repliesbtn_"]', function () {
				var qnaReplyId = $(this).closest('.reply_item').find('#qnaReplyId').val();
				var replyRepliesContainer = $('#replies_' + qnaReplyId);
				replyRepliesContainer.toggle();
				
				var url = 'replyReplies/all/' + qnaReplyId;
				$.getJSON(
					url, 
					function(data) {
						// data : 서버에서 전송받은 list 데이터가 저장되어 있음.
						// getJSON()에서 json 데이터는
						// javascript object로 자동 parsing됨.
						console.log(data);
					
						var memberId = $('#memberId').val();
						var list =''; // 댓글 데이터를 HTML에 표현할 문자열 변수	
						
						// $(컬렉션).each() : 컬렉션 데이터를 반복문으로 꺼내는 함수
						$(data).each(function(){
							// this : 컬렉션의 각 인덱스 데이터를 의미
							console.log(this);
							
							var replyReplyCreatedDate = new Date(this.replyReplyCreatedDate);
							var disabled = 'disabled';
							var readonly = 'readonly';
							
							if(memberId == this.memberId) {
								disabled = '';
								readonly = '';
							}
							

							list += '<div class="reply_item">'
					            + '<pre>'
					            + '<input type="hidden" id="qnaReplyId" value="' + this.qnaReplyId + '">'
					            + '&nbsp;&nbsp;'
					            + '&nbsp;&nbsp;'
					            + '&nbsp;&nbsp;'
					            + '&nbsp;&nbsp;'
					            + '&nbsp;&nbsp;'
					            + '&nbsp;&nbsp;'
					            + '&nbsp;&nbsp;'
					            + '&nbsp;&nbsp;'
					            + '&nbsp;&nbsp;'
					            + '&nbsp;&nbsp;'
					            + '&nbsp;&nbsp;'
					            + '&nbsp;&nbsp;'
					            + '&nbsp;&nbsp;'
					            + '&nbsp;&nbsp;'
					            + '&nbsp;&nbsp;'
					            + '&nbsp;&nbsp;'
					            + '&nbsp;&nbsp;'
					            + '&nbsp;&nbsp;'
					            + '&nbsp;&nbsp;'
					            + '&nbsp;&nbsp;'
					            + '&nbsp;&nbsp;'
					            + '<button class="repliesbtn_' + this.qnaReplyId + '" >' + this.memberId + '</button>'
					            + '&nbsp;&nbsp;' // 공백
					            + '<input type="text" id="replyReplyContent" value="' + this.replyReplyContent + '">'
					            + '&nbsp;&nbsp;' // 공백
					            + replyReplyCreatedDate
					            + '&nbsp;&nbsp;' // 공백
					            + '<button class="btn_replyUpdate">수정</button>'
					            + '<button class="btn_replyDelete">삭제</button>'
					            + '</pre>'
					            + '</div>';
					            
						}); // end each()

						$('#replies_' + qnaReplyId).html(list);
					}
				); // end getJSON()
			});
			
			
			
			
			
			
			
			
			
			
			$('#replies').on('click', '.reply_item .btn_update', function(){
				console.log(this);
				
				// 선택된 댓글의 replyId, replyContent 값을 저장
				// prevAll() : 선택된 노드 이전에 있는 모든 형제 노드를 접근
				var qnaReplyId = $(this).prevAll('#qnaReplyId').val();
				var qnaReplyContent = $(this).prevAll('#qnaReplyContent').val();
				console.log("선택된 댓글 번호 : " + qnaReplyId + ", 댓글 내용 : " + qnaReplyContent);
				
				// ajax 요청
				$.ajax({
					type : 'PUT', 
					url : 'replies/' + qnaReplyId, 
					headers : {
						'Content-Type' : 'application/json'
					},
					data : qnaReplyContent,
					success : function(result) {
						console.log(result);
						if(result == 1) {
							alert('댓글 수정 성공!');
							getAllReplies();
						}
					}
				}); // end ajax()
			}); // end replies.on()
			
			// 삭제 버튼을 클릭하면 선택된 댓글 삭제
			$('#replies').on('click', '.reply_item .btn_delete', function(){
				console.log(this);
			
				var qnaBoardId = $('#qnaBoardId').val();
				var qnaReplyId = $(this).prevAll('#qnaReplyId').val();
				console.log("선택된 댓글 번호 : " + qnaReplyId);
				
				// ajax 요청
				$.ajax({
					type : 'DELETE', 
					url : 'replies/' + qnaReplyId, 
					headers : {
						'Content-Type' : 'application/json'
					},
					data : qnaBoardId,
					success : function(result) {
						console.log(result);
						if(result == 1) {
							alert('댓글 삭제 성공!');
							getAllReplies();
						}
					}
				}); // end ajax()
			}); // end replies.on()

			
		}); // end document
	</script>
			
</body>
</html>