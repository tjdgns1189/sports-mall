<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/WEB-INF/views/includes/header.jsp" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<script src="https://code.jquery.com/jquery-3.7.1.js"></script>
<meta charset="UTF-8">
<title>${vo.qnaBoardTitle }</title>
</head>
<body>

<input type="hidden" id="nowMemberId" value="${pageContext.request.userPrincipal.name}" readonly="readonly">

<div class="container-fluid">
 <div class="row">
  <!-- 사이드바 메뉴 -->
  <jsp:include page="../includes/qna-sidebar.jsp" />
   <!-- 메인 콘텐츠 -->
   <div class="col-md-10">
   <h1>QNA게시판</h1>
	<h2>글 보기</h2>
	<div>
		<p>글 번호 : ${vo.qnaBoardId }</p>
	</div>
	<div>
		<p>제목 : </p>
		<p>${vo.qnaBoardTitle }</p>
	</div>
	<div>
		<p>
		   작성자 : <input type="text" id="writer" value="${vo.memberId}" readonly="readonly">
		</p>
		<p>작성일 : ${vo.qnaBoardCreatedDate }</p>
	</div>
	<div>
		<textarea rows="20" cols="120" readonly>${vo.qnaBoardContent }</textarea>
	</div>
	
	<a href="qnaBoard?page=${page }"><input type="button" value="글 목록"></a>
												
									
		<a href="qnaUpdate?qnaBoardId=${vo.qnaBoardId }&page=${page }"><input type="button" id="btnUpdate" value="글 수정"></a>
		<form action="delete" method="POST">
			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
			<input type="hidden" id="qnaBoardId" name="qnaBoardId" value="${vo.qnaBoardId }">
			<input type="submit" id="btnDelete" value="글 삭제"> 
		</form>
		
		
		
		
	<input type="hidden" id="qnaBoardId" name="qnaBoardId" value="${vo.qnaBoardId }">	
			

	<div style="text-align: center;">
		<input type="text" id="memberId" value="${pageContext.request.userPrincipal.name}" readonly="readonly">
		<input type="text" id="qnaReplyContent">
		<button id="btnAdd">작성</button>
	</div>
	
	<hr>
	<div style="text-align: left;">
		<div id="replies"></div>
	</div>
	
	<div>
		<br><br><br><br><br><br><br><br><br><br>
	</div>
	
