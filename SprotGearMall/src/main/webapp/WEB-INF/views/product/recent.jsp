<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>


<c:forEach var="vo" items="${recentProductsList }">
                    <tr class="align-middle">
                        <td>
                            <input type="text" id="productQuantity_${vo.cart.cartId}" 
                            value="${vo}">
                        </td>
                    </tr>
                </c:forEach>
    
</body>
</html>