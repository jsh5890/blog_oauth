let board= {
	init : function() {
		$("#btn_save").on("click",()=>{
			this.save();
		});

	},

	save : function(){
		//alert("하이");
		let data = {
			title : $("#title").val(),
			content : $("#content").val()
		};
		console.log(data);

		//ajax 호출시 디폴트가 비동기 호출
		$.ajax({
			type : "POST",
			url :"/api/board",
			data : JSON.stringify(data), // 화면 데이러
			contentType : "application/json; charset=utf-8",
			dataType : "json" // 서버 데이러
		}).done(function(result){
			alert("글쓰기 완료");
			console.log(result);
			location.href = "/";
		}).fail(function(e){
			alert(JSON.stringify(e));
		});// 아작스 통신을 이용해서 data를 json변경해서 insert하기
	},
}

board.init();