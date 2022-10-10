package com.hit.adam.springframework.aop.info;

import java.lang.reflect.Method;

public interface AspectInfoProvider {
    /**
     * 获得切面的类
     */
    Class getTargetClass();
    /**
     * 获得目的方法
     */
    Method[] getTargetMethods();
}
