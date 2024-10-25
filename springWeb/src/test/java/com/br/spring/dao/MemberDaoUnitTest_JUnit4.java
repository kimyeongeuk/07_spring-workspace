package com.br.spring.dao;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/*
 * JUnit
 * Java 개발시 가장 많이 사용되는 테스팅 기반 프레임워크
 * 독립된 단위 테스트를 지원함
 * 	   ㄴ 단위 테스트 : 소스코드가 의도한 대로 잘 작동되는지 함수 및 메소드에 대한 테스트
 * assert(단정메소드)로 테스트 케이스의 수행결과를 판별할 수 있음
 * 개발 단계 초기에 문제점을 발견해서 빠르게 해결할 수 있음
 * 
 */


@RunWith(SpringJUnit4ClassRunner.class) // JUnit4를 이용하겠다는걸 의미
public class MemberDaoUnitTest_JUnit4 {

	/*
	 * 테스트 케이스 작성 패턴
	 * 
	 * given (준비) : 어떤 데이터를 가지고
	 * when	 (실행) : 어떤 메소드 실행시
	 * then  (검증) : 어떤 결과가 나와야되는지
	 * 			ㄴ assertEquals(예상값, 실행값) : 실행값이 예상값과 일치하는지 확인해주는 메소드
	 * 			ㄴ assertNotNull(실행값)		: 실행값이 null이 아닌지 확인해주는 메소드
	 * 			ㄴ assertTrue(조건)				: 해당 조건이 참인지를 확인해주는 메소드 
	 */
	
	@Test
	public void test01_로그인테스트() {
		
	}

	@Test
	public void test02_회원가입테스트() {
		
	}
	
	@Test
	public void test03_일치하는아이디수조회테스트() {
		
	}
	
	@Test
	public void test04_회원정보변경테스트() {
		
	}
	
	@Test
	public void test05_회원탈퇴테스트() {
		
	}
	
	
	
}
