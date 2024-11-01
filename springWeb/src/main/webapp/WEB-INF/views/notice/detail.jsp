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
          	
          	<table align="center" class="table" id="table1">
                <tr>
                    <th width="120">제목</th>
                    <td colspan="3">${ notice.noticeTitle }</td>
                </tr>
                <tr>
                    <th>작성자</th>
                    <td width="400">${ notice.noticeWriter }</td>
                    <th width="120">작성일</th>
                    <td>${ notice.registDate }</td>
                </tr>
                <tr>
                    <th>첨부파일</th>
                    <td colspan="3">
                  	<c:forEach var="n" items="${ notice.attachList }">
                  		<a href="${ contextPath }${ n.filePath}/${n.filesystemName}" download="${ n.originalName }">${ n.originalName }</a> <br>
                  	</c:forEach>

                    </td>
                </tr>
                <tr>
                    <th>내용</th>
                    <td colspan="3"></td>
                </tr>
                <tr>
                    <td colspan="4"><p style="height:150px">${ notice.noticeContent }</p></td>
                </tr>
            </table>
          	
          	
          	
          	
          	
          	
          	
          	

          </div>
    
        </section>
        <!-- Section end -->
    
        <!-- Footer start -->
        <jsp:include page="/WEB-INF/views/common/footer.jsp"/>
        <!-- Footer end -->
    
    </div>


</body>
</html>