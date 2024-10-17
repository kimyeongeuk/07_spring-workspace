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

<c:if test="${ not empty model }">
	<script>
		alert('${ model }');
	</script>
</c:if>
<c:remove var="model"/>

	${ model }
	<h3>공지사항 상세 페이지</h3>

	<c:choose>
		<c:when test="${ empty notice }">
			조회된 공지사항이 없습니다.
		</c:when>
		<c:otherwise>
			번호 : ${ notice.no } <br>
			제목 : ${ notice.title } <br>
			내용 : ${ notice.content } <br><br>
			<a href="${ contextPath }/notice/modifyForm.do?no=${ notice.no}">수정하기</a>
		</c:otherwise>
	</c:choose>

<!-- 
				* 실습 (공지사항 수정하기)
				1) 공지사항 상세페이지에서 수정페이지로 이동 링크 만들기 (/notice/modifyForm.do?no=현재글번호  url 요청되도록)
				2) 해당 url 요청시 실행되는 Controller 작업 
				   ㄴ 요청시 전달되는 파라미터(수정할글번호) 뽑기
				   ㄴ 해당 번호로 공지사항 조회 서비스 요청
				   ㄴ 조회된 공지사항 객체를 수정페이지에서 사용할수 있도록 담기
				   ㄴ 수정페이지로 포워딩
				3) 해당 url 요청시 포워딩되는 수정페이지(/WEB-INF/views/notice/modifyForm.jsp) 제작하기 
					 ㄴ 수정할수 있는 폼요소로 만들기 (제목 텍스트상자, 내용 텍스트상자 존재)
					    이때 조회된 기존의 공지사항 정보 노출시키기 
					 ㄴ 수정 요청을 보낼 수 있는 submit 버튼 두기
					 ㄴ 수정 요청시 /notice/update.do  url로 요청되도록
				4) 해당 url 요청시 실행되는 Controller 작업
					 ㄴ 요청시 전달되는 파라미터들(번호(숨김상태로), 제목, 내용)을 NoticeDto 객체의 필드에 바로 매핑될 수 있도록 하기
					 ㄴ 해당 NoticeDto 객체를 전달하면서 수정요청할 수 있는 service측 메소드 만들기 
					 ㄴ dao측도 만들기 (이때 그냥 1로 반환되도록 하기)
					 ㄴ 성공했을 경우 상세페이지로 다시 이동될수있도록 하기	 				
			-->

</body>
</html>