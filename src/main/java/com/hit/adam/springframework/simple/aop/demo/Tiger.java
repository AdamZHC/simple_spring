package com.hit.adam.springframework.simple.aop.demo;

public class Tiger implements Animal {
    @Override
    public void feed() {
        System.out.println("Tiger eat sheep!");
    }
}
