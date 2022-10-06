package com.hit.adam.springframework.beans.factory;

import com.hit.adam.springframework.beans.BeansException;
import com.hit.adam.springframework.beans.factory.config.AutowireCapableBeanFactory;
import com.hit.adam.springframework.beans.factory.config.BeanDefinition;
import com.hit.adam.springframework.beans.factory.config.ConfigurableBeanFactory;

/**
 * 这些都是我不懂的
 */
public interface ConfigurableListableBeanFactory extends ListableBeanFactory, AutowireCapableBeanFactory, ConfigurableBeanFactory {

    BeanDefinition getBeanDefinition(String beanName) throws BeansException;

}