<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>관리자 채팅방 목록</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container mt-5">
        <h2>채팅방 목록</h2>
        <ul class="list-group">
            <!-- 채팅방 목록 출력되는곳 -->
            <li class="list-group-item"> 채팅방 ID : 아무튼 아이디 - 사용자 : 사용자명</li>
        </ul>
    </div>

    <script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
    <script>
    $(document).ready(function() {
    	showChatRoom();
    });//end document.ready
    
    //server send event 로 구현해보기
    function showChatRoom(){
    	  $.ajax({
              url: "#", //아직 경로 설정 안되어 있음
              method: "GET",
              success: function(chatrooms) {
                  chatrooms.forEach(function(chatroom) {
                      $('.list-group').append(
                          '<li class="list-group-item">' +
                          '채팅방 ID: ' + chatroom.id + ' - ' +
                          '사용자: ' + chatroom.username +
                          '</li>'
                      );
                  });
              }
          });
    }
    </script>
</body>
</html>