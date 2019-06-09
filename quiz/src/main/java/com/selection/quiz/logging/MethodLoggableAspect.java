package com.selection.quiz.logging;

import java.lang.reflect.Method;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.context.annotation.Configuration;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Aspect
@Configuration
public class MethodLoggableAspect {
	
	@Around("@annotation (com.selection.quiz.logging.MethodLoggable)")
	public Object aroundService (ProceedingJoinPoint joinPoint) throws Throwable {
		Method method = MethodSignature.class.cast(joinPoint.getSignature()).getMethod();
		log.debug(" {} Service Started.....", method.getName());
		Object response = joinPoint.proceed();
		log.debug(" {} Service Exit.....", method.getName());
		return response;
	}
	
	@Around ("execution(* com.selection.quiz.controller.*.*(..))")
	public Object aroundController (ProceedingJoinPoint joinPoint) throws Throwable {
		Method method = MethodSignature.class.cast(joinPoint.getSignature()).getMethod();
		log.debug(" {} Controller Started.....", method.getName());
		Object response = joinPoint.proceed();
		log.debug(" {} Controller Exit.....", method.getName());
		return response;
	}
}
