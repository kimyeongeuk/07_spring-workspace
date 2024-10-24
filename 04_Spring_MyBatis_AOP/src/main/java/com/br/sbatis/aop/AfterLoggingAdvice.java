package com.br.sbatis.aop;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;

import lombok.extern.slf4j.Slf4j;

@Slf4j
//@Aspect
public class AfterLoggingAdvice {

	/*
	 * After Advice 메소드 작성법
	 * 1. 반환타입 : void
	 * 2. 메소드명 : 마음대로
	 * 3. 매개변수 : JoinPoint (생략 가능)
	 */
	
	@After("execution(* com.br.sbatis.controller.*Controller.*(..))")
	public void afterLoggingAdvice() {
		log.info("afterLoggingAdvice 동작 *****************");
	}
	
	
	
}
