<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<script src="https://cdnjs.cloudflare.com/ajax/libs/sockjs-client/1.5.0/sockjs.min.js"></script>
<script src="https://code.jquery.com/jquery-3.7.1.js"></script>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>

<title>채팅 문의</title>
<style type="text/css">
    .chat-box {
        height: 500px; /* 고정된 높이 설정 */
        overflow-y: auto; /* 세로 스크롤바 자동 생성 */
        background-color: #f8f9fa; /* 배경색, 선택사항 */
        border: 1px solid #dee2e6; /* 테두리, 선택사항 */
        padding: 15px; /* 내부 여백, 선택사항 */
    }
    
    .center{
    position:absolute;
    text-align: center;;
    }
.system-message {
    background-color: #CCCCCC; /* 회색 배경 */
    color: white; /* 흰색 글씨 */
    text-align: center; /* 텍스트 가운데 정렬 */
    border-radius: 15px; /* 모서리 둥글게 */
    padding: 8px 15px; /* 안쪽 여백 */
    margin: 10px 0; /* 위아래 마진 */
    font-size: 0.9em; /* 글씨 크기 */
    width: 60%; /* 너비 */
    margin-left: auto; /* 왼쪽 마진 자동 */
    margin-right: auto; /* 오른쪽 마진 자동 */
}


.message {
    max-width: 70%;
    margin-bottom: 10px;
    padding: 10px;
    border-radius: 20px;
    position: relative;
}

.user-message {
    background-color: #1E90FF;
    color: white;
    margin-left: auto;
    border-bottom-right-radius: 0;
}

.other-message {
    background-color: #EF8B47;
    border-bottom-left-radius: 0;
}

.message-header, .message-footer {
    font-size: 0.8em;
}

.message-header {
    margin-bottom: 5px;
}

.message-footer {
    margin-top: 5px;
    text-align: right;
}
</style>

    <script type="text/javascript">
        var ws; // 전역 웹소켓 변수
		var username;
        $(() => {
        	username = $('#username').val();
        	console.log("username", username);
            // 메시지 전송 이벤트
            $('#send').click(() => {
                sendMessage();
                $('#message').val('');
            });

            // 채팅 종료 이벤트
            $('#endChatBtn').click(() => {
                if (ws) {
                    ws.close(); // 웹소켓 연결 종료
                    var $systemMessageDiv = $("<div>").addClass("system-message").text("채팅이 종료되었습니다");
                	$("#log").append($systemMessageDiv);
                }
            });

            var roomId = $('#roomId').val();
            onWebsocket(roomId);
            console.log('roomId', roomId);
            
            
            $('#message').keydown(function (e) {
                // 쉬프트 키가 눌리지 않았고 엔터 키가 눌린 경우
                if (!e.shiftKey && e.key === 'Enter') {
                    e.preventDefault(); 
                    sendMessage();
                    $('#message').val('');
                }
            });
        });

        // 웹소켓 초기화 및 이벤트 핸들러 설정
        function onWebsocket(roomId) {
            var wsProtocol = window.location.protocol === "https:" ? "wss:" : "ws:";
            var wsUrl = wsProtocol + "//" + window.location.host + "/mall/echo";

            if (roomId) {
                wsUrl += "?roomId=" + encodeURIComponent(roomId);
            }

            ws = new WebSocket(wsUrl);
            console.log('wsUrl', wsUrl);

            ws.onmessage = onMessage;
            ws.onclose = onClose;
            ws.onopen = function(event) {
                console.log("웹소켓 연결 성공");
            };
            ws.onerror = function(event) {
                console.error("웹소켓 에러 발생", event);
                var $systemMessageDiv = $("<div>").addClass("system-message").text("에러가 발생하여 채팅이 종료되었습니다");
            	$("#log").append($systemMessageDiv);
            };

        }

        // 메시지 전송 함수
        function sendMessage() {
            var message = $('#message').val().trim(); 
            if (message) {
                ws.send(message);
            }
        }

        // 메시지 수신 함수
    	function onMessage(msg) {
    	var data = JSON.parse(msg.data);

    	// 시스템 상태 메시지 처리
    	if (data.senderType === 'state') {
        	var $systemMessageDiv = $("<div>").addClass("system-message").text(data.message);
        	$("#log").append($systemMessageDiv);
    	} else {
        	var isCurrentUser = data.username === username;

        // 일반 메시지 요소 생성
        var $messageDiv = $("<div>").addClass("message").addClass(isCurrentUser ? "user-message" : "other-message");

        // 메시지 헤더 (사용자 이름)
        var $headerDiv = $("<div>").addClass("message-header").text(data.username);
        $messageDiv.append($headerDiv);

        // 메시지 본문
        var $bodyDiv = $("<div>").addClass("message-body").text(data.message);
        $messageDiv.append($bodyDiv);

        // 메시지 푸터 (시간)
        var date = new Date(data.timestamp);
        var formattedTime = date.getHours() + ":" + date.getMinutes().toString().padStart(2, '0');
        var $footerDiv = $("<div>").addClass("message-footer").text(formattedTime);
        $messageDiv.append($footerDiv);

        // 일반 메시지를 채팅박스에 추가
        $("#log").append($messageDiv);
    }

    // 채팅박스 스크롤을 가장 아래로 이동
    $("#log").scrollTop($("#log")[0].scrollHeight);
}


        // 웹소켓 연결 종료 함수
        function onClose() {
            console.log("웹소켓 연결 끊김");
        }
    </script>
</head>
<body>
	
    <div class="container chat-container">
    	<c:if test="${not empty roomId }">
    	<input type="hidden" id="roomId" value="${roomId }">
    	</c:if>
    	<c:if test="${not empty username }">
    	<input type="hidden" id="username" value="${username }">
    	</c:if>
    		<div class="card-header d-flex justify-content-between align-items-center">
    		<div></div> <!-- 좌측 여백을 위한 빈 div -->
    		<h5 class="mb-0">채팅 상담</h5> <!-- 중앙에 배치될 제목 -->
    	<button class="btn btn-danger" type="button" id="endChatBtn">채팅 종료</button> <!-- 우측의 채팅 종료 버튼 -->
		</div>
        <div class="row">
            <div class="col-12">
                <div class="card">
                     <!-- 채팅 내용 들어가는곳 -->
                    <div id="chatbox" class="card-body chat-box">
                    <div id="log">
                    
                    
                    </div>
                    </div>
                    <div class="card-footer">
						<div class="input-group mb-3">
    					<input type="text" class="form-control chat-input" id="message" placeholder="메시지 입력...">
    					<button id="send" class="btn btn-outline-secondary" type="button" id="sendButton">보내기</button>
					</div>                    
					</div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>