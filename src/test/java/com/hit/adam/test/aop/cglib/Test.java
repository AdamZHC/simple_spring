package com.hit.adam.test.aop.cglib;

import net.sf.cglib.proxy.Enhancer;

public class Test {
    public static void main(String[] args) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(HelloService.class);
        enhancer.setCallback(new HelloMethodInterceptor());

        HelloService o = (HelloService)enhancer.create();
        o.sayHello();
    }
}
