package com.example.springboot.service;

import com.example.springboot.domain.Hello;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class HelloServiceImpl implements HelloService {
    @Value("${com.example.id}")
    private String id;

    @Value("${com.example.name}")
    private String name;

    @Override
    public Hello say() {
        return new Hello(id, name);
    }
}
