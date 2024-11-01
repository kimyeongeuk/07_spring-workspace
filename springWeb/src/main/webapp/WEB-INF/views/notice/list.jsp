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
          	<c:if test="${loginUser.status eq 'A'}">
          	<a class="btn btn-secondary" href="${ contextPath }/notice/regist.do">글쓰기</a>
          	</c:if>
            <c:choose>
            	<c:when test="${ empty list }">
            		등록된 공지사항이 없습니다.
            	</c:when>
            	<c:otherwise>
		            <table border="1" id="table">
		            	<thead>
		            		<tr>
		            			<td>번호</td>
		            			<td>제목</td>
		            			<td>작성자</td>
		            			<td>작성일</td>
		            		</tr>
		            	</thead>
		            	
		            	<tbody>
		            		<c:forEach items="${ list }" var="n">
		            			<tr onclick="location.href='${contextPath}/notice/detail.do?no=${ n.noticeNo}'">
		            				<td>${ n.noticeNo }</td>
		            				<td>${ n.noticeTitle }</td>
		            				<td>${ n.noticeWriter }</td>
		            				<td>${ n.registDate }</td>
		            			<tr>
		            		</c:forEach>
		            		
		            	</tbody>
		            </table>
	            </c:otherwise>
            </c:choose>
							  <ul id="paging_area" class="pagination d-flex justify-content-center">
                <li class="page-item ${ pi.currentPage == 1 ? 'disabled' : '' }"><a class="page-link" href="${ contextPath }/notice/list.do?page=${pi.currentPage - 1}">이전</a></li>
                <c:forEach var="p" begin="${ pi.startPage }" end="${ pi.endPage }">
                <li class="page-item ${ pi.currentPage == p ? 'active' : '' }"><a class="page-link" href="${ contextPath }/notice/list.do?page=${ p }">${ p }</a></li>
                </c:forEach>
                <li class="page-item ${ pi.currentPage == pi.maxPage ? 'disabled' : '' }"><a class="page-link" href="${ contextPath }/notice/list.do?page=${pi.currentPage + 1}">다음</a></li>
            </ul>
          </div>
	
        </section>
        <!-- Section end -->
    
        <!-- Footer start -->
        <jsp:include page="/WEB-INF/views/common/footer.jsp"/>
        <!-- Footer end -->
    
    </div>




</body>
</html>