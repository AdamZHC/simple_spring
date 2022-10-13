package com.hit.adam.springframework.aop.support.jdk;

import com.hit.adam.springframework.aop.config.MethodAdviser;
import com.hit.adam.springframework.aop.info.AspectInfo;

import java.lang.reflect.Method;

public class DefaultJdkProxyDispatcher extends AbstractJdkAspectWireProxyDispatcher {
    /**
     * 提供切面的信息的
     */
    private AspectInfo aspectInfo;

    public void setAspectInfo(AspectInfo aspectInfo) {
        this.aspectInfo = aspectInfo;
    }

    @Override
    public MethodAdviser getMethodAdviser() {
        return this.aspectInfo.getMethodAdviser();
    }

    @Override
    public Method[] getTargetMethods() {
        return aspectInfo.getTargetMethods();
    }

    @Override
    public Object getTargetObject() {
        return this.aspectInfo.getTargetObject();
    }
}
