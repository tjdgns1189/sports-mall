<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="https://code.jquery.com/jquery-3.7.1.js"></script>
<title>Insert title here</title>
</head>
<body>
<input type="text" id="productQuantity" 
                            value="${productList}">


<table>
        <tbody id="productTableBody">
<!-- 여기서 JSTL 변수인 serverProductList를 사용할 수 있습니다. -->
<c:forEach var="vo" items="${productList}">
    <tr class="align-middle">
        <td>
            <input type="text" class="productQuantity1" value="${vo.productId}">
        </td>
        <td>
            <input type="text" class="productQuantity2" value="${vo}">
        </td>
    </tr>
</c:forEach>
		</tbody>
</table>


<script type="text/javascript">

//쿠키 이름을 기반으로 쿠키 값을 가져오는 함수
function getCookieValue(cookieName) {
    // 쿠키 문자열 가져오기
    var cookies = document.cookie.split(';');

    // 쿠키 목록을 반복하면서 원하는 쿠키를 찾음
    for (var i = 0; i < cookies.length; i++) {
        var cookie = cookies[i].trim();
        // 쿠키 이름이 일치하면 해당 쿠키의 값을 반환
        if (cookie.startsWith(cookieName + '=')) {
            return cookie.substring(cookieName.length + 1);
        }
    }

    // 해당 쿠키가 없으면 빈 문자열 반환
    return '';
}

// "recentProducts" 쿠키 값을 가져와서 리스트로 변환
var recentProductsCookieValue = getCookieValue('recentProducts');
var recentProductsList = recentProductsCookieValue.split(',');
console.log('recentProductsCookieValue:', recentProductsCookieValue);
console.log('recentProductsList:', recentProductsList);
var url = "recents" + recentProductsCookieValue
// CSRF 토큰 값 가져오기 (이 부분은 페이지에 존재하는지 확인 필요)


// 서버로 데이터를 POST 방식으로 전송
$.ajax({
    type: "GET",
    url: "recents/",
    headers: {
        'Content-Type': 'application/json'
    },
    data: { recentProductsCookieValue: recentProductsCookieValue },
    success: function (productList) {
        // 성공 시 처리
        if (productList !== null) {
            alert('성공');
            console.log('productList서버에서 얘 왔는지 확인:', productList);
//            $('#test').val(productList);
//           $('#productQuantity').val(productList);



	// 기존 내용을 지우기
                    $('#productTableBody').empty();

                    // 받은 데이터를 기반으로 동적으로 HTML 엘리먼트 추가
                    for (var i = 0; i < productList.length; i++) {
                        var product = productList[i];

                        // 새로운 행 생성
                        var newRow = $('<tr class="align-middle">');

                        // 입력 엘리먼트를 포함한 셀 추가
                        var cell1 = $('<td>').append('<input type="text" class="productQuantity1" value="' + product.productId + '">');
                        var cell2 = $('<td>').append('<input type="text" class="productQuantity2" value="' + product + '">');

                        // 행에 셀 추가
                        newRow.append(cell1);
                        newRow.append(cell2);

                        // 행을 테이블 본문에 추가
                        $('#productTableBody').append(newRow);
                    }



        } else {
            alert('실패');
        }
    }
});


</script>




    
</body>
</html>