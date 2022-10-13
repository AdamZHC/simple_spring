package com.hit.adam.springframework.aop.config;

import java.util.Map;

/**
 * 需要实现aop的方法需要实现这个接口
 *
 * 这样的设计可以方便实现两边的环绕传参
 * 然后就可以把两边的逻辑连接起来
 */
public interface MethodAdviser {

    /**
     * 在对应的方法前执行
     */
    Map<String, Object> doBefore(Object targetObject);
    /**
     * 在对应的方法之后执行
     */
    void doAfter(Map<String, Object> context, Object targetObject);
}
