<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상품문의하기</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <script src="https://code.jquery.com/jquery-3.7.1.js"></script>
</head>
<body>
<div class="container mt-4">
    <h2>상품 문의</h2>
    <form action="prdQnaUpdate" method="POST" id="qnaForm">
    <input type="hidden" id="csrfToken"name="${_csrf.parameterName}" value="${_csrf.token}"><br>
    <input type="hidden" name="productId" value="${vo.productId } ">
    <input type="hidden" name="prdQnaId" value="${vo.prdQnaId }">
    <div class="form-row">
        <div class="form-group">
            <label>문의 유형</label>
            <div>
                <div class="form-check form-check-inline">
                    <input class="form-check-input" type="radio" name="prdQnaCategory" id="type1" value="상품"
   						 ${vo.prdQnaCategory == '상품' ? 'checked="checked"' : ''} />
                    <label class="form-check-label" for="type1">상품</label>
                </div>
                 <div class="form-check form-check-inline">
                    <input class="form-check-input" type="radio" name="prdQnaCategory" id="type2" value="배송"
 					${vo.prdQnaCategory == '배송' ? 'checked="checked"' : ''} />	                    
 				<label class="form-check-label" for="type2">배송</label>
                </div>
                 <div class="form-check form-check-inline">
                    <input class="form-check-input" type="radio" name="prdQnaCategory" id="type3" value="반품/취소"
                     ${vo.prdQnaCategory == '반품/취소' ? 'checked="checked"' : ''} />	
                    <label class="form-check-label" for="type3">반품/취소</label>
                </div>
                 <div class="form-check form-check-inline">
                    <input class="form-check-input" type="radio" name="prdQnaCategory" id="type4" value="교환/변경"
                    ${vo.prdQnaCategory == '교환/변경' ? 'checked="checked"' : ''} />	
                    <label class="form-check-label" for="type4">교환/변경</label>
                </div>
                 <div class="form-check form-check-inline">
                    <input class="form-check-input" type="radio" name="prdQnaCategory" id="type5" value="기타"
                     ${vo.prdQnaCategory == '기타' ? 'checked="checked"' : ''} />	
                    <label class="form-check-label" for="type5">기타</label>
                </div>
            </div>
        </div>
        </div>
        <div class="form-group">
            <label for="inquiryContent">내용</label>
            <textarea class="form-control" name="prdQnaContent" id="prdQnaContent" rows="5">
            ${vo.prdQnaContent }</textarea>
        </div>

        <div class="form-group form-check">
             <input type="checkbox" class="form-check-input" id="privateCheck" name="prdQnaSecret" value="1"
 					${vo.prdQnaSecret == 1 ? 'checked="checked"' : ''} />	                    
            <label class="form-check-label" for="privateCheck">비밀글로 설정</label>
        </div>

        <div class="text-center">
            <button type="submit" class="btn btn-primary">수정</button>
            <button type="button" class="btn btn-secondary" onclick="window.close();">취소</button>
        </div>
    </form>
</div>
</body>
<script type="text/javascript">
$('#qnaForm').on('submit', function(event) {
    event.preventDefault();

    var formData = new FormData(this);
    var csrfToken = $('#csrfToken').val();

    $.ajax({
        url: this.action,
        type: 'PUT',
        data: formData,
        headers: {
            'X-CSRF-TOKEN': csrfToken
        },
        processData: false, 
        contentType: false,  
        success: (result) =>{
            console.log("result", result.result);
            if (result.result === "success") {
                alert("문의 수정 성공.");
                window.opener.location.reload();
                window.close();
            } else {
                alert("서버에서 문제가 발생했습니다.");
            }
        },
        error: function(xhr, status, error) {
            console.error("Error:", error);
            alert("요청 처리 중 오류가 발생했습니다.");
        }
    });
});
</script>

</html>