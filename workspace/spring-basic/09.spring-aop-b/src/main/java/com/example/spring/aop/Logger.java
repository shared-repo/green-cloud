package com.example.spring.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class Logger {
	
	@Pointcut("execution(* *..*.*(..))")
	public void pointcutAll() {}
	
	@Pointcut("execution(* com.example..*.*1(..))")
	public void pointcut1() {}
	
	
	@Before("pointcutAll()") // 별도 선언된 포인트컷 적용
	public void logBefore(JoinPoint joinPoint) { // JoinPoint : 현재 호출되고 있는 메서드 정보 저장
		System.out.printf("==========> Logger.logBefore : %s.%s <==========\n", 
						  joinPoint.getSignature().getDeclaringTypeName(), 
						  joinPoint.getSignature().getName());
	}
	
	@After("execution(* *..*.*(..))") // 현재 어드바이스에 적용되는 포인트컷 직접 적용
	public void logAfter(JoinPoint joinPoint) { // JoinPoint : 현재 호출되고 있는 메서드 정보 저장
		System.out.printf("==========> Logger.logAfter : %s.%s <==========\n", 
						  joinPoint.getSignature().getDeclaringTypeName(), 
						  joinPoint.getSignature().getName());
	}
	
	@Around("pointcut1()")
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
