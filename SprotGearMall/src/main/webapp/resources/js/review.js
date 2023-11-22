  $(() => {
    let currentRating = parseInt($('#reviewRating').val(), 10); 
    $('.review-btn').click(function() {
      var productId = $(this).data('productid');
      var productName = $(this).data('productname');
      var productPrice = $(this).data('productprice');
    });//end review-btnclick

    $('#star-rating .fa-star').on('click', function() {
      var currentStar = $(this);
      currentRating = currentStar.data('rating'); 
      setRating(currentRating);
      if (currentRating != 0) {
        $('#currentRating').text(currentRating + "점"); 
      }
    });//end star.clcik

    $('#submit').click(function() {
      var reviewText = $('#reviewContent').val();
      var csrfToken = $("#csrfToken").val();
      var productId = parseInt($('#productId').val(), 10);
      var orderId = parseInt($('#orderId').val(), 10);
      console.log(orderId)
      var headers = {
      'Content-Type': 'application/json',
      'X-CSRF-TOKEN': csrfToken
    };
    var regex =/^.{10,}$/
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
          "reviewContent": reviewText,
          "orderId" : orderId
        }),
        success: (result) => {
          console.log('ajax 전송완료');
          console.log(result);
          if (result === 'success') {
            alert('리뷰 등록에 성공했습니다');
            window.opener.location.href = '/mall/orders/orderlist'; // 부모 창의 URL을 변경
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

  $('#updateSubmit').click(function(){
      var reviewContent = $('#reviewContent').val();
      var csrfToken = $("#csrfToken").val();
      var reviewId = parseInt($('#reviewId').val(), 10);
      var headers = {
      'Content-Type': 'application/json',
      'X-CSRF-TOKEN': csrfToken
    };
    console.log('reviewContent', reviewContent);
    console.log('reviewId',reviewId);
    console.log('reviewRating',reviewRating);
    var regex =/^.{10,}$/
    if(!regex.test(reviewContent)){
      $('#contentLength').text('리뷰는 10글자 이상 입력해 주세요');
      return;
    }
    $.ajax({
      type : 'PUT',
      url : '/mall/member/reviewUpdate',
      headers : headers,
      data : JSON.stringify({
        "reviewId" : reviewId,
        "reviewContent" : reviewContent,
        "reviewRating" : currentRating
      }),
      success : (result) =>{
        if(result === "success"){
          alert("리뷰를 수정 했습니다")
          window.opener.location.href = '/mall/member/reviewList'; // 부모 창의 URL을 변경
          window.close(); 
        }else{
          alert("리뷰 수정 실패 나중에 다시 시도해 주세요")
        }
      },
      error: (jqXHR, textStatus, errorThrown) => {
        if (jqXHR.status == 403) {
          alert("잘못된 접근입니다");
        }
      } // end error
    })//end ajax
  })//end updateSubmit.click

  function setRating(ratingValue){
    $('#star-rating .fa-star').removeClass('fas').addClass('far');
    $('#star-rating .fa-star').each(function() {
      if (parseInt($(this).data('rating'), 10) <= ratingValue) {
        $(this).removeClass('far').addClass('fas');
      }
    });
    $('#currentRating').text(ratingValue + "점");
  }//end setRating()
  setRating(currentRating);

  
  $('.delete_btn').click(function() {
    var reviewId = $(this).data('id');
    var csrfToken = $("#csrfToken").val();
    var $reviewDiv = $("#review-" + reviewId);
    var headers = {
      'Content-Type': 'application/json',
      'X-CSRF-TOKEN': csrfToken
    };

    $.ajax({
      type: 'delete',
      url: '/mall/member/reviewDelete',
      headers: headers,
      data: JSON.stringify({
        'reviewId': reviewId
      }),
      success: (success) => {
        console.log(success);
        if (success === 'success') {
          alert('리뷰 삭제를 성공했습니다');
          $reviewDiv.remove();
        } else {
          alert('삭제 실패');
        }
      } // end success function
    }); // end ajax 
  }); // end delete_btn.click

  }); // end document.ready