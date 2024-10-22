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

	<h3>공지사항 수정페이지</h3>

	<c:choose>
		<c:when test="${ empty notice }">
			조회된 공지사항이 없습니다.
		</c:when>
		<c:otherwise>
			<form action="${ contextPath }/notice/modifyForm.do" method="post">
			번호 : ${ notice.no } <br>
			<input type="hidden" name="no" value="${ notice.no }">
			제목 : <input type="text" name="title" value="${ notice.title }"><br>
			내용 : <textarea name="content">${ notice.content }</textarea><br>
			<button type="submit">확인</button>
			</form>
		</c:otherwise>
	</c:choose>


</body>
</html>