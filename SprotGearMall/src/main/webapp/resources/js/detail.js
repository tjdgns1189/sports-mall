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

  document.getElementById('addToCart').addEventListener('click', function () {
    // 필요한 데이터 가져오기
    var memberId = "${pageContext.request.userPrincipal.name}";
    var productId = "${product.productId}";
    var productPrice = "${product.productPrice}";
    var productQuantity = '1';
    var csrfToken = $("#csrfToken").val();

    // 서버로 보낼 데이터 객체 생성
    var obj = {
      'memberId' : memberId,
      'productId' : productId,
      'productPrice' : productPrice,
      'productQuantity' : productQuantity
    };
    console.log(obj);

    // 제품을 장바구니에 추가하기 위해 서버로 AJAX 요청 보내기
    $.ajax({
      type: 'POST',
      url: '../cart/cartlists', // 서버 엔드포인트와 일치하도록 URL 업데이트
      headers : {
			'Content-Type' : 'application/json',
			'X-CSRF-TOKEN': csrfToken
		},
      data: JSON.stringify(obj),
      contentType: 'application/json',
      success: function (result) {
    	console.log(result);
    	if(result == 1) {
        // 서버에서의 응답 처리 (예: 성공 메시지 표시)
        alert('제품이 성공적으로 장바구니에 추가되었습니다.');
    	} else {
    	alert('에러');
    	}
      }
    });
  });
  

