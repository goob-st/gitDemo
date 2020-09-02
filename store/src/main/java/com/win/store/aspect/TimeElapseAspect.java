package com.win.store.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * @Date 2020/8/6 2:33
 */
@Component
@Aspect
public class TimeElapseAspect {

    @Around("execution(* com.win.store.service.*.*(..))")
    public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
        //在业务方法之前执行的代码
        long start = System.currentTimeMillis();

        //执行业务方法，表示执行任何一个应用范围之内的方法
        Object obj = pjp.proceed();

        //在业务方法之后执行的代码
        long end = System.currentTimeMillis();
        System.err.println("执行时间：" + (end - start) + "ms.");

        return obj;
    }
}
