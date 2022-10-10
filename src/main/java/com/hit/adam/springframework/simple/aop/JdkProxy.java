package com.hit.adam.springframework.simple.aop;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class JdkProxy implements InvocationHandler {
    //需要代理的对象
    private Object targetObject;

    /**
     * 获取代理对象
     * @param targetObject: 传入被代理的对象
     * @return 对应的代理对象
     */
    public Object getJdkProxy(Object targetObject) {
        this.targetObject = targetObject;
        Object proxyInstance = Proxy.newProxyInstance(targetObject.getClass().getClassLoader(), targetObject.getClass().getInterfaces(), this);
        return proxyInstance;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("JDK动态代理，监听开始！");
        // 调用invoke方法，result存储该方法的返回值
        // 被代理的对象
        Object result = method.invoke(targetObject,args);
        System.out.println("JDK动态代理，监听结束！");
        return result;
    }
}
