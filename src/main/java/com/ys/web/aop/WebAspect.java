package com.ys.web.aop;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
public class WebAspect {

    @Pointcut("execution(* com.ys.web.controller.ArticleController.home(..))")
    public  void hi(){


    }


//    @AfterReturning("hi()")
//    public void hihi(){
//        //System.out.println("asdfasdfasdfsdfasdfsdfasdfas");
//    }
//    @Before("hi()")
//    public void performanceLogging() throws Throwable {
//        //StringBuilder sb = new StringBuilder();
//
//        long startTime = System.currentTimeMillis();
//
//
//
//        long endTime=System.currentTimeMillis();
//
//        System.out.println("------------------------------------------------------");
//        System.out.println("argumetns : pointcut");
//        System.out.println("elapsed in millsec: " + (endTime-startTime));
//        System.out.println("------------------------------------------------------");
//
//
//    }

//    @Around("execution(* com.ys.web.controller..*.*(..))")
//    public Object performanceLogging(ProceedingJoinPoint pjp) throws Throwable {
//
//        long startTime = System.currentTimeMillis();
//
//        String signatureName=null;
//
//        if (pjp.getSignature() != null)
//            signatureName = pjp.getSignature().getName();
//
//        int argLength = pjp.getArgs().length;
//
//        for (int i = 0; i < argLength; i++)
//            sb.append(pjp.getArgs()[i]).append(", ");
//
//
//        String sessionId = "";
//
//        if (RequestContextHolder.currentRequestAttributes() != null)
//            sessionId = RequestContextHolder.currentRequestAttributes().getSessionId();
//
//        Object returnValue = pjp.proceed();
//
//        long endTime=System.currentTimeMillis();
//
//        System.out.println("------------------------------------------------------");
//        System.out.println(signatureName);
//        System.out.println("argumetns : " + sb.toString());
//        System.out.println("elapsed in millsec: " + (endTime-startTime));
//        System.out.println("------------------------------------------------------");
//
//        return returnValue;
//    }

}
