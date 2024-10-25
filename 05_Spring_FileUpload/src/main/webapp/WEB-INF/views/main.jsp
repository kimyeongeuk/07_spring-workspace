<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${ pageContext.request.contextPath }" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>

<style>
	form>label {
		font-size: 12px;
		color: gray;
	}
</style>

</head>
<body>
	
	<script>
		$(function(){
			$('input[type=file]').on('change',function(evt){
				const files = evt.target.files;	// fileList {0:File,1:File,..}		
				console.log(files);
				
				let totalSize = 0;
				
				for(let i=0;i<files.length;i++){ 
					if(files[i].size > 10 * 1024 * 1024){ // 첨부파일 한개의 크기가 10MB 초과할 경우
						alert('첨부파일의 최대 크기는 10MB입니다.');
						evt.target.value = "";
						return;
					}
					totalSize += files[i].size;
					
					if(totalSize >100 * 1024 * 1024){ // 전체 첨부파일 사이즈가 100MB 초과할 경우
						alert('전체 첨부파일 최대 크기는 100MB입니다.');
						evt.target.value = "";
						return;
					}
					
				}
				
			})	
		})
	</script>
	
	
	<h2>1. 한 개의 첨부파일 업로드 테스트</h2>
	<form action="${ contextPath }/board/insert1.do" method="post" enctype="multipart/form-data">
		게시글 제목 : <input type="text" name="boardTitle"> <br>
		게시글 내용 : <textarea name="boardContent"></textarea> <br>
		첨부파일 : <input type="file" name="uploadFile"> <br>
		<label>첨부파일 사이즈는 10MB 이하여야 합니다.</label> <br><br>
		<button type="submit">등록</button>
	</form>
	
	
	<h2>2. 다중 첨부파일 업로드 테스트</h2>
	
	<form action="${ contextPath }/board/insert2.do" method="post" enctype="multipart/form-data">
		게시글 제목 : <input type="text" name="boardTitle"> <br>
		게시글 내용 : <textarea name="boardContent"></textarea> <br>
		첨부파일 : <input type="file" name="uploadFile" multiple> <br>
		<label>각 첨부파일 사이즈는 10MB 이하, 총 100MB 이하여야 됩니다.</label> <br><br>
		<button type="submit">등록</button>
	</form>
	
	
	<h2>3. 비동기식으로 첨부파일 업로드 테스트</h2>
	
	<div id="async_test">
		게시글 제목 : <input type="text" id="title"> <br>
		게시글 내용 : <textarea id="content"></textarea> <br>
		첨부파일 : <input type="file" id="file"> <br><br>
		
		<button type="button" onclick="" id="submit">등록</button>
	</div>
	<br>
	<h2>비동기식 다중 첨부파일 업로드</h2>
	<div id="async_test2">
		게시글 제목 : <input type="text" id="title2"> <br>
		게시글 내용 : <textarea id="content2"></textarea> <br>
		첨부파일 : <input type="file" id="file2" multiple> <br><br>
		
		<button type="button" onclick="" id="submit2">등록</button>
	</div>
	
	<h2>4. 첨부파일 목록 조회</h2>
	<button type="button" onclick="fn_selectAttachList();">첨부파일 조회</button>
	
	<div id="result"></div>
	
	<script>
		function fn_selectAttachList(){
			$.ajax({
				url:'${contextPath}/board/atlist.do',
				success:function(res){
					console.log(res);
					
					let a = '';
					for(let i=0;i<res.length;i++){
						a += '<a href="${contextPath}'+ res[i].filePath + "/" + res[i].filesystemName + '" download="'+ res[i].originalName +'" >'+ res[i].originalName + '</a><br>';
					}
					$('#result').html(a);
				}
				
			})
		}
	</script>
	
	
	
	<script>
		$(function(){
			$('#submit').on("click",function(){
					
				// ajax 게시글 등록 (요청시 전달값 : 제목,내용,첨부파일)
				// 첨부파일 전달해야할 경우 => ForData(가상의 form요소) 객체에 담아서 전달
				let formData = new FormData();
				formData.append("boardTitle", document.getElementById('title').value);
				formData.append("boardContent", document.getElementById('content').value);
				formData.append("uploadFile", document.getElementById('file').files[0]); // File객체 담기
				/*
				{
						boardTitle : document.getElementById('title').value,
						boardContet : document.getElementById('content').value,
						uploadFile : document.getElementById('file').files[0]
					}
				*/
				$.ajax({
					url:'${contextPath}/board/ajaxInsert.do',
					type:'post',
					data:formData,
					processData: false, // processData : false 선언시 formData를 string으로 변한하지 않음
					contentType: false, // contentType : false 선언시 multipart/form-data로 전송되게 함
					success:function(res){
						if(res == 'SUCCESS'){
							alert('성공적으로 등록');
							location.reload();
						}else{
							alert('등록 실패');
						}
					},
					error:function(){
						
					}
					
				})
				
			})
			
			
			
			$('#submit2').on("click",function(){
					
				// ajax 게시글 등록 (요청시 전달값 : 제목,내용,첨부파일)
				// 첨부파일 전달해야할 경우 => ForData(가상의 form요소) 객체에 담아서 전달
				let formData = new FormData();
				let files = document.getElementById('file2').files;
				formData.append("boardTitle", document.getElementById('title2').value);
				formData.append("boardContent", document.getElementById('content2').value);
				for(let i=0;i<files.length;i++){
					formData.append("uploadFile", files[i]); // File객체 담기
					
				}
				/*
				{
						boardTitle : document.getElementById('title').value,
						boardContet : document.getElementById('content').value,
						uploadFile : document.getElementById('file').files[0]
					}
				*/
				$.ajax({
					url:'${contextPath}/board/ajaxInsert2.do',
					type:'post',
					data:formData,
					processData: false, // processData : false 선언시 formData를 string으로 변한하지 않음
					contentType: false, // contentType : false 선언시 multipart/form-data로 전송되게 함
					success:function(res){
						if(res == 'SUCCESS'){
							alert('성공적으로 등록');
							location.reload();
						}else{
							alert('등록 실패');
						}
					},
					error:function(){
						
					}
					
				})
				
			})
			
			
			
			
		})
	</script>
	
	
	
	<hr>
	
	<h2>번외. 내용작성란에 에디터 적용</h2>
	<p>
		에디터 기능 사용시 내용 작성시 폰트 수정, 정렬, 이미지삽입 등등이 가능함 <br>
		위의 과정들이 반영된 html 요소가 넘어감 => DB에 기록 <br>
	
		> 에디터 api : summernote, ckeditor, 등등
	</p>
	
	<form action="${ contextPath }/board/editor/insert.do" method="post">
		게시글 제목 : <input type="text" name="boardTitle"> <br>
		게시글 내용 : <textarea id="summernote" name="boardContent"></textarea> <br><br>
		<button type="submit">등록</button>
	</form>
	
	<!-- include libraries(jQuery, bootstrap) -->
	<link href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css" rel="stylesheet">
	<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
	
	<!-- include summernote css/js -->
	<link href="https://cdn.jsdelivr.net/npm/summernote@0.9.0/dist/summernote.min.css" rel="stylesheet">
	<script src="https://cdn.jsdelivr.net/npm/summernote@0.9.0/dist/summernote.min.js"></script>
	
	<script>
		$(document).ready(function() {
		  $('#summernote').summernote({
			  width: 500,
			  height: 500,
			  placeholder: 'hello world',
			  callbacks: {
				  onImageUpload: function(images){
					  
					  // 비동기식으로 이미지 업로드
					  for(let i=0; i<images.length;i++){
						  
						  let formData = new FormData();
						  formData.append('image',images[i]);
						  
						  $.ajax({
							  url:'${contextPath}/board/editor/imageUpload.do',
							  type:'post',
							  data:formData,
							  processData: false,
							  contentType: false,
							  async: false,
							  success:function(src){
								  // 파일업로드후 저장경로+저장파일명을 응답데이터로 받아
								  // 현재 에디터에 <img> 의 src속성값을 응답데이터로 변경
								  $('#summernote').summernote('insertImage',"${contextPath}" + src);
							  }
						  })
						  
					  }
					  
				  }
			  }
		  })
		});
	</script>
	
	<h2>번외. 에디터로 등록한 게시글 조회해보기</h2>
	<form id="editor_search">
		조회할 글번호 : <input type="text" name="boardNo">
		<button type="button" onclick="fn_selectBoard();">검색</button>
	</form>
	
	<h4>조회결과</h4>
	<b>제목 : </b> <span id="title-result"></span> <br>
	<b>내용</b>
	<div id="content-result"></div>
	
	<script>
		function fn_selectBoard(){
			$.ajax({
				url:'${contextPath}/board/editor/detail.do',
				data: $('#editor_search').serialize(),
				success:function(res){
					$('#title-result').text(res.boardTitle);
					$('#content-result').html(res.boardContent);
				},
			})
		}
	</script>
	
	
	
	
	
	
	
	
	
</body>
</html>