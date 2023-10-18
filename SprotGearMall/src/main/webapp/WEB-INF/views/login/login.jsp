<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
<link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/css/bootstrap.min.css" 
 rel="stylesheet" integrity="sha384-/Y6pD6FV/Vv2HJnA6t+vslU6fwYXjCFtcEpHbNJ0lyAFsXTsjBbfaDjzALeQsN6M" crossorigin="anonymous">

<link href="https://getbootstrap.com/docs/4.0/examples/signin/signin.css" 
 rel="stylesheet" crossorigin="anonymous">
</head>
<body>
	<!--  <h1>로그인 화면</h1>
	<form action="login" method="POST">
		<input type="text" name="memberId" required="required"placeholder="아이디"><br> 
		<input type="password"name="password" required="required" placeholder="비밀번호"><br> 
		<input type="submit" value="로그인">
	</form>
	<a href="register"><button>회원가입</button></a>-->
	
	
	
<div class="container">
      <form class="form-signin" method="POST" action="login">
        <h2 class="form-signin-heading"><a href="/mall/index">로그인</a></h2>
        <p><input type="text" id="username" name="memberId" class="form-control" placeholder="아이디" required="required"></p>
        <p><input type="password" id="password" name="password" class="form-control" placeholder="비밀번호" required="required"></p>
        <button class="btn btn-lg btn-primary btn-block" type="submit">로그인</button>
      </form>
      <div class="container" style="text-align : center;">
      		<a href="register" >회원가입</a>
      </div>
</div>
</body>
</html>