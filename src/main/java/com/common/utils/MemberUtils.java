package com.common.utils;

import com.common.model.MemberInfo;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author:artorias
 * @Description:
 * @Date:create in 9:47 2018/7/31 0031
 * Modeified By:
 */
public class MemberUtils {

    public static final String MEMBER_INFO="MEMBER_INFO";

    public static void setMemberInfo(MemberInfo memberInfo){
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = (HttpServletRequest) requestAttributes.resolveReference(RequestAttributes.REFERENCE_REQUEST);
        String s = JSONUtils.objToJson(memberInfo);
        request.getSession().setAttribute(MEMBER_INFO,s);
    }

    public static MemberInfo getMemberInfo(){
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = (HttpServletRequest) requestAttributes.resolveReference(RequestAttributes.REFERENCE_REQUEST);
        String attribute = (String) request.getSession().getAttribute(MEMBER_INFO);
        if (attribute==null){
            return null;
        }
        MemberInfo memberInfo = JSONUtils.jsonToObj(attribute, MemberInfo.class);
        return  memberInfo;
    }

    public static void delMemberInfo(){
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = (HttpServletRequest) requestAttributes.resolveReference(RequestAttributes.REFERENCE_REQUEST);
        request.getSession().removeAttribute(MEMBER_INFO);
    }
}
