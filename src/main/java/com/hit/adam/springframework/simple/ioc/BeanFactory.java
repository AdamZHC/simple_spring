package com.hit.adam.springframework.simple.ioc;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Bean容器
 *
 * 其实编码方式主要依托于：接口定义、类实现接口、抽象类
 * 实现接口、继承类、继承抽象类，而这些操作方式可以很好的隔离开每个类的
 * 基础功能、通用功能和业务功能，当类的职责清晰后，你的整个设计也会变得
 * 容易扩展和迭代。
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
