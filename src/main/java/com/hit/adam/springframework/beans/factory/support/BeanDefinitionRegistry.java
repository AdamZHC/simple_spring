package com.hit.adam.springframework.beans.factory.support;

import com.hit.adam.springframework.beans.factory.config.BeanDefinition;

public interface BeanDefinitionRegistry {

    void registerBeanDefinition(String name, BeanDefinition beanDefinition);
}
