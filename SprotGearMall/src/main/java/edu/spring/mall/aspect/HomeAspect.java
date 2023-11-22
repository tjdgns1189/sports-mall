package edu.spring.mall.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component 
@Aspect // Aspect = Advice + Pointcut
public class HomeAspect {
	private static final Logger logger =
			LoggerFactory.getLogger(HomeAspect.class);

	@Pointcut("execution(* edu.spring.mall.HomeController.home(..))") 
	public void pcHome() {}
	
	@Around("pcHome()") 
	public Object homeAdvice(ProceedingJoinPoint jp) {
		Object result = null;
		
		logger.info("===== homeAdvice");
		
		try {
			logger.info("===== home() 호출");
			result = jp.proceed(); 
			logger.info("===== home() "); 
		} catch (Throwable e) { 
			
			logger.info("===== : " + e.getMessage());
		} finally {
			logger.info("===== finally");
		}
		
		return result;
	}
}
