package com.hit.adam.springframework.beans.factory.config;

import com.hit.adam.springframework.beans.BeansException;

/**
 * Factory hook that allows for
 * custom modification of new bean instances,e.g. checking
 * for marker interfaces or wrapping them with proxies
 */
public interface BeanPostProcessor {

    Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException;

    Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException;

}
