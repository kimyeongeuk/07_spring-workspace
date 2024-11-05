package com.br.spring.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.FlashMap;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.support.RequestContextUtils;

/*
 * Interceptor (정확히는 HandlerInterceptor)
 * - servlet에서 특정 controller가 실행되기전, 실행된 후에 낚아채서 실행할 내용을 정의
 * 	 ex) 특정 요청을 할 수 있는 회원이 맞는지 "로그인여부판단"
 * 		 특정 요청을 할 수 있는 권한이 맞는지 "회원의 권한체크"
 * preHandle(전처리)	: DispatcherServlet이 특정 Controller를 호출하기 전에 낚아채는 영역
 * postHandle(후처리)	: Controller에서 요청 처리 후 DispatcherServlet으로 뷰정보가 돌아가는 순간 낚아채는 영역
 */

public class LoginCheckInterceptor implements HandlerInterceptor{

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		HttpSession session = request.getSession();
		
		if(session.getAttribute("loginUser") != null) { // 로그인이 되어있을 경우
			return true; // 정상적으로 Controller 실행
		}else { // 로그인 되어있지 않을 경우
			// alert 메세지와 함께 메인페이지가 다시 보여지도록
			
			// RedirectAttributes의 대안
			FlashMap flashMap = new FlashMap();
			flashMap.put("alertMsg","로그인 후 이용가능한 서비스입니다.");
			RequestContextUtils.getFlashMapManager(request).saveOutputFlashMap(flashMap, request, response);
			
			response.sendRedirect(request.getContextPath());
			
			return false; // Controller 실행되지 않도록
		}
				

	}
	
}
