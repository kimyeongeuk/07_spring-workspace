<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${ pageContext.request.contextPath }" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
        #boardList th, #boardList td:not(:nth-child(2)){text-align: center;}
        #boardList>tbody>tr:hover{cursor:pointer;}

        .page-link {
            color: #6c757d; 
            background-color: #fff;
            border: 1px solid #ccc; 
        }
        .page-item.active .page-link {
            z-index: 1;
            color: #555;
            font-weight:bold;
            background-color: #f1f1f1;
            border-color: #ccc;
        }
        .page-link:focus, .page-link:hover {
            color: #000;
            background-color: #fafafa; 
            border-color: #ccc;
        }
    </style>
</head>
<body>

	 <div class="container p-3">

        <!-- Header, Nav start -->
        <jsp:include page="/WEB-INF/views/common/header.jsp"/>
        <!-- Header, Nav end -->
    
        <!-- Section start -->
        <section class="row m-3" style="min-height: 500px">
    
          <div class="container border p-5 m-4 rounded">
            <h2 class="m-4">게시글 목록</h2>
            <br>

            <!-- 로그인후 상태일 경우만 보여지는 글쓰기 버튼-->
            <a class="btn btn-secondary" style="float:right" href="">글쓰기</a>
            <br>
            
            <br>
            <table id="boardList" class="table table-hover" align="center">
                <thead>
                  <tr>
                    <th width="100px">번호</th>
                    <th width="400px">제목</th>
                    <th width="120px">작성자</th>
                    <th>조회수</th>
                    <th>작성일</th>
                    <th>첨부파일</th>
                  </tr>
                </thead>
                <tbody>
                	<c:choose>
                		<c:when test="${ empty list }">
                			<tr>
                				<td colspan="6">조회된 게시글이 없습니다.</td>
                			</tr>
                		</c:when>
                		<c:otherwise>
                			<c:forEach var="l" items="${ list }">
		                    <tr>
		                        <td>${ l.boardNo }</td>
		                        <td>${ l.boardTitle }</td>
		                        <td>${ l.boardWriter }</td>
		                        <td>${ l.count }</td>
		                        <td>${ l.registDate }</td>
		                        <td>${ l.attachCount > 0 ? '*' : '' }</td>
		                    </tr>
	                    </c:forEach>
                    </c:otherwise>
                   </c:choose>
                </tbody>
            </table>
            <br>

            <ul class="pagination d-flex justify-content-center">
                <li class="page-item ${ pi.currentPage == 1 ? 'disabled' : '' }"><a class="page-link" href="${ contextPath }/board/list.do?page=${pi.currentPage - 1}">Previous</a></li>
                <c:forEach var="p" begin="${ pi.startPage }" end="${ pi.endPage }">
                <li class="page-item ${ pi.currentPage == p ? 'active' : '' }"><a class="page-link" href="${ contextPath }/board/list.do?page=${ p }">${ p }</a></li>
                </c:forEach>
                <li class="page-item ${ pi.currentPage == pi.maxPage ? 'disabled' : '' }"><a class="page-link" href="${ contextPath }/board/list.do?page=${pi.currentPage + 1}">Next</a></li>
            </ul>
           
            <br clear="both"><br>
            
            <form action="" method="get" class="d-flex justify-content-center">
                <div class="select" >
                    <select class="custom-select" name="condition">
                        <option value="">작성자</option>
                        <option value="">제목</option>
                        <option value="">내용</option>
                    </select>
                </div>
                <div class="text">
                    <input type="text" class="form-control" name="">
                </div>
                <button type="submit" class="search_btn btn btn-secondary">검색</button>
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