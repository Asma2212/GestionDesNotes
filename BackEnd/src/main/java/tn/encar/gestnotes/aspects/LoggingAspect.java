package tn.encar.gestnotes.aspects;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;


@Component
@Aspect
public class LoggingAspect {
private static final Logger logger = LogManager.getLogger(LoggingAspect.class);
@Before("execution(* tn.encar.gestnotes.services.impl.EnseignantService.*(..))")
public void logMethodEntry(JoinPoint joinPoint) {
String name = joinPoint.getSignature().getName();
logger.info("La methode " + name + " : ");
}
}
