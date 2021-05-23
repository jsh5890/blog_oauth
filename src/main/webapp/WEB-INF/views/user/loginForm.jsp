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
		<a href="/oauth2/authorization/kakao"><img alt="" src="/image/kakao_login.png" style="height: 40px;"></a>
		<a href="/oauth2/authorization/naver"><img alt="" src="/image/naver_login.png" style="height: 40px;"></a>
		<a href="/oauth2/authorization/google" ><img alt="" src="/image/google_login.png" style="height: 40px;"></a>
	</form>

</div>

<%@ include file="../layout/footer.jsp"%>