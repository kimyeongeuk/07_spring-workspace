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

	<c:choose>
		<c:when test="${ empty list }">
			존재하는 공지사항이 없습니다.
		</c:when>
		<c:otherwise>
			<table border="1">
				<thead>
					<tr>
						<th>번호</th>
						<th>제목</th>
						<th>내용</th>
					</tr>
				</thead>
				
				<tbody>
					<c:forEach var="l" items="${ list }">
						<tr>
							<td>${ l.no }</td>
							<td>${ l.title }</td>
							<td>${ l.content }</td>
						</tr>
					</c:forEach>
				</tbody>				
			</table>
		</c:otherwise>
	</c:choose>


</body>
</html>