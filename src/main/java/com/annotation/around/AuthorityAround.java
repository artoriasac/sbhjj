package com.annotation.around;

import com.annotation.Authority;
import com.common.model.Result;
import com.common.utils.MemberUtils;
import com.service.inf.user.UserService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Order(2)
public class AuthorityAround {
    @Autowired
    private UserService userService;

    @Around("@annotation(com.annotation.Authority)&&@annotation(authority)")
    public Object doInvoke(ProceedingJoinPoint joinPoint,Authority authority)throws Throwable{
        int value = authority.value();
        if (value==1){
            Integer id = MemberUtils.getMemberInfo().getId();
            if (!userService.isAdmin(id)) {
               return new Result<>("600", "权限不足");
           }
        }
        return joinPoint.proceed();
    }
}
