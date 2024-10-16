package com.br.mvc.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/member")
@Controller
public class MemberController {

	// ====== 요청시 전달되는 값(parameter) 처리하는 방법 ======
	/*
	 * 1. HttpServletRequest 방법
	 * 2. @RequestParam 이용하는 방법
	 * 3. 커맨드 객체 이용하는 방법
	 * 
	 */
	
	/*
	 * 1. HttpServletRequest 방법
	 */
	@RequestMapping("/detail.do")
	public String memberDetail(HttpServletRequest request) {
		int no = Integer.parseInt(request.getParameter("no"));
		System.out.println("조회할 회원번호 : " + no);
		
		
		return "main";
	}
	
	
	//@RequestMapping(value="/enroll1.do", method=RequestMethod.GET) // 405 에러 (요청전송방식이 맞지 않을 경우)
	//@RequestMapping(value="/enroll1.do", method=RequestMethod.POST)
	@PostMapping("/enroll1.do") // Spring 버전업 되면서 @GetMapping, @PostMapping 지원
	public String memberEnroll(HttpServletRequest request) {
		//request.setCharacterEncoding("utf-8"); // => 스프링에서 제공하는 인코딩 필터 등록
		
		String name = request.getParameter("name");
		int age = Integer.parseInt(request.getParameter("age"));
		String address = request.getParameter("address");
		
		System.out.println("이름 : "+name);
		System.out.println("나이 : "+age);
		System.out.println("주소 : "+address);
		
		return "main";
	}
	
	/*
	 * 2. @RequestParam 이용하는 방법
	 * 	  request.getParameter를 자동으로 해주는 어노테이션
	 * 
	 * 	  1) 요청시 전달값을 받기 위한 변수를 매개변수로 작성하기
	 * 	  2) 요청시 전달값의 키값과 담고자하는 변수명이 다를경우
	 * 		 또는 어노테이션의 속성을 활용하고자 할 경우 어노테이션 기술
	 */
	
	@GetMapping("/detail2.do")
	public String memberDetail2(int no) {
		System.out.println("조회할 회원번호 : "+no);
		return "main";
	}
	
	@PostMapping("/enroll2.do")
	public String memberEnroll2(String name // 넘어오는키값이랑 담는 변수명이 같으면 @RequestParam 생략가능
							   ,int age // 내부적으로 파싱되서 담기는데 값이없으면 오류뜸
							   ,String addr)  // 어노테이션 내에서 value 속성값만 작성할 경우 속성값 생략가능
	{
		
		System.out.println("이름 : "+name);
		System.out.println("나이 : "+age);
		System.out.println("주소 : "+addr);
		
		return "main";
	}
	
	
	
	
	
	
}
