<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="https://cdnjs.cloudflare.com/ajax/libs/sockjs-client/1.5.0/sockjs.min.js"></script>
<script src="https://code.jquery.com/jquery-3.7.1.js"></script>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>

<title>채팅창</title>
    <script type="text/javascript">
    $('#send').click(() => {
        sendMessage();
        $('#message').val('')
    });

    var ws = new SockJS("/mall/echo");
    ws.onmessage = onMessage;
    ws.onclose = onClose;

    function sendMessage() {
        ws.send($('#message').val());
    }

    function onMessage(msg) {
        var data = msg.data;
        $('#log').append(data + "<br/>");
    }

    function onClose() {
        console.log("웹소켓 연결 끊김");
    }
    </script>
</head>
<body>

<div class="container-fluid">
    <div class="row">
        <!-- 사이드바 메뉴 -->
       <jsp:include page="../includes/qna-sidebar.jsp" />
        <!-- 메인 콘텐츠 -->
        <div class="col-md-10">
			<h2>Chat Room</h2>
    		<div id="log" style="height:300px;overflow-y:scroll;"></div>
    		<input type="text" id="message" />
    		<button id="send">전송</button>
    </div>
    </div>
    </div>
</body>
</html>