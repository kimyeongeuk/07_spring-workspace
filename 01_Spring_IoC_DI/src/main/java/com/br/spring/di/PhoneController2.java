package com.br.spring.di;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PhoneController2 {
	
	/*
	 * 직접 Service 객체 생성할 경우 (스프링 사용 전)
	 * private PhoneService pService 
	 * 				= new PhoneServiceWebImpl()		:	웹 개발 당시
	 * 				= new PhoneServiceMobileImpl()	:	모바일 개발 당시
	 * 개발자가 직접 new 객체 생성시 "결합도가 높음"의 문제 발생
	 * 결합도(종속관계)	: 연관관계의 클래스들 간에 각 클래스 수정시 서로에게 영향을 미치는 정도
	 * 
	 * 	ex) 연관관계의 클래스
	 * 		public class A {
	 * 			private B b = new B();
	 * 		}
	 * 		public class B {
	 * 
	 * 		}
	 * 
	 * 		A클래스에서 B클래스를 필드로 사용할 경우 연관관계 형성
	 * 		만일 B 클래스가 변경(클래스명, 생성자 또는 메소드 수정 등)될 경우
	 * 		A 클래스도 일부 수정해야되는 상황 발생
	 * 		=> "두 객체간에 결합도가 높다"라고 표현
	 * 
	 * 스프링의 Ioc + DI를 활용해서 해결 가능
	 * 1) 개발에 사용할 서비스 클래스를 빈으로 등록 (xml || java || MVC용 어노테이션)
	 * 2) DI를 통해 객체를 주입받아 사용
	 * 	  
	 * 	  => 사용할 객체의 클래스가 변경되도 해당 객체를 참조하는 클래스에서
	 * 		 수정할 필요가 없음 => 결합도가 낮아짐
	 * 
	 */
	
	@Autowired
	private PhoneService pService;
	
	
	@RequestMapping("/list.ph")
	public void selectList() {
		pService.selectList();
	}

	@RequestMapping("insert.ph")
	public void insertPhone() {
		pService.insertPhone();
	}
	
	
}
