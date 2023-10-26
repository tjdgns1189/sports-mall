<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${vo.qnaBoardTitle }</title>
</head>
<body>
	<h2>글 수정 페이지</h2>
	<form action="qnaUpdate" method="POST">
		<input type="hidden" name="page" value="${page }">
		<div>
			<p>글 번호 : ${vo.qnaBoardId }</p>
			<input type="hidden" name="qnaBoardId" value="${vo.qnaBoardId }">
		</div>
		<div>
			<p>제목 : </p>
			<input type="text" name="qnaBoardTitle" value="${vo.qnaBoardTitle }">
		</div>
		<div>
			<p>작성자 : ${vo.memberId }</p>
			<p>작성일 : ${vo.qnaBoardCreatedDate }</p>
		</div>
		<div>
			<textarea rows="20" cols="120" name="qnaBoardContent">${vo.qnaBoardContent }</textarea>
		</div>
		<div>
			<input type="submit" value="등록">
		</div>
	</form>	
</body>
</html>