/**
 *
 */
package com.imooc.web.aspect;

import java.util.Date;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * @author ai
 *
 */
//@Aspect
//@Component
public class TimeAspect {

    /**
     * @Around 环绕通知，写切片的时候一般都用around来写
     * ProceedingJoinPoint 包含参数名和值
     * @param pjp
     * @return
     * @throws Throwable
     */
    @Around("execution(* com.imooc.web.controller.UserController.*(..))")
    public Object handleControllerMethod(ProceedingJoinPoint pjp) throws Throwable {

        System.out.println("time aspect start");
        // 参数名
        Object[] args = pjp.getArgs();
        for (Object arg : args) {
            System.out.println("arg is " + arg);
        }

        long start = new Date().getTime();

        // 和过滤器的doFilter()方法一样 相当于切入点
        Object object = pjp.proceed();

        System.out.println("time aspect 耗时:" + (new Date().getTime() - start));

        System.out.println("time aspect end");

        return object;
    }

}
