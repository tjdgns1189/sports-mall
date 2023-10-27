
$(() => {
	if(isLiked) {
        $('.heart').addClass('heart-filled');
    } else {
        $('.heart').removeClass('heart-filled');
    }//end if(isLiked)
    
    $('.like-btn').on('click', () => {
        isLike();
    })//emd like-btn.on
})// end document.ready

function isLike() {
    var productId = $('#productId').val();
    var memberId = $('#memberId').val();
    var csrfToken = $("#csrfToken").val();
    var headers = {
        'Content-Type': 'application/json'
    };
    headers['X-CSRF-TOKEN'] = csrfToken;

    if ($('.heart').hasClass('heart-filled')) {
        $.ajax({
            type: "DELETE",
            url: 'likes',
            headers: headers,
            data: JSON.stringify({
                "memberId": memberId,
                "productId": productId,
            }),
            success: (result) => {
                console.log(result);
                if (result === 'success') {
                    $('.heart').toggleClass('heart-filled');
                }
            },
            error: (jqXHR, textStatus, errorThrown) => {
                if (jqXHR.status == 403) {
                    alert("로그인이 필요합니다");
                }
            }
        });
    } else {
        $.ajax({
            type: "POST",
            url: 'likes',
            headers: headers,
            data: JSON.stringify({
                "memberId": memberId,
                "productId": productId,
            }),
            success: (result) => {
                console.log(result);
                if (result === 'success') {
                    $('.heart').toggleClass('heart-filled');
                }
            },
            error: (jqXHR, textStatus, errorThrown) => {
                if (jqXHR.status == 403) {
                    alert("로그인이 필요합니다");
                }
            }
        });
    }
}//end isLike()
