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
					<button type="button" class="mytmall_bt01" aria-haspopup="dialog"
						aria-controls="ar-mytmall-pwchange"
						onclick="">
						<span>변경하기</span>
					</button></td>
			</tr>
			<tr>
				<th scope="row">아이디</th>
				<td><sec:authentication property="principal.username" /></td>
			</tr>
			<tr>
				<th scope="row">비밀번호</th>
				<td><a
					href=""
					class="mytmall_bt01" data-log-actionid-area="change_password"
					data-log-actionid-label="btn" data-is-ab-send="1"><span>비밀번호
							변경하기</span></a></td>
			</tr>
			<tr>
				<th scope="row">연락처</th>
				<td class="contact">
					<dl class="info_list">
						<dt class="in_tit">전화 번호</dt>
						<dd>
							<sec:authentication property="principal.phone" />
							<button type="button" class="mytmall_bt01" aria-haspopup="dialog"
								aria-controls="ar-mytmall-pwchange"
								onclick="">
								<span>변경하기</span>
							</button>
						</dd>
						<dt class="in_tit">이메일 주소</dt>
						<dd>
							<sec:authentication property="principal.email" />
							<button type="button" class="mytmall_bt01" aria-haspopup="dialog"
								aria-controls="ar-mytmall-pwchange"
								onclick="">
								<span>변경하기</span>
							</button>
						</dd>
					</dl>
					<div class="mytmall_member_layer_wrap">
						<div class="layer_def_b2" role="dialog" style="display: none;"
							id="ar-mytmall-pwchange" aria-hidden="true"
							aria-labelledby="ar-mytmall-pwchange-title">
							<div class="layer_ctrl">
								<div class="btm_btn">
									<button type="button" class="defbtn_lsm dtype6"
										onclick="">
										<span>닫기</span>
									</button>
								</div>
							</div>
						</div>
					</div>
				</td>
			</tr>
		</tbody>
	</table>
</body>
</html>