$(() => {
       function closeAllDropdowns() {
        $(".dropdown-menu").hide();
    }

    // 사용자 메뉴 버튼
    $("#btn-user").click(function(event) {
        event.stopPropagation();
        closeAllDropdowns();
        $(this).next(".dropdown-menu").toggle();
    });

    // 구매 관련 메뉴 버튼
    $("#btn-cart").click(function(event) {
        event.stopPropagation();
        closeAllDropdowns();
        $(this).next(".dropdown-menu").toggle();
    });

    // 알림 메뉴 버튼
    $("#btn-notification").click(function(event) {
        event.stopPropagation();
        closeAllDropdowns();
        $("#drop-notification").toggle();
    });

    // 문서 바깥 클릭 시 모든 드롭다운 메뉴 닫기
    $(document).click(function() {
        closeAllDropdowns();
    });
    
    checkNotification();
});//end document ready


function targetURL() {
    console.log("targetURL() 호출")
    var currentUrl = window.location.href;

    document.cookie = "targeturl=" + encodeURIComponent(currentUrl) + "; path=/";
    window.location.href = "/mall/member/loginForm";


}//end targetURL

function checkNotification() {
    console.log('checkNotification() 호출');
    $.ajax({
        type: 'GET',
        url: '/mall/NotificationCheck',
        success: function(result) {
            console.log("result", result);
            if (result.length > 0) {
                $('#notificationBadge').removeClass('visually-hidden');
                
                var notificationBody = ""; 

                result.forEach(function(list) {
					notificationBody += "<a onclick='updateReadDate(" + list.notificationId + ")' href='" + list.targetUrl + "' class='dropdown-item'>";
    				notificationBody += "<div>" + list.notificationContent + "</div>";
    				notificationBody += "<div>" + formatDate(list.notificationDate) + "</div>";
    				notificationBody += "</a><hr>";
                });

                $('#drop-notification').html(notificationBody);
            }
        }
    }); // end ajax
}//end checkNotification()

function formatDate(timestamp) {
    var date = new Date(timestamp);
    var year = date.getFullYear().toString().substr(-2); // 년도의 마지막 두 자리
    var month = ('0' + (date.getMonth() + 1)).slice(-2); // 월 (0부터 시작하므로 +1 필요)
    var day = ('0' + date.getDate()).slice(-2); // 일

    return year + '.' + month + '.' + day;
} // end formatDate


function updateReadDate(notificationId){
	var csrfToken = $('#csrfToken').val();
	var readDate = new Date().toISOString();
	var headers= {
			'Content-Type': 'application/json',
	        'X-CSRF-TOKEN': csrfToken
	}
	
	   $.ajax({
        type: 'PUT',
        url: 'notificationUpdate',
        headers:headers,
        data: JSON.stringify({
            'notificationId': notificationId,
            'readDate': readDate
            })
    });
	
	
}

