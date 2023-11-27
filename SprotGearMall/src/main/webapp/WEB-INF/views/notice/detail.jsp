<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="/WEB-INF/views/includes/header.jsp" %>
    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
   <style>
        .notice-date {
            font-size: 0.9rem;
            color: #adb5bd; /* 부트스트랩 secondary 색상 */
            margin-bottom: 1rem;
        }
        .btn-custom:hover {
            background-color: #bb2d3b; /* 색상을 약간 어둡게 조정 */
        }
    </style>
<body>
    <div class="container my-4">
        <div class="card">
            <div class="card-body">
                <h5 class="card-title notice-title">${vo.noticeTitle }</h5>
               <fmt:formatDate value="${vo.noticeCreatedDate}" pattern="yyyy-MM-dd" var="date"/>
                <p class="card-text notice-date">${date }</p>
                <!-- 공지사항 본문 -->
                <p class="card-text notice-content">${vo.noticeContent }</p>
            </div>
            <div class="card-footer bg-transparent border-top-0">
                
      		  <sec:authorize access="hasRole('ROLE_ADMIN')">
                <!-- 수정 버튼 -->
                <a href="update?noticeId=${vo.noticeId }" class="btn btn-outline-info">수정</a>
                <!-- 삭제 버튼 -->
                <form action="delete" method="POST" style="display: inline;">
                    <input type="hidden" name="noticeId" value="${vo.noticeId }">
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                    <input type="submit" class="btn btn-danger" value="삭제">
                </form>
                </sec:authorize>
            </div>
        </div>
	<div>
	<a href="noticeboard" style="text-align:center;"><button>목록으로</button></a>
	</div>
    </div>
</body>
</html>