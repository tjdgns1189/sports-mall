$(document).ready(function(){
  // 별 클릭 이벤트
  $('#star-rating .fa').on('click', function(){
    var rating = $(this).data('value');
    $('#star-rating .fa').each(function(){
      if($(this).data('value') <= rating){
        $(this).removeClass('fa-star-o').addClass('fa-star');
      } else {
        $(this).removeClass('fa-star').addClass('fa-star-o');
      }
    });
  });

  // 리뷰 모달을 보여주는 함수
  window.reviewModal = function(productId) {
    // 상품 정보 설정
    $('#productName').text($('#' + productId).text());
    // 별점 초기화
    $('#star-rating .fa').removeClass('fa-star').addClass('fa-star-o');
    // 모달 표시
    $('#reviewModal').modal('show');
  };

  // 리뷰 전송 버튼 클릭 이벤트
  $('#submitReview').on('click', function(){
    var reviewText = $('#reviewText').val();
    var rating = $('#star-rating .fa-star').length;
    // 리뷰와 별점 정보를 서버로 전송하는 로직을 여기에 작성
  });
});