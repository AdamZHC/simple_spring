package com.hit.adam.springframework.aop.support.cglib;

import com.hit.adam.springframework.aop.CommonProxyDispatcher;
import com.hit.adam.springframework.aop.ProxyDispatcher;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;

public abstract  class AbstractCglibProxyDispatcher implements ProxyDispatcher, CommonProxyDispatcher {
    @Override
    public Object getProxyInstance() {
        Enhancer enhancer = new Enhancer();
        Class<?> clazz = getTargetObject().getClass();
        enhancer.setSuperclass(clazz);
        // 设置enhancer的回调对象
        enhancer.setCallback(getMethodInterceptor());
        // 创建代理对象
        Object o = enhancer.create();
        return o;
    }

    protected abstract MethodInterceptor getMethodInterceptor();

}
