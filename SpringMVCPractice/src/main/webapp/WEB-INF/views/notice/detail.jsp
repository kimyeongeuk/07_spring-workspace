<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${ pageContext.request.contextPath }"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<c:if test="${ not empty alert }">
	<script>
		alert('${alert}')
	</script>
</c:if>

<body>



	<c:choose>
		<c:when test="${ empty list }">
			조회된 게시글이 없습니다.
		</c:when>
		<c:otherwise>
			글번호 : ${ list.no } <br>
			글제목 : <input type="text" value="${ list.title }" name="title" readonly> <br>
			글내용 : <textarea name="content" readonly>${ list.content }</textarea> <br>
			
			<a href="${ contextPath }/notice/modifyForm.do?no=${ list.no }">수정하기</a>
		</c:otherwise>
	</c:choose>


</body>
</html>