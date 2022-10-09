package com.hit.adam.springframework.beans.factory.config;

import com.hit.adam.springframework.beans.factory.HierarchicalBeanFactory;

/**
 * 另外加了一个功能就是配置的BeanFactory
 */
public interface ConfigurableBeanFactory extends HierarchicalBeanFactory, SingletonBeanRegistry {
    /**
     * 接口里面也可以添加成员变量
     */
    String SCOPE_SINGLETON = "singleton";

    String SCOPE_PROTOTYPE = "prototype";

    void addBeanPostProcessor(BeanPostProcessor beanPostProcessor);

}
