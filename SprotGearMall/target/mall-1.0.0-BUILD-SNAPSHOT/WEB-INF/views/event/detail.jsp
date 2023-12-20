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


	<div class="container my-4">
        <div class="card">
            <div class="card-body">
                <h5 class="card-title eventBoard-title">제목 : ${vo.eventBoardTitle }
                	<fmt:formatDate value="${vo.eventBoardCreatedDate}" pattern="yyyy-MM-dd" var="date"/>
                <span class="card-text eventBoard-date" style="margin-left: 750px;">날짜 : ${date }</span>
                </h5>
               
                <!-- 공지사항 본문 -->
                <p class="card-text eventBoard-content">내용 : ${vo.eventBoardContent }</p>
            </div>
            <div class="card-footer bg-transparent border-top-0">
                
      		  <sec:authorize access="hasRole('ROLE_ADMIN')">
                <!-- 수정 버튼 -->
                <a href="update?eventBoardId=${vo.eventBoardId }" class="btn btn-outline-info">수정</a>
                <!-- 삭제 버튼 -->
                <form action="delete" method="POST" style="display: inline;">
                    <input type="hidden" name="eventBoardId" value="${vo.eventBoardId }">
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                    <input type="submit" class="btn btn-danger" value="삭제">
                </form>
                </sec:authorize>
            </div>
        </div>
	<div>
	<a href="eventboard" style="text-align:center;"><button>목록으로</button></a>
	</div>
	
	 <!-- 댓글 표시 부분 추가 -->
    <div class="card mt-4">
        <div class="card-body">
            <h5 class="card-title">댓글</h5>
            <ul class="list-group">
                    
                    
                    
            </ul>
        </div>
    </div>

    <!-- 댓글 작성 폼 추가 -->
    <div class="card mt-4">
        <div class="card-body">
                <div class="form-group">
                    <label for="commentContent">댓글 작성</label>
                    <textarea class="form-control" id="commentContent" name="content" rows="3" required></textarea>
                </div>
                <input type="hidden" id="eventBoardId" name="eventBoardId" value="${vo.eventBoardId}">
                <button type="button" onclick="replyInsert()" id="btn-insert" class="btn btn-primary">댓글 작성</button>
        </div>
    </div>
	
    </div>
    
    <script type="text/javascript">
    	$(()=>{
    		getreplies();
    	})//end document.ready
    	
    function replyInsert() {
        var eventBoardId = $('#eventBoardId').val();
        var eventReplyContent = $('#commentContent').val();
        var csrfToken = $('#csrfToken').val();
        var headers = {
            'Content-Type': 'application/json',
            'X-CSRF-TOKEN': csrfToken
        };

        $.ajax({
            type: 'POST',
            url: 'repliesInsert',
            headers: headers,
            data: JSON.stringify({
                'eventBoardId': eventBoardId,
                'eventReplyContent': eventReplyContent
            }),
            success: function(result) {
            	if(result == 1) {
            		console.log('result',result);
                  	getreplies();
                  	$('#commentContent').val('');
            		}
               },
            error: function(xhr, status, error) {
                if (xhr.status == 403) {
                    alert("로그인이 필요합니다.");
                } else {
                    console.error("오류 발생: ", error);
                    alert("댓글 작성실패 나중에 다시 시도해주세요")
                }
            }
        });//end ajax
    }

    	function getreplies() {
    	    var eventBoardId = $('#eventBoardId').val();
    	    var currentUserMemberId = '${currentUserMemberId}';

    	    // 페이지 로드 시 댓글 가져오기
    	    $.ajax({
    	        type: 'GET',
    	        url: 'replies',
    	        data: { 'eventBoardId': eventBoardId },
    	        success: function (replies) {
    	            // 댓글 데이터를 직접 문자열로 조합하여 출력
    	            var str = '';
    	            for (var i = 0; i < replies.length; i++) {
    	                var comment = replies[i];

    	                var commentNumber = comment.eventReplyId;
    	                var commentContent = comment.eventReplyContent;
    	                var timestamp = comment.eventReplyCreatedDate;
    	                var memberId = comment.memberId;

    	                // 날짜 포맷팅 함수
    	                function formatDate(timestamp) {
    	                    return timestamp ? new Date(timestamp).toLocaleString('ko-KR', {
    	                        year: 'numeric',
    	                        month: '2-digit',
    	                        day: '2-digit'
    	                    }) : '';
    	                }

    	                // HTML 문자열 생성
    	                str += '<li class="list-group-item" id="li-' +commentNumber+ '">' +
    	                    '<div class="comment-info" id="div-'+ commentNumber +'">' +
    	                    '<span class="comment-number">번호: ' + commentNumber + '</span> &nbsp;&nbsp; ' +
    	                    '<span class="comment-member-id">작성자: ' + memberId + '</span> &nbsp;&nbsp; ' +
    	                    '<span class="comment-content">내용: ' + commentContent + '</span> &nbsp;&nbsp; ' +
    	                    '<span class="comment-time">날짜: ' + formatDate(timestamp) + '</span>' +
    	                    '</div>' +
    	                    '<div class="comment-actions" id="btn-div-' + commentNumber + '">';
    	                    
    	                 // 현재 사용자와 댓글의 작성자가 같을 경우에만 수정, 삭제 버튼 추가
    	                 if (currentUserMemberId === memberId) {
                    str += '<button class="btn btn-primary" onclick="editComment(' + commentNumber + ')">수정</button>' +
                        '<button class="btn btn-danger" onclick="deleteComment(' + commentNumber + ')">삭제</button>';
                }
    	                 
    	                 str += '</div>' +
    	                    	'</li>';
    	            }

    	            // 댓글을 출력할 요소에 문자열을 설정
    	            $('.list-group').html(str);
    	        },
    	        error: function () {
    	            console.error('댓글을 가져오는 중에 오류가 발생했습니다.');
    	        }
    	    });
    	}

		function deleteComment (eventReplyId) {
			console.log('deleteComment 호출 id:', eventReplyId );
			var csrfToken = $('#csrfToken').val();
			var headers = {
					'Content-Type': 'application/json',
					'X-CSRF-TOKEN': csrfToken
			}
			
			$.ajax({
				type : 'delete',
				url : 'replies',
				data : JSON.stringify(
						{ 'eventReplyId' : eventReplyId }
				) ,
				headers : headers,
				success : function (result) {
					if(result == 1) {
						alert('삭제완료');
						$('#li-' + eventReplyId).remove();

					}
				}
				
			})
			
		} 
		
		function editComment(commentNumber) {
		    var commentInfo = $('#div-' + commentNumber);
		    
		    // 현재 댓글 내용 가져오기
		    var commentContent = commentInfo.find('.comment-content').text().replace('내용: ', '').trim();

		    // 수정 버튼 대신 수정 완료 버튼으로 변경
		    var actionsDiv = $('#btn-div-' + commentNumber);
		    actionsDiv.html('<button class="btn btn-success" onclick="completeEdit(' + commentNumber + ')">수정 완료</button>');

		    // 댓글 내용을 입력 필드로 교체
		    commentInfo.find('.comment-content').html('내용 : <input type="text" class="edit-comment-input" id="edit-comment-input-' + commentNumber + '" value="' + commentContent + '">');
		}
		
		function completeEdit(commentNumber) {
		    // 수정된 댓글 내용 가져오기
		    var editedContent = $('#edit-comment-input-' + commentNumber).val();
		    var csrfToken = $('#csrfToken').val();
		    var headers = {
		        'Content-Type': 'application/json',
		        'X-CSRF-TOKEN': csrfToken
		    };

		    // 서버로 수정된 댓글 내용을 전송 (AJAX 요청 필요)
		    $.ajax({
		        type: 'PUT',
		        url: 'replies',
		        data: JSON.stringify({
		            'eventReplyId': commentNumber, // 수정 대상 댓글의 ID
		            'eventReplyContent': editedContent
		        }),
		        headers: headers,
		        success: function(response) {
		            if (response === 1) {
		                // 성공적으로 서버에서 응답이 왔을 때, UI 업데이트
		                getreplies();
		            }
		        },
		        error: function(error) {
		            // 오류 처리
		            console.error('댓글 수정 중 오류 발생:', error);
		        }
		    });
		}
    </script>
    
</body>	
</html>