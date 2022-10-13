package com.hit.adam.springframework.aop;

import com.hit.adam.springframework.aop.config.MethodAdviser;

import java.lang.reflect.Method;

public interface AssistanceProxyDispatcher {

    MethodAdviser getMethodAdviser();

    abstract Method[] getTargetMethods();
}
