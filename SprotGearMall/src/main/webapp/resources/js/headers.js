$(() => {
    $(".menu-button").click(() => {
        $(".dropdown-content").toggle();
    });//end menu-button.click

    $(document).click((event) => {
        if (!$(event.target).closest('.menu').length) {
            $(".dropdown-content").hide();
        }
    });//end document.click
});//end document ready

function targetURL() {
    console.log("targetURL() 호출")
    var currentUrl = window.location.href;

    document.cookie = "targeturl=" + encodeURIComponent(currentUrl) + "; path=/";
    window.location.href = "/mall/member/loginForm";


}//end targetURL
