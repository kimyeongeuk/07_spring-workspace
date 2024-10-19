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
<body>
			<form action="${ contextPath }/notice/update.do">
			글번호 : ${ list.no } <br>
			<input type="hidden" name="no" value="${ list.no }">
			글제목 : <input type="text" value="${ list.title }" name="title"> <br>
			글내용 : <textarea name="content">${ list.content }</textarea> <br>
			
			<button type="submit">수정하기</button>
			</form>
			
</body>
</html>