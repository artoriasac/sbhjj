package com.annotation.around;

import com.common.model.MemberInfo;
import com.common.model.Result;
import com.common.utils.MemberUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.DefaultParameterNameDiscoverer;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

/**
 * @Author:artorias
 * @Description:
 * @Date:create in 9:43 2018/7/31 0031
 * Modeified By:
 */
@Component
@Aspect
@Order(1)
public class LoginAround {
    @Around("@annotation(com.annotation.Login)")
    public Object doInvoke(ProceedingJoinPoint joinPoint)throws Throwable{
    MemberInfo memberInfo = MemberUtils.getMemberInfo();
    if(memberInfo==null) {
        return new Result<>("600","未登录");
    }
     return joinPoint.proceed();
    }
}
