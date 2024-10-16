<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${ pageContext.request.contextPath }" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	

	<form action="${ contextPath }/test2">
		핸드폰명 : <input type="text" name="phone">
		브랜드 : <input type="text" name="brand">
		가격 : <input type="text" name="price">
		날짜 : <input type="text" name="date">
		<button type="submit">확인</button>
	</form>

	

</body>
</html>