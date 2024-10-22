package com.br.sbatis.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

import lombok.extern.slf4j.Slf4j;


@Slf4j
//@Aspect

public class BeforeLoggingAdvice { // controller측 모든 메소드(PointCut)가 동작되기 전에 로그 출력(Advice)

	@Pointcut("execution (* com.br.sbatis.controller.*Controller.*(..))") /* 포인트컷 표현식 */
	public void setPointCut() {}										  /* 포인트컷 서명 */
	
	/*
	 * Before Advice 메소드 작성법 (즉, Pointcut 메소드 전에 실행시키고자 하는 메소드)
	 * 1. 반환타입 : void
	 * 2. 메소드명 : 마음대로 가능
	 * 3. 매개변수 : JoinPoint 타입 객체 (생략 가능, 핵심로직 정보를 알아내고자 할 경우 작성)
	 * 
	 */
	
	@Before("setPointCut()")
	public void loggingBeforeAdvice(JoinPoint joinPoint) { 
		//log.info("loggingBeforeAdvice 메소드 동작 &***********@#$%#%&@$&@$%^!#$%!#$%!&^");
		
		Object[] args = joinPoint.getArgs(); // 핵심로직이 실행될때 요청시 전달값 (배열)
		for(Object b : args) {
			//log.info("arg : {}",b);
		}
		
		// 현재 실행되는 핵심로직 메소드 정보
		Signature signature = joinPoint.getSignature();
		log.info("method : {}",signature.getName());		// 메소드명만
		log.info("short : {}",signature.toShortString());	// 클래스명 * 메소드명
		log.info("long : {}",signature.toLongString());		// 반환형 + 클래스명 + 매소드명
		
	}
	
	
	
}
