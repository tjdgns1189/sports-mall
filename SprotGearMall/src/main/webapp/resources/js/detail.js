$(() => {
	if(isLiked) {
        $('.heart').addClass('heart-filled');
    } else {
        $('.heart').removeClass('heart-filled');
    }//end if(isLiked)
    
    $('.like-btn').on('click', () => {
        isLike();
    })//emd like-btn.on

    $('.reviewDelete').click(function(){
        var reviewId = $(this).data('review-id');
        var confirmed = confirm("리뷰를 삭제하시겠습니까?");
    
        if(confirmed){
        var csrfToken = $("#csrfToken").val();
        var headers = {
        'Content-Type': 'application/json',
        'X-CSRF-TOKEN': csrfToken
      };
        $.ajax({
            type:"DELETE",
            url: "/mall/member/reviewDelete",
            headers:headers,
            data:JSON.stringify({
                "reviewId" : reviewId
            }),
            success:(result)=>{
                if(result ==='success'){
                $('#review-' + reviewId).remove();
                alert("리뷰가 삭제되었습니다")
                }
            }
        })//end ajax
    }//end if 
    })//end reviewDelete.click
})// end document.ready

function isLike() {
    var productId = $('#productId').val();
    var memberId = $('#memberId').val();
    var csrfToken = $("#csrfToken").val();
    var headers = {
        'Content-Type': 'application/json',
        'X-CSRF-TOKEN': csrfToken
      };
	$('.heart').prop('disabled', true);
	
    if ($('.heart').hasClass('heart-filled')) {
        $.ajax({
            type: "DELETE",
            url: 'likes',
            headers: headers,
            data: JSON.stringify({
                "memberId": memberId,
                "productId": productId,
            }),
            success: (result)=> {
                console.log(result);
                if (result === 'success') {
                    $('.heart').toggleClass('heart-filled');
					$('.heart').prop('disabled', false);
                    
                }
            },
            error: (jqXHR, textStatus, errorThrown) => {
                if (jqXHR.status == 403) {
                    alert("로그인이 필요합니다");
               $('.heart').prop('disabled', false);
                    
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
                    $('.heart').prop('disabled', false);
                }
            },
            error: (jqXHR, textStatus, errorThrown) => {
                if (jqXHR.status == 403) {
                    alert("로그인이 필요합니다");
                    $('.heart').prop('disabled', false);
                }
            }
        });
    }
}//end isLike()


