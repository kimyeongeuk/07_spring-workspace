<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${ pageContext.request.contextPath }" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>.
    <style>
        #reply_area tbody>tr>th:nth-child(1){width:120px}
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
            <h2 class="m-4">게시글 상세</h2>
            <br>

            <a class="btn btn-secondary" style="float:right" href="${ contextPath }/board/list.do">목록으로</a>
            <br><br>
            <table align="center" class="table" id="table1">
                <tr>
                    <th width="120">제목</th>
                    <td colspan="3">${ b.boardTitle }</td>
                </tr>
                <tr>
                    <th>작성자</th>
                    <td width="400">${ b.boardWriter }</td>
                    <th width="120">작성일</th>
                    <td>${ b.registDate }</td>
                </tr>
                <tr>
                    <th>첨부파일</th>
                    <td colspan="3">
                    <c:choose>
                    	<c:when test="${ not empty loginUser }">
	                    	<c:forEach var="at" items="${ b.attachList }">
	                        <a href="${ contextPath }${at.filePath}/${at.filesystemName}" download="${ at.originalName }">${ at.originalName }</a> <br>
	                      </c:forEach>
	                     </c:when>
	                     <c:otherwise>
	                     	<script>
	                     		$(function(){
	                     			$('#table1 a').on('click',function(){
	                     				alert('로그인 후 다운로드가 가능합니다');
			                     		return false;
	                     			})
	                     		})
	                     	</script>
	                     	<c:forEach var="at" items="${ b.attachList }">
	                        <a href="${ contextPath }${at.filePath}/${at.filesystemName}" download="${ at.originalName }">${ at.originalName }</a> <br>
	                      </c:forEach>
	                     </c:otherwise>
                     </c:choose>

                    </td>
                </tr>
                <tr>
                    <th>내용</th>
                    <td colspan="3"></td>
                </tr>
                <tr>
                    <td colspan="4"><p style="height:150px">${ b.boardContent }</p></td>
                </tr>
            </table>
            <br>

            <!-- 수정하기, 삭제하기 버튼은 이글이 본인글일 경우만 보여져야됨 -->
            <c:if test="${ loginUser.userId eq b.boardWriter }">
	            <div align="center">
	            	<form id="frm" action="" method="post">
	            		<input type="hidden" name="no" value="${ b.boardNo }">
	                <button class="btn btn-primary" type="submit" onclick="$('#frm').attr('action','${contextPath}/board/modify.do')">수정하기</button>
	                <button class="btn btn-danger" type="submit" onclick="$('#frm').attr('action','${contextPath}/board/delete.do')">삭제하기</button>
	            	</form>
	            </div>
	          </c:if>  
            <br><br>

            <table id="reply_area" class="table" align="center">
                <thead>
                	<c:if test="${ not empty loginUser }">
                    <tr>
                        <th colspan="2" width="650">
                            <textarea class="form-control" id="reply_content" rows="2" style="resize:none; width:100%"></textarea>
                        </th>
                        <th style="vertical-align: middle"><button class="btn btn-secondary" id="reply_submit" onclick="fn_insertReply()">등록하기</button></th>
                    </tr>
                   </c:if>
                    <tr>
                       <td colspan="3">댓글 (<span id="rcount">0</span>) </td> 
                    </tr>
                </thead>
                <tbody>

                  
                </tbody>
            </table>
          
          	<script>
          		$(document).ready(function(){
          			fn_replyList();
          		})
          	
          		// 해당 게시글의 댓글 목록 조회용 함수 (ajax) 함수
          		function fn_replyList(){
          			$.ajax({
          				url: '${contextPath}/board/rlist.do',
          				data: {no: ${b.boardNo}}, 
          				success:function(res){
          					console.log(res[0].registDate);
          					
          					/*
                        <tr>
                            <th>user02</th>
                            <td>댓글입니다.너무웃기다앙</td>
                            <td>2020-04-10</td>
                        </tr>
                        */
                        let tr = "";
                        for(let i=0; i<res.length; i++){
                        	tr += "<tr>"
                        		 +		"<th>" + res[i].replyWriter + "</th>"
                        		 +		"<td>" + res[i].replyContent + "</td>"
                        		 +		"<td>" + res[i].registDate		+	"</td>"
                        		 +	"</tr>"
                        }
                        
                        $('#reply_area tbody').html(tr);
                        
          				}
          			})
          		}
          		
          		// 댓글 등록용 (ajax) 함수
          		function fn_insertReply(){
          			$.ajax({
          				url:'${contextPath}/board/rinsert.do',
          				type:'post',
          				data: {
          					replyContent: $('#reply_content').val(),
          					refBno: ${b.boardNo},
          				},
          				success:function(res){
          					if(res == 'SUCCESS'){
          						alert('댓글이 등록되었습니다.');
          						fn_replyList();
          						$('#reply_content').val('');
          					}else{
          						alert('댓글 등록이 실패했습니다.');
          						fn_replyList();
          						$('#reply_content').val('');
          					}
          				}
          			})
          		}
          		
          		
          		
          	</script>
          
          
          </div>
    
        </section>
        <!-- Section end -->
    
        <!-- Footer start -->
        <jsp:include page="/WEB-INF/views/common/footer.jsp"/>
        <!-- Footer end -->
    
    </div>
</body>
</html>