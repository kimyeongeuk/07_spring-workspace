<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${ pageContext.request.contextPath }" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

		<div class="container p-3">

        <!-- Header, Nav start -->
        <jsp:include page="/WEB-INF/views/common/header.jsp"/>
        <!-- Header, Nav end -->
    
        <!-- Section start -->
        <section class="row m-3" style="min-height: 500px">
    
          <div class="container border p-5 m-4 rounded">
          	<!--
          	<form action="${ contextPath }/notice/insert.do" method="post" enctype="multipart/form-data">
          		제목 : <input type="text" name="noticeTitle"> <br>
          		내용 : <textarea name="noticeContent"></textarea> <br>
              <input type="file" name="uploadFiles" multiple><br><br>
          		
          		<button type="submit">등록하기</button>
          	</form>
          	-->
          	 <form  id="enroll-form" method="post" action="${ contextPath }/notice/insert.do" enctype="multipart/form-data">
                <div class="form-group">
                    <label for="title">제목 </label>
                    <input type="text" class="form-control" id="title" name="noticeTitle" required><br>
                    
                    <label for="userName">내용 </label>
                    <textarea class="form-control" required name="noticeContent" id="content" rows="10" style="resize:none;"></textarea><br>
                    
                    <label for="upfile">첨부파일 </label>
                    <input type="file" class="form-control-file border" id="upfile" name="uploadFiles" multiple><br>
                    
                </div>
                <br>
                <div align="center">
                    <button type="submit" class="btn btn-primary">등록하기</button>
                    <button type="reset" class="btn btn-danger">취소하기</button>
                </div>

            </form>

          </div>
    
        </section>
        <!-- Section end -->
    
        <!-- Footer start -->
        <jsp:include page="/WEB-INF/views/common/footer.jsp"/>
        <!-- Footer end -->
    
    </div>
	
</body>
</html>