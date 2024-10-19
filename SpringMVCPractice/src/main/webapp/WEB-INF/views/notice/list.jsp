<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${ pageContext.request.contextPath }"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src=""></script>
</head>
<body>

	<h3>게시글 목록</h3>


	<c:choose>
		<c:when test="${ empty list }">
			조회된 게시글이 없습니다.
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
					<c:forEach var="c" items="${ list }">
						<tr>
							<td><a href="${ contextPath }/notice/detail.do?no=${ c.no }">${ c.no }</a></td>
							<td>${ c.title }</td>
							<td>${ c.content }</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			
		</c:otherwise>
	</c:choose>
	<form action="${ contextPath }/notice/delete.do">
		삭제할 게시글 번호 : <input type="number" name="no">
		<button type="submit">확인</button>
	</form>

</body>
</html>