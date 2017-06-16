package com.ys.web.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;

//@Aspect
//@Component
public class WebAspect {

    private StringBuilder sb = new StringBuilder();

    @Autowired
    public WebAspect() {
    }

    @Around("execution(* com.ys.web.controller..*(..))")
    public Object performanceLogging(ProceedingJoinPoint pjp) throws Throwable {

        long startTime = System.currentTimeMillis();

        String signatureName=null;

        if (pjp.getSignature() != null)
            signatureName = pjp.getSignature().getName();

        int argLength = pjp.getArgs().length;

        for (int i = 0; i < argLength; i++)
            sb.append(pjp.getArgs()[i]).append(", ");


        String sessionId = "";

        if (RequestContextHolder.currentRequestAttributes() != null)
            sessionId = RequestContextHolder.currentRequestAttributes().getSessionId();

        Object returnValue = pjp.proceed();

        long endTime=System.currentTimeMillis();

        System.out.println("------------------------------------------------------");
        System.out.println(signatureName);
        System.out.println("argumetns : " + sb.toString());
        System.out.println("elapsed in millsec: " + (endTime-startTime));
        System.out.println("------------------------------------------------------");

        return returnValue;
    }

}
