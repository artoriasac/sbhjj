package com.common.utils;


import org.apache.poi.ss.formula.functions.T;
import org.springframework.http.*;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

public class SendHttpRequestUtils {
    private static RestTemplate restTemplate=new RestTemplate();



    public static ResponseEntity<T>  SendGetRequest(String url, Class T){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        ResponseEntity<T> resp = restTemplate.exchange(url, HttpMethod.GET, null,T);
        return resp;
    }

    public static <T> ResponseEntity<T>  SendPostRequest(String url, HttpEntity<MultiValueMap<String, String>> request, Class T){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        ResponseEntity<T> resp = restTemplate.postForEntity(url, request,T);
        return resp;
    }
}
