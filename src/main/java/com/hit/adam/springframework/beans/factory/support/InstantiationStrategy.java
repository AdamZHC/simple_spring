package com.hit.adam.springframework.beans.factory.support;

import com.hit.adam.springframework.beans.factory.config.BeanDefinition;

import java.lang.reflect.Constructor;

@SuppressWarnings("All")
public interface InstantiationStrategy {
    /**
     * 也就是说实例化的方法全部交给了这个接口对应的方法去做
     */
    Object instantiate(BeanDefinition beanDefinition, String beanName, Constructor ctor, Object[] args);
}
