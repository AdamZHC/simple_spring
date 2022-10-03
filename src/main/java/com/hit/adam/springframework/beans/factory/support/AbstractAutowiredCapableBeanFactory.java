package com.hit.adam.springframework.beans.factory.support;

import com.hit.adam.springframework.beans.factory.config.BeanDefinition;

/**
 * 注意到这里可以选择性地去继承
 */
public abstract class AbstractAutowiredCapableBeanFactory extends AbstractBeanFactory {

    @Override
    public Object createBean(String beanName, BeanDefinition beanDefinition) {
        Object bean = null;
        try {
            bean = beanDefinition.getBeanClass().newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        addSingletonObject(beanName, bean);
        return bean;
    }
}
