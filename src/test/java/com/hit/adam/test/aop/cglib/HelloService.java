package com.hit.adam.test.aop.cglib;

public class HelloService {
    /**
     * 被代理方法
     */
    public void sayHello() {
        System.out.println("Hello Spring!");
    }
}
