package com.hit.adam.springframework.aop.support;

import com.hit.adam.springframework.aop.config.AspectInterceptor;
import com.hit.adam.springframework.aop.info.AspectInfoProvider;

import java.lang.reflect.Method;

public class DefaultProxyDispatcher extends AbstractAspectWireProxyDispatcher{
    /**
     * 提供切面的
     */
    private AspectInterceptor aspectInterceptor;
//    /**
//     * 提供信息
//     */
//    private AspectInfoProvider provider;
    /**
     * 提供对应的功能
     */
    private Object target;

    public void setTarget(Object target) {
        this.target = target;
    }

    public void setAspectInterceptor(AspectInterceptor aspectInterceptor) {
        this.aspectInterceptor = aspectInterceptor;
    }

//    public void setProvider(AspectInfoProvider provider) {
//        this.provider = provider;
//    }
//
//
//    public AspectInfoProvider getProvider() {
//        return provider;
//    }

//    @Override
//    protected Method[] getMethods() {
//
//    }

    @Override
    protected AspectInterceptor getInterceptor() {
        return this.aspectInterceptor;
    }

    @Override
    protected Object getTargetObject() {
        return this.target;
    }
}
