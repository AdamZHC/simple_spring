package com.hit.adam.test.aop;

import com.hit.adam.springframework.aop.config.Aspect;

public class Tiger implements Animal{
    @Override
    @Aspect
    public void feed() {
        System.out.println("Tiger Feed!!!");
    }
}
