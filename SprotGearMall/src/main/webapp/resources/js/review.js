$(() => {
  let currentRating = 0;

  $('.review-btn').click(function() {
    var productId = $(this).data('productid');
    var productName = $(this).data('productname');
    var productPrice = $(this).data('productprice');
    $('#reviewModal #reviewModalLabel').text(productName);
    $('#reviewModal #productPrice').text(productPrice);

    $('#star-rating .fa-star').addClass('far').removeClass('fas');
    $('#reviewModal').modal('show');
  });

  $('#star-rating .fa-star').on('click', function() {
    var currentStar = $(this);
    currentRating = currentStar.data('rating'); 

    $('#star-rating .fa-star').addClass('far').removeClass('fas');

    currentStar.prevAll().addBack().addClass('fas').removeClass('far');

    if (currentRating != 0) {
      $('#currentRating').text(currentRating + "점"); 
    }
  });

  $('#submit').click(function() {
    var reviewText = $('#reviewContent').val();
    var csrfToken = $("#csrfToken").val();
	var productId = parseInt($('#productId').val(), 10);//문자열로 들어와서 바꾼거임
	var regex =/^.{10,}$/
    var headers = {
  	'Content-Type': 'application/json',
  	'X-CSRF-TOKEN': csrfToken
	};

    if (!regex.test(reviewText)) {
      $('#contentLength').text('리뷰는 10글자 이상 입력해 주세요');
        console.log('리뷰글자수 걸렸음')
      return;
    }
    if (currentRating == 0) {
    	console.log('별점 걸렸음')
      $('#currentRating').text('별점을 입력해 주세요');
      return;
    }
    $.ajax({
      type: 'POST',
      url: '/mall/member/review',
      headers: headers,
      data: JSON.stringify({
        "productId" : productId,
        "reviewRating": currentRating, 
        "reviewContent": reviewText
      }),
      success: (result) => {
        console.log('ajax 전송완료');
        console.log(result);
        if (result === 'success') {
          alert('리뷰 등록에 성공했습니다');
          window.close(); 
        } else {
          alert('리뷰 등록에 실패했습니다 다시 등록해주세요');
        }
      }, // end success
      error: (jqXHR, textStatus, errorThrown) => {
        if (jqXHR.status == 403) {
          alert("잘못된 접근입니다");
        }
      } // end error
    }); // end ajax
  }); // end submit.click
}); // end document.ready