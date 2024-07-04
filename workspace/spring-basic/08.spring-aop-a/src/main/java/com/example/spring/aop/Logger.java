package com.example.spring.aop;

import org.aspectj.lang.JoinPoint;

public class Logger {
	
	// public void logBefore() {
	public void logBefore(JoinPoint joinPoint) { // JoinPoint : 현재 호출되고 있는 메서드 정보 저장
		System.out.printf("==========> Logger.logBefore : %s.%s <==========\n", 
						  joinPoint.getSignature().getDeclaringTypeName(), 
						  joinPoint.getSignature().getName());
	}
	
	// public void logAfter() {
	public void logAfter(JoinPoint joinPoint) { // JoinPoint : 현재 호출되고 있는 메서드 정보 저장
		System.out.printf("==========> Logger.logAfter : %s.%s <==========\n", 
						  joinPoint.getSignature().getDeclaringTypeName(), 
						  joinPoint.getSignature().getName());
	}


}
