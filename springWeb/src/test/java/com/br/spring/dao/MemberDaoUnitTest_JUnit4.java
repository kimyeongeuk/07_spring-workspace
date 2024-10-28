package com.br.spring.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.br.spring.dto.MemberDto;

import lombok.extern.slf4j.Slf4j;

/*
 * JUnit
 * Java 개발시 가장 많이 사용되는 테스팅 기반 프레임워크
 * 독립된 단위 테스트를 지원함
 * 	   ㄴ 단위 테스트 : 소스코드가 의도한 대로 잘 작동되는지 함수 및 메소드에 대한 테스트
 * assert(단정메소드)로 테스트 케이스의 수행결과를 판별할 수 있음
 * 개발 단계 초기에 문제점을 발견해서 빠르게 해결할 수 있음
 * 
 * 
 * ContextConfiguration
 * 테스트시에는 서버를 구동하지 않기때문에 자동으로 빈 등록이 되지 않음
 * 이때 해당 객체를 Bean으로 등록시키는 파일을 강제로 읽어들일때 필요한 어노테이션
 * 
 * 1) <bean>		location="file:src/main/webapp/WEB-INF/spring/root-context.xml"
 * 2) @Bean			classes="AppConfig.class"
 * 3) 빈스캐닝		location="file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"
 */


@RunWith(SpringJUnit4ClassRunner.class) // JUnit4를 이용하겠다는걸 의미
@ContextConfiguration(locations={"file:src/main/webapp/WEB-INF/spring/root-context.xml",
								"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})
@FixMethodOrder(MethodSorters.NAME_ASCENDING) // 메소드의 이름순으로 테스트를 수행하겠다는걸 의미
public class MemberDaoUnitTest_JUnit4 {

	@Autowired
	private MemberDao memberDao;
	
	
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
		// given
		MemberDto m = MemberDto.builder().userId("admin01").userPwd("1234").build();
		// when
		MemberDto result = memberDao.selectMember(m);
		// then
		assertNotNull(result);
		
		
	}

	@Ignore
	@Test
	public void test02_회원가입테스트() {
		// given
		MemberDto m = MemberDto.builder().userId("test03").userPwd("1234").userName("테스트").email("test@br.or.kr")
										 .gender("M").phone("010-1111-2222").address("서울시 강서구 마곡동").build();
		
		// when
		int result = memberDao.insertMember(m);
		
		// then
		assertEquals(1, result);
	}
	
	@Test
	public void test03_일치하는아이디수조회테스트() {
		// given
		String ckechId = "user01";
		// when
		int result = memberDao.selectUserIdCount(ckechId);
		// then
		assertEquals(1, result);
		
	}
	
	@Test
	public void test04_회원정보변경테스트() {
		// given
		MemberDto m = MemberDto.builder().userId("user01").userName("변경테스트").email("updateTest.br.com")
										 .phone("010-0000-0000").address("서울시 강남구").gender("F").build();
		// when
		int result = memberDao.updateMember(m);
		// then
		assertEquals(1, result);
		
	}
	
	@Test
	public void test05_회원탈퇴테스트() {
		// given
		String userId = "user02";
		// when
		int result = memberDao.deleteMember(userId);
		// then
		assertEquals(1, result);
		
		
	}
	
	
	
}
