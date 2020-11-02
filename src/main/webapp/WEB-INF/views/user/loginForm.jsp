<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>

<div class="container">
	<form action="/auth/login" method="post">
		<div class="form-group">
			<label for="username">Username</label>
			<input type="text" name="username" class="form-control" placeholder="Enter username" id="username">
		</div>
		<div class="form-group">
			<label for="password">Password</label>
			<input type="password" name="password"class="form-control" placeholder="Enter password" id="password">
		</div>
		<div class="form-group form-check">
			<label class="form-check-label">
			<input class="form-check-input" id="remember" type="checkbox"> Remember me
			</label>
		</div>
		<!-- <button id="btn_login" class="btn btn-primary">회원가입</button> -->
		<button id="" class="btn btn-primary">로그인</button>
		<a href="https://kauth.kakao.com/oauth/authorize?client_id=0e0a1a3805f19f56cd093b4484789bdf&redirect_uri=http://localhost:8000/auth/kakao/callback&response_type=code"><img alt="" src="/image/kakao_login.png" style="height: 40px;"></a>
	</form>

</div>

<%@ include file="../layout/footer.jsp"%>