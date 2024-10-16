package com.br.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


/*
 *   @Controller
 * - 클라이언트 요청을 처리할 클래스를 Bean으로 등록하기 위해 작성하는 어노테이션
 * - ㄴ url 요청시 자동으로 Controller내의 메소드가 수행되기 위해서는 
 *      스프린이 해당 객체를 간리할 수 있도록 Bean으로 등록되야됨
 * - servlet-context.xml 파일에 <component-scan> 때문에 빈 스캐닝에 의해 빈으로 등록됨
 * 	 <annotation-driven>에 의해서 @Controller 가 활성화 됨
 */

@Controller
public class MvcController {

	/*
	 * 스프링 사용 전 (서블릿클래스 제작)
	 * 
	 * @WebServlet("/ 또는 /main.do")
	 * 
	 * public void doGet(HttpServletRequest request, HttpServletResponse response) throws Exception{
	 * 		// 1) 포워딩 할 경우
	 * 		request.getRequestDispatcher("/WEB-INF/views/main.jsp").forward(request,response);
	 * 
	 * 		// 2) redirect 경우
	 * 		response.sendRedirect("재요청할url");
	 * }
	 * 
	 */
	
	/*
	 * 스프링 사용 후
	 * 1. 요청 처리를 담당하는 역할의 Controller 클래스 상단에 @Controller 작성
	 * 2. 해당 클래스 내에 url요청별로 실행시킬 메소드 작성 후 @RequestMapping으로 url 매핑값 작성
	 * 
	 * @RequestMapping(value="" method=)
	 * - 요청을 인식하는 어노테이션으로 url매핑값과 요청메소드(GET, POST)를 인식함
	 * - url요청과 실행시킬 메소드를 매핑시켜주는 역할로 메소드 상단에 작성하면됨
	 * 
	 * 	<속성>
	 * 	1) value 속성	: URL Mapping 값, 여러개 작성 가능, 속성명 생략시 기본값
	 * 	2) method 속성	: 요청 방식 (RequestMethod.GET, RequestMethod.POST 상수값으로 작성)
	 * 
	 * 	<메소드 작성법>
	 * 	1) 반환타입
	 * 	   - String	: 응답할 jsp의 경로를 문자열로 반환
	 * 	   - void	: 딱히 반환값이 없을 경우 응답할 jsp 경로는 url 매핑값과 동일하게 인식됨 
	 * 				  ex) /board/list.do	url 매핑값일 경우
	 * 					  /board/list.jsp	페이지로 이동
	 * 	2) 메소드명
	 * 	   아무 역할이 없음	(아무렇게 작성해도됨)
	 * 
	 * 	3) 매개변수
	 * 	   요청과 응답을 위한 각종 변수들 선언
	 * 	   주로 사용되는 매개변수
	 * 	   ㄴ HttpServletRequest request
	 * 	   ㄴ HttpServletResponse response
	 * 	   ㄴ HttpSession session
	 * 	   ㄴ 일반 변수			: 요청시 파라미터를 받는 변수
	 * 	   ㄴ 커맨드 객체		: 요청시 파라미터들을 담는 객체
	 * 	   ㄴ Model model		: 응답페이지에서 필요한 데이터(attribute)를 담는 객체
	 * 	   ㄴ ModelAndView mv 	: Model(데이터) + View(뷰정보)를 같이 담을 수 있는 객체
	 * 	   ㄴ RedirectAttributes arr : redirect 할때 필요한 데이터(flash attribute)를 담는 객체
	 * 	   ㄴ 
	 * 
	 */
	
	
	@RequestMapping(value={"/","main.do"}, method=RequestMethod.GET)
	public String welcomePage() {
		System.out.println("MvcController클래스의 welcomePage 메소드 작동됨");
		return "main";
		/*
		 * 반환값은 사실상 DispatcherServlet의 ViewResolver에 전달됨
		 * 이때 반환값 앞(prefix)에 "/WEB-INF/views/" 가 붙고
		 * 			   뒤(suffix)에 ".jsp"가 붙으면서 응답뷰 경로가 완성됨
		 * 기본적으로 포워딩으로 인식되서 포워딩처리까지 자동으로 진행됨
		 * 
		 * 만일 redirect 하고싶다면 반환 문자열의 앞에 redirect: 붙여주면됨
		 * 즉, return "redirect:url경로";
		 */
	
	}
	
	
}
