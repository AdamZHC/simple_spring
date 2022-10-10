package com.hit.adam.springframework.simple.aop.demo;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * 这样就是没有实现参数化
 */
public class TigerProxy implements InvocationHandler {
    /**
     * 一定是被代理的对象
     * 如果是被代理类的话，就是不能实现对应的操作了
     * 因为有一个实现类的思考
     */
    private Tiger tiger;

    public TigerProxy (Tiger tiger) {
        this.tiger = tiger;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("Before Call Method");
        Object result = method.invoke(tiger, args);
        System.out.println("After Call Method");
        return result;
    }
}
