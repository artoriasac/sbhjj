package com.common.utils;

import com.common.model.MemberInfo;
import com.common.redis.service.RedisService;
import org.elasticsearch.common.geo.GeoHashUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

/**
 * @Author:artorias
 * @Description:
 * @Date:create in 9:47 2018/7/31 0031
 * Modeified By:
 */
@Component
public class MemberUtils {

    public static final String MEMBER_INFO="MEMBER_INFO";

    public static final String IP="MEMBER_IP";


    private static RedisService redisService;

    @Autowired
    public void setRedisServer (RedisService redisService){
        MemberUtils.redisService=redisService;
    }

    public static String setMemberInfo(MemberInfo memberInfo){
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = (HttpServletRequest) requestAttributes.resolveReference(RequestAttributes.REFERENCE_REQUEST);
        String ip =(String)request.getAttribute(IP);
        String info = JSONUtils.objToJson(memberInfo);
        String jwtKey = getJwtKey();
        String jwt = JwtUtils.generatorToken(info, jwtKey);
        if (redisService.hasKey(getRedisKey(ip))){
            redisService.del(getRedisKey(ip));
        }
        redisService.set(getRedisKey(ip),jwtKey);
        request.getSession().setAttribute(MEMBER_INFO,info);
        return jwt;
    }

    public static MemberInfo getMemberInfo(){
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = (HttpServletRequest) requestAttributes.resolveReference(RequestAttributes.REFERENCE_REQUEST);
        String attribute = (String) request.getSession().getAttribute(MEMBER_INFO);
        if (attribute==null){
            String ip =(String)request.getAttribute(IP);
            String jwtKey = redisService.get(getRedisKey(ip));
            String jwt = request.getHeader("jwt");
            if (jwt==null||jwt.isEmpty()){
                return null;
            }
            attribute = JwtUtils.phaseToken(jwtKey, jwt);
            if (attribute==null){
                return null;
            }
        }
        MemberInfo memberInfo = JSONUtils.jsonToObj(attribute, MemberInfo.class);
        return  memberInfo;
    }

    public static void delMemberInfo(){
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = (HttpServletRequest) requestAttributes.resolveReference(RequestAttributes.REFERENCE_REQUEST);
        request.getSession().removeAttribute(MEMBER_INFO);
    }

    public static String getJwtKey(){
        String uuid = UUID.randomUUID().toString()+UUID.randomUUID().toString();
        return uuid;
    }

    public static String getRedisKey(String ip){
        return IP+ip;
    }
}
