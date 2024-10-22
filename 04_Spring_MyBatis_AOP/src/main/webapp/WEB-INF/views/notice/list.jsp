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
<c:if test="${ not empty alert }">
<script>
	alert('${alert}');
</script>
</c:if>

<body>

	<h3>공지사항 목록 페이지</h3>

	<c:choose>
		<c:when test="${ empty list }">
			존재하는 공지사항이 없습니다.
		</c:when>
		<c:otherwise>
			<form action="${ contextPath }/notice/delete.do" method="post">
				<table border="1">
					<thead>
						<tr>
							<th></th>
							<th>번호</th>
							<th>제목</th>
							<th>내용</th>
						</tr>
					</thead>
					
					<tbody>
						<c:forEach var="l" items="${ list }">
							<tr>
								<td><input type="checkbox" name="deleteNo" value="${ l.no }"></td>
								<td>${ l.no }</td>
								<td><a href="${ contextPath }/notice/detail.do?no=${ l.no }">${ l.title }</a></td>
								<td>${ l.content }</td>
							</tr>
						</c:forEach>
					</tbody>				
				</table>
				<br>
				<button type="submit">삭제</button><br>
			</form>
			<a href="${ contextPath }/notice/enroll.do">글 작성페이지로 이동</a>
			
		</c:otherwise>
	</c:choose>


</body>
</html>