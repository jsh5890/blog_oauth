let user= {
	init : function() {
		$("#btn_save").on("click",()=>{
			this.save();			
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
		
		$.ajax().done().fail();// 아작스 통신을 이용해서 data를 json변경해서 insert하기
	}
}

user.init();