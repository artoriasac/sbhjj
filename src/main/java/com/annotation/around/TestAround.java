package com.annotation.around;

import com.annotation.Test;
import com.annotation.TestAnnotation;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.*;

import static java.util.stream.Collectors.toList;

/**
 * @Author:artorias
 * @Description:
 * @Date:create in 16:59 2018/7/30 0030
 * Modeified By:
 */
@Aspect
@Component
public class TestAround {

    @Around("@annotation(com.annotation.Test)&&@annotation(test)")
    public Object doInvoke(ProceedingJoinPoint joinPoint,Test test)throws Throwable{
        System.out.println("around success->"+test.value());
        return   joinPoint.proceed();


    }
}
