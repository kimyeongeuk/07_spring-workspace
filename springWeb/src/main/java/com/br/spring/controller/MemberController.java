package com.br.spring.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.br.spring.dto.MemberDto;
import com.br.spring.service.MemberService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {

	private final MemberService memberService;
	private final BCryptPasswordEncoder bcryptPwdEncoder;
	
	@PostMapping("/signin.do")
	public void signIn(MemberDto m, HttpServletResponse response, HttpSession session, HttpServletRequest request) throws IOException {
		
		MemberDto loginUser = memberService.selectMember(m);
		
		// 로그인 성공시 => 세션에 회원정보 담고, alert와 함께 메인 페이지가 보여지도록
		// 로그인 실패시 => alert와 함께 기존에 보던 페이지 유지
		
		// script문을 응답데이터로 돌려줘서 흐름제어
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		out.println("<script>");
		
		
		if(loginUser != null) { // 로그인 성공
			session.setAttribute("loginUser", loginUser);
			out.println("alert('"+loginUser.getUserName()+"님 환영합니다~');");
			out.println("location.href='"+ request.getHeader("referer") +"';"); // 이전에 보던 페이지로 이동 
		}else { // 로그인 실패
			out.println("alert('로그인에 실패하였습니다. 아이디 및 비밀번호를 다시 확인해주세요');");
			out.println("history.back()");
		}
		
		out.println("</script>");
		
	}
	
	@GetMapping("/signout.do")
	public String signOut(HttpSession session) {
		
		session.invalidate();

		return "redirect:/";
	}
	
	@GetMapping("/signup.do")
	public void signupPage() {}
	
	@ResponseBody
	@GetMapping("/idcheck.do")
	public String idCheck(String checkId) {
		return memberService.selectUserIdCount(checkId) == 0 ? "YYYYY" : "NNNNN";
	}
	
	@PostMapping("/insert.do")
	public String signup(MemberDto m,RedirectAttributes ra) {
		log.debug("암호화전 member : {}",m);
		
		/*
		 * 암호화
		 * - 어떠한 값(평문)을 다른사람들이 알아볼수 없는 암호문으로 변경하는 과정
		 * - DB 같은 저장소에 개인정보 중 보호해야되는 값들은 암호문으로 보관해야됨
		 * 
		 * 			암호화
		 * 평문 ---------------> 암호문
		 * 평문 <--------------- 암호문
		 * 			복호화
		 * 
		 * 1) 양방향 암호화 방식 : 암호화, 복호화 둘 다 가능
		 * 2) 단방향 암호화 방식 : 암호화만 가능
		 * 
		 * 옛날에는 양방향암호화 방식으로 비밀번호 처리함
		 * => 기존의 비밀번호를 알려줄 수 있었음
		 * => 평문을 유추 할 수 있다는게 보안에 취약 => 보안정책이 바뀜 (단방향 암호화방식으로)
		 * => 이때 많이 사용하던 방식 SHA 암호화방식 (해쉬알고리즘)
		 * 	  => 매번 똑같은 평문을 입력하면 매번 똑같은 암호문으로 만들어줌 (단점)
		 * 		 ex) 1111 => xASDF@1234DFa..
		 * 			 1111 => xASDF@1234DFa..
		 * 	  => 샘플데이터가 많이 취합되면서 평문을 가지고 암호문을 유추할 수 있고
		 * 		 역으로 암호문만 넣으면 평문을 알려주는 레인보우 테이블이 만들어짐
		 * 
		 * => SHA방식의 문제점을 보완하기위해서 솔팅기법(salting) 추가
		 * 	  * 솔팅기법
		 * 		평문에 매번 랜덤값을 덧붙여서 암호화하게됨
		 * 		=> 똑같은 평문을 입력해도 암호문이 달라짐
		 * 		   ex) 1111 + salt값(45211) --> @231XwserX23
		 * 			   1111 + salt값(12345) --> @23cSERGv123
		 * 	  
		 * 	  스프링 시큐리티 모듈에서 제공하는 BCrypt암호화방식 -- 솔팅기법이 추가됨
		 * 	  1) 스프링 시큐리티 라이브러리 추가 (pom.xml)
		 * 	  2) BCryptPasswordEncoder 빈으로 등록 (xml파일)
		 * 	  3) web.xml에서 해당 spring-security.xml 파일 pre-loading 되도록 등록
		 * 
		 * 
		 * 
		 */
		
		m.setUserPwd(bcryptPwdEncoder.encode(m.getUserPwd()));
		
		log.debug("암호화 후 member: {}", m);
		
		int result = memberService.insertMember(m);
		
		/*
		 * 성공시 alert와 함께 메인페이지
		 * 실패시 alert와 함께 기존에 작업중이던 페이지 유지
		 */
		if(result>0) {
			ra.addFlashAttribute("alertMsg","회원가입 성공");
		}else {
			ra.addFlashAttribute("alertMsg","회원가입 실패");
			ra.addFlashAttribute("historyBackYN","Y");
		}
		
		return "redirect:/";
		
	}
	
	
	
	
	
	
	
	
	
	
}