</div>
</div>
</div>
	
	<script type="text/javascript">
		$(document).ready(function(){
			getAllReplies();

			// 댓글 추가
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

			
			
			
			//댓글 전부 창에 띄우기
			function getAllReplies() {
				var qnaBoardId = $('#qnaBoardId').val();
				var nowMemberId = '${pageContext.request.userPrincipal.name}';
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
					            + '<pre style="font-weight: bold;">'
					            + '<input type="hidden" id="qnaReplyId" value="' + this.qnaReplyId + '">'
					            + '<input style="border: none; font-size: 20px;" type="text" class="writerReply" value="' + this.memberId + '" readonly="readonly">'
					            + '&nbsp;&nbsp;' // 공백
					            + '<input type="text" id="qnaReplyContent" value="' + this.qnaReplyContent + '">'
					            + '&nbsp;&nbsp;' // 공백
					            + qnaReplyCreatedDate
					            + '&nbsp;&nbsp;' // 공백
					            + '<button class="repliesbtn_' + this.qnaReplyId + '" >대댓글</button>'
					            + '<button class="btn_update">수정</button>'
					            + '<button class="btn_delete">삭제</button>'
					            + '</pre>'
					            + '<div id="repliesForm_' + this.qnaReplyId + '"></div>' 
					            + '<div id="replies_' + this.qnaReplyId + '"></div>' 
					            + '</div>';
					            
						}); // end each()

						$('#replies').html(list);
						
						
						// 처음에는 모든 업데이트 버튼을 숨김
			            $('.btn_update').hide();

			            // 해당 댓글에 대한 업데이트 버튼만 표시
			            $('.reply_item').each(function () {
			                var writerReply = $(this).find('.writerReply').val();
			                if (nowMemberId === writerReply) {
			                    $(this).find('.btn_update').show();
			                    console.log('nowMemberId' + nowMemberId);
			                    console.log('writerReply' + writerReply);
			                }
			            });
			            
			            
			         	// 처음에는 모든 삭제 버튼을 숨김
			            $('.btn_delete').hide();

			            // 해당 댓글에 대한 삭제 버튼만 표시
			            $('.reply_item').each(function () {
			                var writerReply = $(this).find('.writerReply').val();
			                if (nowMemberId === writerReply) {
			                    $(this).find('.btn_delete').show();
			                    console.log('nowMemberId' + nowMemberId);
			                    console.log('writerReply' + writerReply);
			                }
			            });
					}
				); // end getJSON()
			} // end getAllReplies()
			
			
			
			

			// 대댓글버튼 누르면 대댓글입력창 띄우기
			$('#replies').on('click', '.reply_item button[class^="repliesbtn_"]', function () {
			    var qnaReplyId = $(this).closest('.reply_item').find('#qnaReplyId').val();
			    var replyRepliesContainer = $('#repliesForm_' + qnaReplyId);
			    var replyForm = '';
			    
				console.log(qnaReplyId);

			    // 대댓글 목록을 토글(보이기/숨기기)
			    replyRepliesContainer.toggle();

			    var replyForm = '<div id="replyRepliesContainer_' + qnaReplyId + '">'
			    	+ '&nbsp;&nbsp;'
	            	+ '&nbsp;&nbsp;'
	            	+ '&nbsp;&nbsp;'
			        + '<span style="color: red;">대댓글 입력창&nbsp;&nbsp;</span><input style="border-color: red;" type="text" id="memberId" value="${pageContext.request.userPrincipal.name}" readonly="readonly">'
			        + '<input style="border-color: red;" type="text" id="replyReplyContent">'
			        + '<button style="border-color: red;" class="btnAddReplyReply">작성</button>'
			        + '</div>'
			        + '</br>'

			    $('#repliesForm_' + qnaReplyId).html(replyForm);
			        
			        
			        
			        
			});
			
			
			
			
			//대댓글 삽입하기
			$('#replies').on('click', '.btnAddReplyReply', function () {
			    var qnaReplyId = $(this).closest('.reply_item').find('#qnaReplyId').val();
			    var memberId = $('#memberId').val();
			    var replyReplyContent = $('#replyReplyContent').val();
			    var csrfToken = $("#csrfToken").val();
			    var qnaReplyCreatedDate = new Date(this.qnaReplyCreatedDate);
			    
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
			                var qnaReplyCreatedDate = new Date(this.qnaReplyCreatedDate);
			                // 대댓글을 생성하여 화면에 추가(qnaReplyCreatedDate이 값이 안뜸)
			                var replyItem = '<div class="reply_item" style="margin-right: 1.3cm;">' +
			                    '<pre>' +
			                    '&nbsp;&nbsp;' + 
					            '&nbsp;&nbsp;' +
					            '&nbsp;&nbsp;' +
			                    '<input type="hidden" id="qnaReplyId" value="' + qnaReplyId + '">' +
			                    memberId +
			                    '&nbsp;&nbsp;' +
			                    '<input type="text" id="replyReplyContent" value="' + replyReplyContent + '">' +
			                    '&nbsp;&nbsp;' +
			                    this.qnaReplyCreatedDate +
			                    '&nbsp;&nbsp;' +
			                    '<button>수정*</button>' +
			                    '<button>삭제</button>' +
			                    '</pre>' +
			                    '</div>';


			                // 대댓글을 화면에 추가(이거 코드 안먹는듯. 에러때 뜨는용도)
			                $('#replies_' + qnaReplyId).append(replyItem);
			                // 알람안떠서 에러로 대체
			            }else{
			            	alert('에러');
			            	var qnaReplyCreatedDate = new Date(this.qnaReplyCreatedDate);
			            	var replyItem = '<div class="reply_item" style="margin-right: 1.3cm;">' +
		                    '<pre>' +
		                    '&nbsp;&nbsp;' + 
				            '&nbsp;&nbsp;' +
				            '&nbsp;&nbsp;' +
		                    '<input type="hidden" id="qnaReplyId" value="' + qnaReplyId + '">' +
		                    memberId +
		                    '&nbsp;&nbsp;' +
		                    '<input type="text" id="replyReplyContent" value="' + replyReplyContent + '">' +
		                    '&nbsp;&nbsp;' +
		                    qnaReplyCreatedDate +
		                    '&nbsp;&nbsp;' +
		                    '<button>수정</button>' +
		                    '<button>삭제</button>' +
		                    '</pre>' +
		                    '</div>';
		                    
			            	$('#replies_' + qnaReplyId).append(replyItem);
			            }
			        }
			    });
			});
			
			
			
			// 댓글에 있는 대댓글버튼을 클릭하면 대댓글목록이 뜸
			$('#replies').on('click', '.reply_item button[class^="repliesbtn_"]', function () {
				var qnaReplyId = $(this).closest('.reply_item').find('#qnaReplyId').val();
				var nowMemberId = '${pageContext.request.userPrincipal.name}';
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
					            + '<input type="hidden" id="replyReplyId" value="' + this.replyReplyId + '">'
					            + '&nbsp;&nbsp;'
					            + '&nbsp;&nbsp;'
					            + '&nbsp;&nbsp;'
					            + '<input type="hidden" class="writerReply" value="' + this.memberId + '" readonly="readonly">'
					            + 'ㄴ&nbsp;' + this.memberId
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
						
						
						
						// 처음에는 모든 업데이트 버튼을 숨김
			            $('.btn_replyUpdate').hide();

			            // 해당 댓글에 대한 업데이트 버튼만 표시
			            $('.reply_item').each(function () {
			                var writerReply = $(this).find('.writerReply').val();
			                if (nowMemberId === writerReply) {
			                    $(this).find('.btn_replyUpdate').show();
			                    console.log('nowMemberId' + nowMemberId);
			                    console.log('writerReply' + writerReply);
			                }
			            });
			            
			            
			         	// 처음에는 모든 삭제 버튼을 숨김
			            $('.btn_replyDelete').hide();

			            // 해당 댓글에 대한 삭제 버튼만 표시
			            $('.reply_item').each(function () {
			                var writerReply = $(this).find('.writerReply').val();
			                if (nowMemberId === writerReply) {
			                    $(this).find('.btn_replyDelete').show();
			                    console.log('nowMemberId' + nowMemberId);
			                    console.log('writerReply' + writerReply);
			                }
			            });
						
					}
				); // end getJSON()
			});
			
			
			
			
			
			
			
			
			
			// 댓글 수정
			$('#replies').on('click', '.reply_item .btn_update', function(){
				console.log(this);
				
				// 선택된 댓글의 replyId, replyContent 값을 저장
				// prevAll() : 선택된 노드 이전에 있는 모든 형제 노드를 접근
				var qnaReplyId = $(this).prevAll('#qnaReplyId').val();
				var qnaReplyContent = $(this).prevAll('#qnaReplyContent').val();
				var csrfToken = $("#csrfToken").val();
				console.log("선택된 댓글 번호 : " + qnaReplyId + ", 댓글 내용 : " + qnaReplyContent);
				
				// ajax 요청
				$.ajax({
					type : 'PUT', 
					url : 'replies/' + qnaReplyId, 
					headers : {
						'Content-Type' : 'application/json',
						'X-CSRF-TOKEN': csrfToken
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
				var csrfToken = $("#csrfToken").val();
				console.log("선택된 댓글 번호 : " + qnaReplyId);
				
				// ajax 요청
				$.ajax({
					type : 'DELETE', 
					url : 'replies/' + qnaReplyId, 
					headers : {
						'Content-Type' : 'application/json',
						'X-CSRF-TOKEN': csrfToken
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
			
			
			
			
			
			// 글 수정 버튼을 숨김
		    $('#btnUpdate').hide();

		    // nowMemberId와 writer의 값이 같을 때만 글 수정 버튼을 표시
		    var nowMemberId = $('#nowMemberId').val();
		    var writer = $('#writer').val(); // 텍스트 내용 가져오기
		    if (nowMemberId === writer) {
		        $('#btnUpdate').show();
		    }
		    
		 	// 글 삭제 버튼을 숨김
		    $('#btnDelete').hide();

		    // nowMemberId와 writer의 값이 같을 때만 글 삭제 버튼을 표시
		    var nowMemberId = $('#nowMemberId').val();
		    var writer = $('#writer').val(); // 텍스트 내용 가져오기
		    if (nowMemberId === writer) {
		        $('#btnDelete').show();
		    }
		    
		 	
		    
		    
		 	// 대댓글 수정 버튼 클릭 시
		    $('#replies').on('click', '.reply_item .btn_replyUpdate', function () {
		        // 선택된 댓글의 replyId, replyContent 값을 저장
		        var replyReplyId = $(this).closest('.reply_item').find('#replyReplyId').val();
		        var replyReplyContent = $(this).closest('.reply_item').find('#replyReplyContent').val();
		        var qnaReplyId = $(this).closest('.reply_item').find('#qnaReplyId').val(); // 추가: 부모 댓글의 qnaReplyId 얻기
		        console.log("replyReplyContent : " + replyReplyContent)
		        var csrfToken = $("#csrfToken").val();
		        console.log("선택된 대댓글 번호 : " + replyReplyId + ", 대댓글 내용 : " + replyReplyContent);

		        // ajax 요청
		        $.ajax({
		            type: 'PUT',
		            url: 'replyReplies/' + replyReplyId,
		            headers: {
		                'Content-Type': 'application/json',
		                'X-CSRF-TOKEN': csrfToken
		            },
		            data: replyReplyContent,
		            success: function (result) {
		                console.log(result);
		                if (result == 1) {
		                    alert('대댓글 수정 성공!');
		                    
		                    
		                }
		            }
		        });
		    });// end 대댓글 수정
		    
		    
		    
		 	// 삭제 버튼을 클릭하면 선택된 대댓글 삭제
			$('#replies').on('click', '.reply_item .btn_replyDelete', function(){
				console.log(this);
			
				var qnaBoardId = $('#qnaBoardId').val();
				var replyReplyId = $(this).closest('.reply_item').find('#replyReplyId').val();
				var csrfToken = $("#csrfToken").val();
				console.log("선택된 대댓글 번호 : " + replyReplyId);
				
				// ajax 요청
				$.ajax({
					type : 'DELETE', 
					url : 'replyReplies/' + replyReplyId, 
					headers : {
						'Content-Type' : 'application/json',
						'X-CSRF-TOKEN': csrfToken
					},
					data : qnaBoardId,
					success : function(result) {
						console.log(result);
						if(result == 1) {
							alert('대댓글 삭제 성공!');
							getAllReplies();
						}
					}
				}); // end ajax()
			}); // end replies.on()

			
		 	


			
		}); // end document
		
		
	
	</script>
			
</body>
</html>