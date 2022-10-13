package com.hit.adam.springframework.aop.info;

import com.hit.adam.springframework.aop.config.MethodAdviser;

import java.lang.reflect.Method;

public interface AspectInfo {
    /**
     * 获得切面的类
     */
    Object getTargetObject();
    /**
     * 获得目的方法
     */
    Method[] getTargetMethods();
    /**
     * 获取切面方法
     */
    MethodAdviser getMethodAdviser();
}
