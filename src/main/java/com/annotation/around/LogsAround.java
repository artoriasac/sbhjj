package com.annotation.around;

import com.annotation.Logs;
import com.common.model.Result;
import com.common.utils.JSONUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LogsAround {
    @Around("@annotation(com.annotation.Logs)&&@annotation(logs)")
    public Object doInvoke(ProceedingJoinPoint joinPoint, Logs logs)throws Throwable{
        try {
            return  joinPoint.proceed();
        }catch (Exception e){
            return new Result<>("500",e.getMessage());
        }
    }
}
