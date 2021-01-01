let board = {
	init: function() {
		$("#btn_save").on("click", () => {
			this.save();
		});

		$("#btn_delete").on("click", () => {
			this.deleteById();
		});

		$("#btn_update").on("click", () => {
			this.update();
		});

		$("#btn_reply_save").on("click", () => {
			this.replySave();
		});

	},

	save: function() {
		//alert("하이");
		let data = {
			title: $("#title").val(),
			content: $("#content").val()
		};
		console.log(data);

		//ajax 호출시 디폴트가 비동기 호출
		$.ajax({
			type: "POST",
			url: "/api/board",
			data: JSON.stringify(data), // 화면 데이러
			contentType: "application/json; charset=utf-8",
			dataType: "json" // 서버 데이러
		}).done(function(result) {
			alert("글쓰기 완료");
			console.log(result);
			location.href = "/";
		}).fail(function(e) {
			alert(JSON.stringify(e));
		});// 아작스 통신을 이용해서 data를 json변경해서 insert하기
	},


	deleteById: function() {
		let data = {
			id: $("#id").text()
		};
		$.ajax({
			type: "DELETE",
			url: "/api/board",
			data: JSON.stringify(data),
			contentType: "application/json; charset=utf-8",
			dataType: "json"
		}).done(function(result) {
			alert("삭제 완료");
			console.log(result);
			location.href = "/";
		}).fail(function(e) {
			alert(JSON.stringify(e));
		});
	},

	update: function() {

		let data = {
			id: $("#id").val(),
			title: $("#title").val(),
			content: $("#content").val()
		};
		console.log(data);

		//ajax 호출시 디폴트가 비동기 호출
		$.ajax({
			type: "PUT",
			url: "/api/board",
			data: JSON.stringify(data), // 화면 데이러
			contentType: "application/json; charset=utf-8",
			dataType: "json" // 서버 데이러
		}).done(function(result) {
			alert("글쓰기 완료");
			console.log(result);
			location.href = "/";
		}).fail(function(e) {
			alert(JSON.stringify(e));
		});// 아작스 통신을 이용해서 data를 json변경해서 insert하기
	},
	replySave: function() {
		//alert("하이");
		let data = {
			content: $("#reply_content").val()
		};
		let boardId = $("#boardId").val();
		console.log(data);
		//ajax 호출시 디폴트가 비동기 호출
		$.ajax({
			type: "POST",
			url: `/api/board/${boardId}/reply`,
			data: JSON.stringify(data), // 화면 데이러
			contentType: "application/json; charset=utf-8",
			dataType: "json" // 서버 데이러
		}).done(function(result) {
			alert("댓글작성 완료");
			console.log(result);
			location.href = `/board/view/${boardId}`;
		}).fail(function(e) {
			alert(JSON.stringify(e));
		});// 아작스 통신을 이용해서 data를 json변경해서 insert하기
	},

	replyDelete: function(boardId, replyId) {
		console.log("boardId : ", boardId);
		console.log("replyId : ", replyId);
		
		$.ajax({
			type: "DELETE",
			url: `/api/board/${boardId}/reply/${replyId}`,
			dataType: "json" // 서버 데이러
		}).done(function(result) {
			alert("댓글삭제 완료");
			console.log(result);
			location.href = `/board/view/${boardId}`;
		}).fail(function(e) {
			alert(JSON.stringify(e));
		});
	},
	
}

board.init();