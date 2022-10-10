package com.hit.adam.springframework.aop.config;

public interface AspectInterceptor {
    /**
     * 获取方法的拦截表达式
     * 大致上会有权限类名和方法的表达
     * String methodMather();
     */

    /**
     * 在对应的方法前执行
     */
    void doBefore(Object targetObject);
    /**
     * 在对应的方法之后执行
     */
    void doAfter(Object targetObject);
}
