let user= {
	init : function() {
		$("#btn_save").on("click",()=>{
			this.save();
		});
		
		$("#btn_update").on("click",()=>{
			this.update();
		});

	},

	save : function(){
		//alert("하이");
		let data = {
			username : $("#username").val(),
			password : $("#password").val(),
			email : $("#email").val()
		};
		console.log(data);

		//ajax 호출시 디폴트가 비동기 호출
		$.ajax({
			type : "POST",
			url :"/auth/join",
			data : JSON.stringify(data), // 화면 데이러
			contentType : "application/json; charset=utf-8",
			dataType : "json" // 서버 데이러
		}).done(function(result){
			alert("회원가입 완료");
			console.log(result);
			location.href = "/";
		}).fail(function(e){
			alert(JSON.stringify(e));
		});// 아작스 통신을 이용해서 data를 json변경해서 insert하기
	},
	
	update : function(){
		//alert("하이");
		let data = {
			id : $("#id").val(),
			password : $("#password").val(),
			email : $("#email").val()
		};
		console.log(data);

		//ajax 호출시 디폴트가 비동기 호출
		$.ajax({
			type : "PUT",
			url :"/auth/userUpdate",
			data : JSON.stringify(data), // 화면 데이러
			contentType : "application/json; charset=utf-8",
			dataType : "json" // 서버 데이러
		}).done(function(result){
			alert("회원수정 완료");
			console.log(result);
			location.href = "/";
		}).fail(function(e){
			alert(JSON.stringify(e));
		});// 아작스 통신을 이용해서 data를 json변경해서 insert하기
	},
}

user.init();