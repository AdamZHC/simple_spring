package com.hit.adam.springframework.simple.ioc;

/**
 * Bean的定义类
 * 用来抽象所有的类
 */
public class BeanDefinition {

    private Object bean;

    public BeanDefinition(Object bean) {
        this.bean = bean;
    }

    public Object getBean() {
        return this.bean;
    }

}
