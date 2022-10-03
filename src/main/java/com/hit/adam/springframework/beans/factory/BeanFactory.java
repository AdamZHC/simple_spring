package com.hit.adam.springframework.beans.factory;

import com.hit.adam.springframework.beans.BeansException;

/**
 * 最最核心的接口
 * 以及最最的核心的方法就是这个 getBean
 */
public interface BeanFactory {

    Object getBean(String name) throws BeansException;
}
