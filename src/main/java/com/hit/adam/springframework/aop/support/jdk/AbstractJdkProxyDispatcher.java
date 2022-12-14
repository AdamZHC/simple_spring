package com.hit.adam.springframework.aop.support.jdk;

import com.hit.adam.springframework.aop.CommonProxyDispatcher;
import com.hit.adam.springframework.aop.ProxyDispatcher;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public abstract class AbstractJdkProxyDispatcher implements ProxyDispatcher, CommonProxyDispatcher {

    @Override
    public Object getProxyInstance() {
        Object targetObject = getTargetObject();
        Object proxyInstance = Proxy.newProxyInstance(
                Thread.currentThread().getContextClassLoader(),
                //这里是可以这样的
                //应该是getClass访问到方法区了，然后就把类的完成的信息都返回了
                targetObject.getClass().getInterfaces(),
                getInvocationHandler()
        );
        return proxyInstance;
    }
    //这就是关键完成的地方
    protected abstract InvocationHandler getInvocationHandler();

}
