<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="https://cdnjs.cloudflare.com/ajax/libs/sockjs-client/1.5.0/sockjs.min.js"></script>
<script src="https://code.jquery.com/jquery-3.7.1.js"></script>
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
	<h2>Chat Room</h2>
    <div id="log" style="height:300px;overflow-y:scroll;"></div>
    <input type="text" id="message" />
    <button id="send">전송</button>
</body>
</html>