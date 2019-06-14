package com.common.interceptor;

import com.common.utils.MemberUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class HttpInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object obj) throws Exception {
        String header = request.getHeader("X-Real-IP");
        if (header!=null){
            request.setAttribute(MemberUtils.IP,header);
        }else {
            request.setAttribute(MemberUtils.IP,request.getRemoteAddr());
        }
        return true;
    }
}
