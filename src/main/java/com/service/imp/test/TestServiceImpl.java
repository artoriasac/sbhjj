package com.service.imp.test;

import com.service.inf.test.TestService;
import org.springframework.stereotype.Service;

@Service
public class TestServiceImpl implements TestService {
    @Override
    public String test(String test) {
        return "bbb";
    }
}
