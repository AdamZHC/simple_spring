package com.hit.adam.springframework.aop.support;

import com.hit.adam.springframework.aop.config.AspectPolisher;
import com.hit.adam.springframework.aop.info.AspectInfo;

import java.lang.reflect.Method;
import java.util.Arrays;

public class DefaultProxyDispatcher extends AbstractAspectWireProxyDispatcher {
    /**
     * 提供切面的信息的
     */
    private AspectInfo aspectInfo;

    public void setAspectInfo(AspectInfo aspectInfo) {
        this.aspectInfo = aspectInfo;
    }

    @Override
    protected AspectPolisher getAspectPolisher() {
        return this.aspectInfo.getAspectPolisher();
    }

    @Override
    protected Method[] getTargetMethods() {
        return aspectInfo.getTargetMethods();
    }

    @Override
    protected Object getTargetObject() {
        return this.aspectInfo.getTargetObject();
    }
}
