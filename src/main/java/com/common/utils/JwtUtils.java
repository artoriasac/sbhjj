package com.common.utils;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;

public class JwtUtils {
    public static String generatorToken(String subject,String key){
        byte[] bytes = DatatypeConverter.parseBase64Binary(key);
        Key secretKey = new SecretKeySpec(bytes, SignatureAlgorithm.HS256.getJcaName());
        return Jwts.builder().setSubject(subject).signWith(secretKey).compact();
    }

    public static String phaseToken(String key,String jwt){
        byte[] bytes = DatatypeConverter.parseBase64Binary(key);
        Key secretKey = new SecretKeySpec(bytes, SignatureAlgorithm.HS256.getJcaName());
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(jwt).getBody().getSubject();
    }
}
