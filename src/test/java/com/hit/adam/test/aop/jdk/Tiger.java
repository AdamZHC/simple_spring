package com.hit.adam.test.aop.jdk;

import com.hit.adam.springframework.aop.config.Pointcut;

public class Tiger implements Animal{
    @Override
    @Pointcut
    public void feed() {
        System.out.println("Tiger Feed!!!");
    }
}
