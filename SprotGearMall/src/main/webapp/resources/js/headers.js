$(() => {
    $(".menu-button").click(() => {
        $(".dropdown-content").toggle();
    });//end menu-button.click

    $(document).click((event) => {
        if (!$(event.target).closest('.menu').length) {
            $(".dropdown-content").hide();
        }
    });//end document.click
    
    checkNotification();
});//end document ready

function targetURL() {
    console.log("targetURL() 호출")
    var currentUrl = window.location.href;

    document.cookie = "targeturl=" + encodeURIComponent(currentUrl) + "; path=/";
    window.location.href = "/mall/member/loginForm";


}//end targetURL

function checkNotification(){
console.log('checkNotification() 호출')
    $.ajax({
        type:'GET',
        url:'/mall/NotificationCheck',
        success:(result)=>{
        //result 조건에 따라 달기
        	console.log("result",result);
            $('#notificationBadges').removeClass('visually-hidden');
        }
    })//end ajax
}
