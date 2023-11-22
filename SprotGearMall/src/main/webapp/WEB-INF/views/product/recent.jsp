<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<input type="text" id="productQuantity1" 
                            value="${test }">

<c:forEach var="vo" items="${recentProductsList}">
    <tr class="align-middle">
        <td>
            <input type="text" id="productQuantity1" value="${vo }">
        </td>
    </tr>
</c:forEach>

<c:forEach var="i" begin="0" end="3">    
<p><c:out value="${i}" /></p>
</c:forEach>

    
</body>
</html>