package com.hit.adam.springframework;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Bean容器
 */
public class BeanFactory {

    Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>();

    public Object getBean(String name) {
        return beanDefinitionMap.get(name).getBean();
    }

    public void register(String name, BeanDefinition beanDefinition) {
        beanDefinitionMap.put(name, beanDefinition);
    }
}
