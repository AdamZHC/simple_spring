package com.hit.adam.springframework.beans.factory;

import com.hit.adam.springframework.beans.BeansException;
import com.hit.adam.springframework.beans.factory.config.AutowireCapableBeanFactory;
import com.hit.adam.springframework.beans.factory.config.BeanDefinition;
import com.hit.adam.springframework.beans.factory.config.BeanPostProcessor;
import com.hit.adam.springframework.beans.factory.config.ConfigurableBeanFactory;

/**
 * 这些都是我不懂的
 *
 * 有点懂了，好像是配置的作用
 * 就是专门用来配置实现作用的，直接交给中间件设计的
 */
public interface ConfigurableListableBeanFactory extends ListableBeanFactory, AutowireCapableBeanFactory, ConfigurableBeanFactory {

    BeanDefinition getBeanDefinition(String beanName) throws BeansException;

    void preInstantiateSingletons() throws BeansException;

    void addBeanPostProcessor(BeanPostProcessor beanPostProcessor);

}