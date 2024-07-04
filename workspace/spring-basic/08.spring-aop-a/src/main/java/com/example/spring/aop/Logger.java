package com.example.spring.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;

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
	
	public Object logAround(ProceedingJoinPoint joinPoint) {
		
		long start = System.currentTimeMillis();
		
		Object returnValue = null;
		
		try {
			// before area
			returnValue = joinPoint.proceed(); // 실제 메서드 호출 ( 필수 )
			// after returning area
		} catch (Throwable ex) {	
			// after throwing area
		} finally {
			// after area
			long stop = System.currentTimeMillis();
			System.out.printf("==========> Execution Lap : %d (%s.%s) <==========\n",
					stop - start,
					joinPoint.getSignature().getDeclaringTypeName(), 
					joinPoint.getSignature().getName());
		}
		
		return returnValue;
	}


}
