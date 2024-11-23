package com.luv2code.springboot.thymeleafdemo.aspect;

import com.mysql.cj.log.Log;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Aspect
@Component
public class DemoLoggingAspect {

    private Logger logger = Logger.getLogger(getClass().getName());

    @Pointcut("execution(* com.luv2code.springboot.thymeleafdemo.controller.*.*(..))")
    private void forControllerPackage(){

    }


    @Pointcut("execution(* com.luv2code.springboot.thymeleafdemo.service.*.*(..))")
    private void forServicePackage(){

    }



    @Pointcut("execution(* com.luv2code.springboot.thymeleafdemo.dao.*.*(..))")
    private void forDaoPackage(){

    }

    @Pointcut("forControllerPackage() || forServicePackage() || forDaoPackage()")
    private void forAppFlow(){}

    @Before("forAppFlow()")
    public void before(JoinPoint theJoinPoint){
String theMethod = theJoinPoint.getSignature().toShortString();
logger.info("===>>> in @Before : calling method : "+ theMethod);

Object[] args  = theJoinPoint.getArgs();
for(Object temp:args){
    logger.info("===>>> arguments : "+temp);

}


    }


    @AfterReturning(
            pointcut = "forAppFlow()",
            returning = "theResult"
    )
    public void afterReturning(JoinPoint theJoinPoint,Object theResult){

        String theMethod = theJoinPoint.getSignature().toShortString();
        logger.info("===>>> in @AfterReturning : from method : "+ theMethod);



        logger.info("===>>> result : "+ theResult);

    }


}
