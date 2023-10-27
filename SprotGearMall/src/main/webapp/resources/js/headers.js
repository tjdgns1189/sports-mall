
$(()=>{
    $(".menu-button").click(function() {
        $(".dropdown").toggle();
    });

    $(document).click(function(event) {
        if (!$(event.target).closest('.menu').length) {
            $(".dropdown").hide();
        }
    });


});//end document ready

    function targetURL(){
    	console.log("targetURL() 호출")
        var currentUrl = window.location.href;

        document.cookie = "targeturl=" + encodeURIComponent(currentUrl) + "; path=/";
        window.location.href = "/mall/member/loginForm";


    }
    