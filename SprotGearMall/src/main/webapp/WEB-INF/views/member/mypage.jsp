<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>마이페이지</title>
<script type="text/javascript">
	
</script>
</head>
<body>
	<table class="mytmall_table def">
		<caption>기본정보 상세</caption>
		<colgroup>
			<col width="159">
			<col width="">
		</colgroup>
		<tbody>
			<tr>
				<th scope="row">이름</th>
				<td><sec:authentication property="principal.name" />
			</tr>
			<tr>
				<th scope="row">아이디</th>
				<td><sec:authentication property="principal.username" /></td>
			</tr>
			<tr>
				<th scope="row">비밀번호</th>
				<td><a href="passwordUpdate" class="mytmall_bt01"
					data-log-actionid-area="change_password"
					data-log-actionid-label="btn" data-is-ab-send="1"><span>비밀번호
							변경하기</span></a></td>
			</tr>
			<tr>
				<th scope="row">주소</th>
				<td>
				<sec:authentication property="principal.address" />
				</td>
			</tr>
			
			<tr>
				<th scope="row">연락처</th>
				<td class="contact">
					<dl class="info_list">
						<dt class="in_tit">전화 번호</dt>
						<dd>
							<sec:authentication property="principal.phone" />

						</dd>
						<dt class="in_tit">이메일 주소</dt>
						<dd>
							<sec:authentication property="principal.email" />

						</dd>
					</dl> <a href="update"><button type="button" >
							<span>변경하기</span>
						</button> </a>
				</td>
				
				<td>
				<a href="delete"><button type="button">회원탈퇴</button></a>
				</td>
			</tr>
		</tbody>
	</table>
</body>
</html>