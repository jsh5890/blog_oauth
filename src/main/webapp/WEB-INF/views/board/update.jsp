<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>

<div class="container">
	<form action="/auth/login" method="post">
		<input type="hidden" id="id" value="${boardUpdate.id }">
		<div class="form-group">
			<label for="title">제목</label>
			<input type="text" class="form-control" placeholder="Enter Title" id="title" value="${boardUpdate.title }">
		</div>

		<div class="form-group">
		  <label for="content">내용</label>
		  <textarea class="form-control summernote" rows="5" id="content" >${boardUpdate.content }</textarea>
		</div>
	</form>

	<button id="btn_update" class="btn btn-primary">수정</button>
</div>

<script>
$('.summernote').summernote({
	  tabsize: 2,
	  height: 300,
	  focus : true,
	  lang : 'ko-KR',
	  callbacks : {
			onImageUpload : function(files, editor, welEditable) {       
				for (var i = 0; i < files.length; i++) {
					sendFile(files[i], this);
				}
			}
		}
	});
	
	function sendFile(file, el) {
		var form_data = new FormData();
		console.log("file : ", file);
		form_data.append('file', file);
		$.ajax({
			data : form_data,
			type : "POST",
			url : '/api/image',
			cache : false,
			contentType : false,
			enctype : 'multipart/form-data',
			processData : false,
			success : function(url) {
				console.log("url : " , url);
				$(el).summernote('insertImage', url, function($image) {
					console.log("image : ", $image);
					$image.css('width', "100%");
				});
			}
		});
	}
</script>

<script src="/js/function/board.js"></script>
<%@ include file="../layout/footer.jsp"%>