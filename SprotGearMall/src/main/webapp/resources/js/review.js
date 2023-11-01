$(()=>{
  // 모든 리뷰하기 버튼에 대한 클릭 이벤트를 설정합니다.
  $('.review-btn').click(function(){
    var productId = $(this).data('productid');

    // 예시: $('#productName').text('상품명: ' + productName);

    // 모달 창을 띄웁니다.
    $('#reviewModal').modal('show');
  });

  // 별 클릭 이벤트를 설정합니다.
  $('#star-rating .fa').click(function(){
    // 이 별의 인덱스를 가져옵니다.
    var index = $(this).index();
    
    // 별점을 설정합니다.
    $('#star-rating .fa').removeClass('fa-star').addClass('fa-star-o'); // 모든 별을 비활성화합니다.
    $('#star-rating .fa:lt(' + (index+1) + ')').removeClass('fa-star-o').addClass('fa-star'); // 선택된 별과 그 이전의 모든 별을 활성화합니다.
    
    // 선택된 별점의 값을 어딘가에 저장하거나 이용할 수 있습니다.
    // 예시: var ratingValue = index + 1;
  });

  // 리뷰 전송 버튼 클릭 이벤트를 설정합니다.
  $('#submitReview').click(function(){
    // 리뷰 텍스트와 별점 값을 가져옵니다.
    var reviewText = $('#reviewText').val();
    var rating = $('#star-rating .fa-star').length;

    // 리뷰와 별점 정보를 서버로 전송하는 코드를 여기에 추가하십시오.
    // 예시:
    // $.post('/submit-review', { productId: productId, reviewText: reviewText, rating: rating }, function(response){
    //   // 성공적으로 리뷰를 전송한 후의 동작을 여기에 추가하십시오.
    // });
  });
});